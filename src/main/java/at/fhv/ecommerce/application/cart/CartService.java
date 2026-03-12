package at.fhv.ecommerce.application.cart;

import at.fhv.ecommerce.domain.model.cart.Cart;
import at.fhv.ecommerce.domain.model.cart.CartItem;
import at.fhv.ecommerce.domain.model.product.Product;
import at.fhv.ecommerce.domain.model.user.User;
import at.fhv.ecommerce.infrastructure.persistence.cart.CartRepository;
import at.fhv.ecommerce.infrastructure.persistence.product.ProductRepository;
import org.springframework.stereotype.Service;
import at.fhv.ecommerce.infrastructure.persistence.user.UserRepository;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository,
                       ProductRepository productRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public Cart getCart(Long id){
        return cartRepository.findById(id).orElseThrow();
    }

    public Cart addProduct(Long userId, Long productId, int quantity){

        Cart cart = getCartByUser(userId);
        Product product = productRepository.findById(productId).orElseThrow();

        CartItem item = new CartItem();
        item.setProduct(product);
        item.setQuantity(quantity);

        cart.getItems().add(item);

        return cartRepository.save(cart);
    }

    public Cart removeProduct(Long cartId, Long productId){

        Cart cart = cartRepository.findById(cartId).orElseThrow();

        cart.getItems().removeIf(
                item -> item.getProduct().getId().equals(productId)
        );

        return cartRepository.save(cart);
    }

    public Cart getCartByUser(Long userId){

        return cartRepository.findByUser_Id(userId)
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    User user = userRepository.findById(userId).orElseThrow();
                    cart.setUser(user);
                    return cartRepository.save(cart);
                });
    }
}
