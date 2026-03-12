package at.fhv.ecommerce.presentation.ui.mapper;

import at.fhv.ecommerce.domain.model.order.Order;
import at.fhv.ecommerce.domain.model.order.OrderItem;
import at.fhv.ecommerce.presentation.ui.dto.OrderResponseDTO;
import at.fhv.ecommerce.presentation.ui.dto.OrderItemDTO;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderResponseDTO toDTO(Order order) {

        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setId(order.getId());
        dto.setUserId(order.getUser().getId());

        List<OrderItemDTO> items = order.getItems()
                .stream()
                .map(OrderMapper::toItemDTO)
                .collect(Collectors.toList());

        dto.setItems(items);

        return dto;
    }

    private static OrderItemDTO toItemDTO(OrderItem item) {

        OrderItemDTO dto = new OrderItemDTO();
        dto.setProductId(item.getProduct().getId());
        dto.setProductName(item.getProduct().getName());
        dto.setQuantity(item.getQuantity());

        return dto;
    }
}
