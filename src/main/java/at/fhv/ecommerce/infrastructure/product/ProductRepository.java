package at.fhv.ecommerce.infrastructure.product;

import at.fhv.ecommerce.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}