package springeshop.website.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import springeshop.website.domain.Products;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardDetailsDTO {
    private String title;
    private Long productsId;
    private Double price;
    private Double amount;
    private Double summ;

    public CardDetailsDTO(Products products) {
        this.title = products.getTitle();
        this.productsId = products.getId();
        this.price = products.getPrice();
        this.amount = 1.0;
        this.summ = products.getPrice();

    }
}
