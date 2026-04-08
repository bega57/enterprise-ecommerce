package at.fhv.productservice.application.product;

import org.springframework.stereotype.Service;
import at.fhv.productservice.domain.model.product.Product;
import at.fhv.productservice.infrastructure.persistence.product.ProductRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final Counter productCreatedCounter;
    private final Counter stockUpdateCounter;

    public ProductService(ProductRepository repository, MeterRegistry registry) {
        this.repository = repository;
        this.productCreatedCounter = registry.counter("products.created");
        this.stockUpdateCounter = registry.counter("products.stock.updated");
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product createProduct(Product product) {
        productCreatedCounter.increment();
        return repository.save(product);
    }

    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }

    public Product getProduct(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Product updateProduct(Long id, Product product) {
        stockUpdateCounter.increment();

        Product existing = repository.findById(id).orElseThrow();

        existing.setName(product.getName());
        existing.setPrice(product.getPrice());
        existing.setStock(product.getStock());

        return repository.save(existing);
    }

}