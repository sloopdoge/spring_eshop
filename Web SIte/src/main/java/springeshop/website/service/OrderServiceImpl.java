package springeshop.website.service;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import springeshop.website.config.OrderIntegrationConfig;
import springeshop.website.domain.Order;
import springeshop.website.dto.OrderIntegrationDTO;
import springeshop.website.repository.OrderRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final OrderIntegrationConfig integrationConfig;

    public OrderServiceImpl(OrderRepository orderRepository, OrderIntegrationConfig integrationConfig) {
        this.orderRepository = orderRepository;
        this.integrationConfig = integrationConfig;
    }

    @Override
    @Transactional
    public void saveOrder(Order order) {
        Order savedOrder = orderRepository.save(order);
        sendIntegrationNotify(savedOrder);
    }

    private void sendIntegrationNotify(Order order) {
        OrderIntegrationDTO orderIntegrationDTO = new OrderIntegrationDTO();
        orderIntegrationDTO.setEmail(order.getUser().getEmail());
        orderIntegrationDTO.setAddress(order.getAddress());
        orderIntegrationDTO.setOrderId(order.getId());

        List<OrderIntegrationDTO.OrderDetailsDTO> detailsDTOS = order.getDetails().stream()
                .map(OrderIntegrationDTO.OrderDetailsDTO::new).collect(Collectors.toList());

        orderIntegrationDTO.setDetailsDTOS(detailsDTOS);

        Message<OrderIntegrationDTO> message = MessageBuilder.withPayload(orderIntegrationDTO)
                .setHeader("Content-type", "application/json")
                .build();

        integrationConfig.getOrderChannel().send(message);

    }

    @Override
    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
}
