package at.fhv.ecommerce.presentation.rest;

import at.fhv.ecommerce.application.cart.CartService;
import at.fhv.ecommerce.presentation.dto.AddItemRequestDTO;
import at.fhv.ecommerce.domain.cart.Cart;
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
                        @RequestBody AddItemRequestDTO request){
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