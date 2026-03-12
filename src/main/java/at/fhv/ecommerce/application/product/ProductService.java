package at.fhv.ecommerce.application.product;

import at.fhv.ecommerce.domain.model.product.Product;
import at.fhv.ecommerce.infrastructure.persistence.product.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product createProduct(Product product) {
        return repository.save(product);
    }

    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }

    public Product getProduct(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Product updateProduct(Long id, Product product) {

        Product existing = repository.findById(id).orElseThrow();

        existing.setName(product.getName());
        existing.setPrice(product.getPrice());
        existing.setStock(product.getStock());

        return repository.save(existing);
    }

}