package at.fhv.orderservice.infrastructure.persistence.cart;

import at.fhv.orderservice.domain.model.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUserId(Long userId);

}