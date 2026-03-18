package at.fhv.productservice.infrastructure.persistence.product;

import at.fhv.productservice.domain.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}