package at.fhv.ecommerce.application.service;

import at.fhv.ecommerce.infrastructure.client.OrderClient;
import org.springframework.stereotype.Service;
import at.fhv.ecommerce.presentation.ui.dto.OrderResponseDTO;

@Service
public class OrderService {

    private final OrderClient orderClient;

    public OrderService(OrderClient orderClient) {
        this.orderClient = orderClient;
    }

    public OrderResponseDTO placeOrder(Long userId) {
        return orderClient.placeOrder(userId);
    }

    public OrderResponseDTO[] getOrdersByUser(Long userId) {
        return orderClient.getOrdersByUser(userId);
    }

    public OrderResponseDTO getOrder(Long orderId) {
        return orderClient.getOrderById(orderId);
    }
}