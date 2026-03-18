package at.fhv.ecommerce.application.service;

import at.fhv.ecommerce.infrastructure.client.CartClient;
import at.fhv.ecommerce.presentation.ui.dto.AddItemRequestDTO;
import at.fhv.ecommerce.presentation.ui.dto.CartDTO;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final CartClient cartClient;

    public CartService(CartClient cartClient) {
        this.cartClient = cartClient;
    }

    public CartDTO getCart(Long userId) {
        return cartClient.getCart(userId);
    }

    public CartDTO addItem(Long userId, AddItemRequestDTO request) {
        return cartClient.addItem(userId, request);
    }

    public void removeItem(Long cartId, Long productId) {
        cartClient.removeItem(cartId, productId);
    }
}