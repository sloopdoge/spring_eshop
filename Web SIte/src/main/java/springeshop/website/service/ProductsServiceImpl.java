package springeshop.website.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import springeshop.website.domain.Card;
import springeshop.website.domain.Products;
import springeshop.website.domain.User;
import springeshop.website.dto.ProductDTO;
import springeshop.website.mapper.ProductsMapper;
import springeshop.website.repository.ProductsRepository;

import java.util.Collections;
import java.util.List;

@Service
public class ProductsServiceImpl implements ProductsService {

    private final ProductsMapper mapper = ProductsMapper.MAPPER;

    private final ProductsRepository productsRepository;
    private final UserService userService;
    private final CardService cardService;
    private final SimpMessagingTemplate template;

    public ProductsServiceImpl(ProductsRepository productsRepository,
                               UserService userService, CardService cardService,
                               SimpMessagingTemplate template) {
        this.productsRepository = productsRepository;
        this.userService = userService;
        this.cardService = cardService;
        this.template = template;
    }

    @Override
    @javax.transaction.Transactional
    public void addToUserCard(Long productsId, String email) {
        User user = userService.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found with this email: " + email);
        }

        Card card = user.getCard();
        if (card == null) {
            Card newCard = cardService.createCard(user, Collections.singletonList(productsId));
            user.setCard(newCard);
            userService.save(user);
        }
        else {
            cardService.addProducts(card, Collections.singletonList(productsId));
        }
    }

    @Override
    public List<ProductDTO> getAll() {
        return mapper.fromProductsList(productsRepository.findAll());
    }

    @Override
    public void addProduct(ProductDTO productDTO) {
        Products products = mapper.toProduct(productDTO);
        Products savedProducts = productsRepository.save(products);

        template.convertAndSend("/products",
                ProductsMapper.MAPPER.fromProducts(savedProducts));
    }

    @Override
    public ProductDTO getById(Long id) {
        Products products = productsRepository.findById(id).orElse(new Products());
        return ProductsMapper.MAPPER.fromProducts(products);
    }
}
