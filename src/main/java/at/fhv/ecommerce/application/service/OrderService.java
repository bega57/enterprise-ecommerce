package at.fhv.ecommerce.application.service;

import at.fhv.ecommerce.infrastructure.client.OrderClient;
import at.fhv.ecommerce.presentation.ui.dto.OrderDTO;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderClient orderClient;

    public OrderService(OrderClient orderClient) {
        this.orderClient = orderClient;
    }

    public OrderDTO placeOrder(Long userId) {
        return orderClient.placeOrder(userId);
    }

    public OrderDTO[] getOrdersByUser(Long userId) {
        return orderClient.getOrdersByUser(userId);
    }

    public OrderDTO getOrder(Long orderId) {
        return orderClient.getOrderById(orderId);
    }
}