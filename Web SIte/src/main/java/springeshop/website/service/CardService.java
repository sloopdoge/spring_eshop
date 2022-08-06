package springeshop.website.service;

import springeshop.website.domain.Card;
import springeshop.website.domain.User;
import springeshop.website.dto.CardDTO;

import java.util.List;

public interface CardService {
    Card createCard(User user, List<Long> productsIds);

    void addProducts(Card card, List<Long> productsIds);

    CardDTO getCardByUserEmail(String email);

    void commitCardToOrder(String email);
}
