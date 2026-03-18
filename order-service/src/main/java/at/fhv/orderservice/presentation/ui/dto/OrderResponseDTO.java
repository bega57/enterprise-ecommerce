package at.fhv.orderservice.presentation.ui.dto;

import java.util.List;

public class OrderResponseDTO {

    private Long id;
    private Long userId;
    private List<OrderItemDTO> items;

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }
}