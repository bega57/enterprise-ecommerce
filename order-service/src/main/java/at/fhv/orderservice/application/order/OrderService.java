package at.fhv.orderservice.application.order;

import at.fhv.orderservice.application.event.OrderCreatedEvent;
import at.fhv.orderservice.domain.model.cart.Cart;
import at.fhv.orderservice.domain.model.cart.CartItem;
import at.fhv.orderservice.domain.model.order.Order;
import at.fhv.orderservice.domain.model.order.OrderItem;
import at.fhv.orderservice.infrastructure.client.ProductClient;
import at.fhv.orderservice.infrastructure.client.UserClient;
import at.fhv.orderservice.infrastructure.persistence.cart.CartRepository;
import at.fhv.orderservice.infrastructure.persistence.order.OrderRepository;
import at.fhv.orderservice.presentation.ui.dto.OrderItemDTO;
import at.fhv.orderservice.presentation.ui.dto.OrderResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.cloud.stream.function.StreamBridge;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class OrderService {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final ProductClient productClient;
    private final UserClient userClient;
    private final StreamBridge streamBridge;

    public OrderService(CartRepository cartRepository,
                        OrderRepository orderRepository,
                        ProductClient productClient,
                        UserClient userClient,
                        StreamBridge streamBridge) {

        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.productClient = productClient;
        this.userClient = userClient;
        this.streamBridge = streamBridge;
    }

    public OrderResponseDTO placeOrder(Long userId) {

        userClient.getUser(userId);

        Cart cart = cartRepository.findByUserId(userId).orElseThrow();

        Order order = new Order();
        order.setUserId(userId);
        order.setItems(new ArrayList<>());
        order.setStatus("PENDING");

        for (CartItem cartItem : cart.getItems()) {

            OrderItem item = new OrderItem();
            item.setProductId(cartItem.getProductId());
            item.setQuantity(cartItem.getQuantity());

            order.getItems().add(item);
        }

        cart.getItems().clear();

        Order savedOrder = orderRepository.save(order);

        OrderCreatedEvent event = new OrderCreatedEvent();
        event.orderId = savedOrder.getId();
        event.products = new HashMap<>();

        for (OrderItem item : savedOrder.getItems()) {
            event.products.put(item.getProductId(), item.getQuantity());
        }

        streamBridge.send("orderCreated-out-0", event);

        return mapToDTO(savedOrder);
    }

    public List<OrderResponseDTO> getOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public OrderResponseDTO getOrder(Long orderId) {
        return mapToDTO(orderRepository.findById(orderId).orElseThrow());
    }

    private OrderResponseDTO mapToDTO(Order order) {

        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setId(order.getId());
        dto.setUserId(order.getUserId());
        dto.setStatus(order.getStatus());

        List<OrderItemDTO> items = order.getItems().stream().map(item -> {
            OrderItemDTO itemDTO = new OrderItemDTO();
            itemDTO.setProductId(item.getProductId());
            itemDTO.setQuantity(item.getQuantity());

            var product = productClient.getProduct(item.getProductId());
            itemDTO.setProductName(product.getName());

            return itemDTO;
        }).toList();

        dto.setItems(items);

        return dto;
    }
}