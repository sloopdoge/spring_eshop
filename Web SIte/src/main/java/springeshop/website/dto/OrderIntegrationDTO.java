package springeshop.website.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import springeshop.website.domain.OrderDetails;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderIntegrationDTO {
    private Long orderId;
    private String email;
    private String address;
    private List<OrderDetailsDTO> detailsDTOS;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderDetailsDTO {
        private String title;
        private Double price;
        private Double amount;
        private Double summ;

        public OrderDetailsDTO(OrderDetails orderDetails) {
            this.title = orderDetails.getProducts().getTitle();
            this.price = orderDetails.getPrice().doubleValue();
            this.amount = orderDetails.getAmount().doubleValue();
            this.summ = orderDetails.getPrice().multiply(orderDetails.getAmount()).doubleValue();
        }
    }
}
