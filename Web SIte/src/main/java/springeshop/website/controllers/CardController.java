package springeshop.website.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import springeshop.website.dto.CardDTO;
import springeshop.website.service.CardService;

import java.security.Principal;

@Controller
public class CardController {
    private final CardService cardService;

    public CardController (CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/card")
    public String card (Model model, Principal principal) {
        model.addAttribute("title", "Card");

        if (principal == null) {
            model.addAttribute("card", new CardDTO());
        }
        else {
            CardDTO cardDTO = cardService.getCardByUserEmail(principal.getName());
            model.addAttribute("card", cardDTO);
        }

        return "card";
    }

    @PostMapping("/card")
    public String commitCard (Principal principal) {
        if (principal == null) {
            cardService.commitCardToOrder(principal.getName());
        }
        return "redirect:/card";
    }
}
