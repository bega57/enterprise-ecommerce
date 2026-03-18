package at.fhv.orderservice.presentation.rest.controller;

import at.fhv.orderservice.application.order.OrderService;
import at.fhv.orderservice.presentation.ui.dto.OrderResponseDTO;
import at.fhv.orderservice.presentation.ui.mapper.OrderMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping("/{userId}")
    public OrderResponseDTO placeOrder(@PathVariable Long userId) {
        return OrderMapper.toDTO(service.placeOrder(userId));
    }

    @GetMapping("/user/{userId}")
    public List<OrderResponseDTO> getOrdersByUser(@PathVariable Long userId) {

        return service.getOrdersByUser(userId)
                .stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{orderId}")
    public OrderResponseDTO getOrder(@PathVariable Long orderId) {
        return OrderMapper.toDTO(service.getOrder(orderId));
    }
}