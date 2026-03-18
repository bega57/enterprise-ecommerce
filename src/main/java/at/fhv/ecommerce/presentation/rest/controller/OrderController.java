package at.fhv.ecommerce.presentation.rest.controller;

import at.fhv.ecommerce.application.service.OrderService;
import at.fhv.ecommerce.presentation.ui.dto.OrderResponseDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping("/{userId}")
    public OrderResponseDTO placeOrder(@PathVariable Long userId) {
        return service.placeOrder(userId);
    }

    @GetMapping("/user/{userId}")
    public OrderResponseDTO[] getOrdersByUser(@PathVariable Long userId) {
        return service.getOrdersByUser(userId);
    }

    @GetMapping("/{orderId}")
    public OrderResponseDTO getOrder(@PathVariable Long orderId) {
        return service.getOrder(orderId);
    }
}