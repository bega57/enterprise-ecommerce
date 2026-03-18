package at.fhv.orderservice.presentation.rest.controller;

import at.fhv.orderservice.application.cart.CartService;
import at.fhv.orderservice.presentation.ui.dto.AddItemRequestDTO;
import at.fhv.orderservice.domain.model.cart.Cart;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService service;

    public CartController(CartService service){
        this.service = service;
    }

    @GetMapping("/{userId}")
    public Cart getCart(@PathVariable Long userId){
        return service.getCartByUser(userId);
    }

    @PostMapping("/{userId}/items")
    public Cart addItem(@PathVariable Long userId,
                        @Valid @RequestBody AddItemRequestDTO request){
        return service.addProduct(
                userId,
                request.getProductId(),
                request.getQuantity()
        );
    }

    @DeleteMapping("/{cartId}/items/{productId}")
    public Cart removeItem(@PathVariable Long cartId,
                           @PathVariable Long productId){
        return service.removeProduct(cartId, productId);
    }
}