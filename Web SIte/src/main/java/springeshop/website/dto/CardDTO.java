package springeshop.website.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardDTO {
    private Long amountProducts;
    private Double summ;
    private List<CardDetailsDTO> cardDetails = new ArrayList<>();

    public void aggregate() {
        this.amountProducts = Long.valueOf(cardDetails.size());
        this.summ = cardDetails.stream()
                .map(CardDetailsDTO::getSumm)
                .mapToDouble(Double::doubleValue)
                .sum();
    }
}
