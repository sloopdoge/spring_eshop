package springeshop.website.config;


import org.mapstruct.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.channel.DirectChannel;

@Configuration
@ImportResource("classpath:/integration/http_orders_integration.xml")
public class OrderIntegrationConfig {

    private DirectChannel ordersChannel;

    public OrderIntegrationConfig(@Qualifier("ordersChannel") DirectChannel ordersChannel) {
        this.ordersChannel = ordersChannel;
    }

    public DirectChannel getOrderChannel() {
        return ordersChannel;
    }
}
