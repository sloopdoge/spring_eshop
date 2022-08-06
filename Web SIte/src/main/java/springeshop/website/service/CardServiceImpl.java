package springeshop.website.service;

import org.springframework.stereotype.Service;
import springeshop.website.domain.*;
import springeshop.website.dto.CardDTO;
import springeshop.website.dto.CardDetailsDTO;
import springeshop.website.repository.CardRepository;
import springeshop.website.repository.ProductsRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService{

    private final CardRepository cardRepository;
    private final ProductsRepository productsRepository;
    private final UserService userService;
    private final OrderService orderService;

    public CardServiceImpl(CardRepository cardRepository,
                           ProductsRepository productsRepository,
                           UserService userService,
                           OrderService orderService) {
        this.cardRepository = cardRepository;
        this.productsRepository = productsRepository;
        this.userService = userService;
        this.orderService = orderService;
    }

    @Override
    @javax.transaction.Transactional
    public Card createCard(User user, List<Long> productsIds) {
        Card card = new Card();
        card.setUser(user);
        List<Products> productsList = getCollectRefProductsByIds(productsIds);
        card.setProducts(productsList);
        return cardRepository.save(card);
    }

    private List<Products> getCollectRefProductsByIds(List<Long> productsIds) {
        return productsIds.stream()
                .map(productsRepository::getOne)
                .collect(Collectors.toList());
    }

    @Override
    @javax.transaction.Transactional
    public void addProducts(Card card, List<Long> productsIds) {
        List<Products> products = card.getProducts();
        List<Products> newProductsList = products == null? new ArrayList<>() : new ArrayList<>(products);
        newProductsList.addAll(getCollectRefProductsByIds(productsIds));
        card.setProducts(newProductsList);
        cardRepository.save(card);
    }

    @Override
    public CardDTO getCardByUserEmail(String email) {
        User user = userService.findByEmail(email);
        if (user == null || user.getCard() == null) {
            return new CardDTO();
        }

        CardDTO cardDTO = new CardDTO();
        Map<Long, CardDetailsDTO> mapByProductsIds = new HashMap<>();

        List<Products> products = user.getCard().getProducts();
        for (Products product : products) {
            CardDetailsDTO detailsDTO = mapByProductsIds.get(product.getId());
            if (detailsDTO == null) {
                mapByProductsIds.put(product.getId(), new CardDetailsDTO(product));
            }
            else {
                detailsDTO.setAmount(detailsDTO.getAmount() + 1.0);
                detailsDTO.setSumm(detailsDTO.getSumm() + product.getPrice());
            }
        }

        cardDTO.setCardDetails(new ArrayList<>(mapByProductsIds.values()));
        cardDTO.aggregate();

        return cardDTO;
    }

    @Override
    @Transactional
    public void commitCardToOrder(String email) {
        User user = userService.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found with this email: " + email);
        }
        Card card = user.getCard();
        if (card == null || card.getProducts().isEmpty()) {
            return;
        }

        Order order = new Order();
        order.setStatus(OrderStatus.NEW);
        order.setUser(user);

        Map<Products, Long> productsWithAmount = card.getProducts().stream()
                .collect(Collectors.groupingBy(products -> products, Collectors.counting()));

        List<OrderDetails> orderDetailsList = productsWithAmount.entrySet().stream()
                .map(pair -> new OrderDetails(order, pair.getKey(), pair.getValue()))
                .collect(Collectors.toList());

        BigDecimal total = new BigDecimal(orderDetailsList.stream()
                .map(detail -> detail.getPrice().multiply(detail.getAmount()))
                .mapToDouble(BigDecimal::doubleValue).sum());

        order.setDetails(orderDetailsList);
        order.setSumm(total);
        order.setAddress("none");

        orderService.saveOrder(order);
        card.getProducts().clear();
        cardRepository.save(card);
    }
}
