package at.fhv.orderservice.presentation.ui.mapper;

import at.fhv.orderservice.domain.model.order.Order;
import at.fhv.orderservice.domain.model.order.OrderItem;
import at.fhv.orderservice.presentation.ui.dto.OrderItemDTO;
import at.fhv.orderservice.presentation.ui.dto.OrderResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderResponseDTO toDTO(Order order) {

        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setId(order.getId());
        dto.setUserId(order.getUserId());

        List<OrderItemDTO> items = order.getItems()
                .stream()
                .map(OrderMapper::toItemDTO)
                .collect(Collectors.toList());

        dto.setItems(items);

        return dto;
    }

    private static OrderItemDTO toItemDTO(OrderItem item) {

        OrderItemDTO dto = new OrderItemDTO();
        dto.setProductId(item.getProductId());
        dto.setQuantity(item.getQuantity());

        return dto;
    }
}