package at.fhv.ecommerce.infrastructure.persistence.product;

import at.fhv.ecommerce.domain.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}