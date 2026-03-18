package at.fhv.orderservice.application.cart;

import at.fhv.orderservice.domain.model.cart.Cart;
import at.fhv.orderservice.domain.model.cart.CartItem;
import at.fhv.orderservice.infrastructure.client.ProductClient;
import at.fhv.orderservice.infrastructure.client.UserClient;
import at.fhv.orderservice.infrastructure.persistence.cart.CartRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductClient productClient;
    private final UserClient userClient;

    public CartService(CartRepository cartRepository,
                       ProductClient productClient,
                       UserClient userClient) {
        this.cartRepository = cartRepository;
        this.productClient = productClient;
        this.userClient = userClient;
    }

    public Cart getCart(Long id){
        return cartRepository.findById(id).orElseThrow();
    }

    public Cart addProduct(Long userId, Long productId, int quantity){

        userClient.getUser(userId);
        productClient.getProduct(productId);

        Cart cart = getCartByUser(userId);

        CartItem item = new CartItem();
        item.setProductId(productId);
        item.setQuantity(quantity);

        cart.getItems().add(item);

        return cartRepository.save(cart);
    }

    public Cart removeProduct(Long cartId, Long productId){

        Cart cart = cartRepository.findById(cartId).orElseThrow();

        cart.getItems().removeIf(
                item -> item.getProductId().equals(productId)
        );

        return cartRepository.save(cart);
    }

    public Cart getCartByUser(Long userId){

        return cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    cart.setUserId(userId);
                    return cartRepository.save(cart);
                });
    }
}