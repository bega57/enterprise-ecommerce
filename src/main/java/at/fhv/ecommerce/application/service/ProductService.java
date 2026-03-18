package at.fhv.ecommerce.application.service;

import at.fhv.ecommerce.infrastructure.client.ProductClient;
import at.fhv.ecommerce.presentation.ui.dto.ProductDTO;
import at.fhv.ecommerce.presentation.ui.dto.ProductRequestDTO;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductClient productClient;

    public ProductService(ProductClient productClient) {
        this.productClient = productClient;
    }

    public ProductDTO[] getAllProducts() {
        return productClient.getAllProducts();
    }

    public ProductDTO getProduct(Long id) {
        return productClient.getProductById(id);
    }

    public ProductDTO createProduct(ProductRequestDTO dto) {
        return productClient.createProduct(dto);
    }

    public void deleteProduct(Long id) {
        productClient.deleteProduct(id);
    }

    public ProductDTO updateProduct(Long id, ProductRequestDTO dto) {
        return productClient.updateProduct(id, dto);
    }
}