package at.fhv.ecommerce.application.order;

import at.fhv.ecommerce.domain.cart.Cart;
import at.fhv.ecommerce.domain.cart.CartItem;
import at.fhv.ecommerce.domain.order.Order;
import at.fhv.ecommerce.domain.order.OrderItem;
import at.fhv.ecommerce.domain.product.Product;
import at.fhv.ecommerce.infrastructure.cart.CartRepository;
import at.fhv.ecommerce.infrastructure.order.OrderRepository;
import at.fhv.ecommerce.infrastructure.product.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(CartRepository cartRepository,
                        OrderRepository orderRepository,
                        ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public Order placeOrder(Long userId) {

        Cart cart = cartRepository.findByUser_Id(userId).orElseThrow();

        Order order = new Order();
        order.setUser(cart.getUser());
        order.setItems(new ArrayList<>());

        for (CartItem cartItem : cart.getItems()) {

            Product product = cartItem.getProduct();

            if (product.getStock() < cartItem.getQuantity()) {
                throw new RuntimeException("Not enough stock for product " + product.getId());
            }

            product.setStock(product.getStock() - cartItem.getQuantity());

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(cartItem.getQuantity());

            order.getItems().add(orderItem);
        }

        cart.getItems().clear();

        return orderRepository.save(order);
    }

    public List<Order> getOrdersByUser(Long userId) {
        return orderRepository.findByUser_Id(userId);
    }

    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow();
    }
}