package at.fhv.ecommerce.presentation.ui.dto;

import java.util.List;

public class CartDTO {
    public Long id;
    public Long userId;
    public List<OrderItemDTO> items;
}