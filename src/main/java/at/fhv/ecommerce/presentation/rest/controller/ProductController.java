package at.fhv.ecommerce.presentation.rest.controller;

import at.fhv.ecommerce.application.product.ProductService;
import at.fhv.ecommerce.domain.model.product.Product;
import at.fhv.ecommerce.presentation.ui.dto.ProductRequestDTO;
import at.fhv.ecommerce.presentation.ui.dto.ProductResponseDTO;
import at.fhv.ecommerce.presentation.ui.mapper.ProductMapper;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProductResponseDTO> getProducts() {
        return service.getAllProducts()
                .stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ProductResponseDTO createProduct(@Valid @RequestBody ProductRequestDTO dto) {

        Product product = ProductMapper.toEntity(dto);

        Product saved = service.createProduct(product);

        return ProductMapper.toDTO(saved);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
    }

    @GetMapping("/{id}")
    public ProductResponseDTO getProduct(@PathVariable Long id) {
        return ProductMapper.toDTO(service.getProduct(id));
    }

    @PutMapping("/{id}")
    public ProductResponseDTO updateProduct(@PathVariable Long id,
                                            @Valid @RequestBody ProductRequestDTO dto) {

        Product product = ProductMapper.toEntity(dto);

        Product updated = service.updateProduct(id, product);

        return ProductMapper.toDTO(updated);
    }
}