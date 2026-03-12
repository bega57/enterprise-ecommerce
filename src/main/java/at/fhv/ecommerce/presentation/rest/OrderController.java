package at.fhv.ecommerce.presentation.rest;

import at.fhv.ecommerce.application.order.OrderService;
import at.fhv.ecommerce.domain.order.Order;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping("/{userId}")
    public Order placeOrder(@PathVariable Long userId) {
        return service.placeOrder(userId);
    }

    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUser(@PathVariable Long userId) {
        return service.getOrdersByUser(userId);
    }

    @GetMapping("/{orderId}")
    public Order getOrder(@PathVariable Long orderId) {
        return service.getOrder(orderId);
    }
}