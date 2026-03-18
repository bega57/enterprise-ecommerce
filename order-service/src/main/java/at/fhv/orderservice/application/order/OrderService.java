package at.fhv.orderservice.application.order;

import at.fhv.orderservice.domain.model.cart.Cart;
import at.fhv.orderservice.domain.model.cart.CartItem;
import at.fhv.orderservice.domain.model.order.Order;
import at.fhv.orderservice.domain.model.order.OrderItem;
import at.fhv.orderservice.infrastructure.client.ProductClient;
import at.fhv.orderservice.infrastructure.client.UserClient;
import at.fhv.orderservice.infrastructure.persistence.cart.CartRepository;
import at.fhv.orderservice.infrastructure.persistence.order.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final ProductClient productClient;
    private final UserClient userClient;

    public OrderService(CartRepository cartRepository,
                        OrderRepository orderRepository,
                        ProductClient productClient,
                        UserClient userClient) {
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.productClient = productClient;
        this.userClient = userClient;
    }

    public Order placeOrder(Long userId) {

        userClient.getUser(userId);

        Cart cart = cartRepository.findByUserId(userId).orElseThrow();

        Order order = new Order();
        order.setUserId(userId);
        order.setItems(new ArrayList<>());

        for (CartItem cartItem : cart.getItems()) {

            var product = productClient.getProduct(cartItem.getProductId());

            if (product.getStock() < cartItem.getQuantity()) {
                throw new RuntimeException("Not enough stock");
            }

            OrderItem item = new OrderItem();
            item.setProductId(cartItem.getProductId());
            item.setQuantity(cartItem.getQuantity());

            order.getItems().add(item);
        }

        cart.getItems().clear();

        return orderRepository.save(order);
    }

    public List<Order> getOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow();
    }
}