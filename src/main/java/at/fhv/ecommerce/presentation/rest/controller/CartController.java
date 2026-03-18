package at.fhv.ecommerce.presentation.rest.controller;

import at.fhv.ecommerce.application.service.CartService;
import at.fhv.ecommerce.presentation.ui.dto.AddItemRequestDTO;
import at.fhv.ecommerce.presentation.ui.dto.CartDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService service;

    public CartController(CartService service) {
        this.service = service;
    }

    @GetMapping("/{userId}")
    public CartDTO getCart(@PathVariable Long userId) {
        return service.getCart(userId);
    }

    @PostMapping("/{userId}/items")
    public CartDTO addItem(@PathVariable Long userId,
                           @RequestBody AddItemRequestDTO request) {
        return service.addItem(userId, request);
    }

    @DeleteMapping("/{cartId}/items/{productId}")
    public void removeItem(@PathVariable Long cartId,
                           @PathVariable Long productId) {
        service.removeItem(cartId, productId);
    }
}