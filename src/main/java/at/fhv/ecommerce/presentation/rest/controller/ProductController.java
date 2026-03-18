package at.fhv.ecommerce.presentation.rest.controller;

import at.fhv.ecommerce.application.service.ProductService;
import at.fhv.ecommerce.presentation.ui.dto.ProductDTO;
import at.fhv.ecommerce.presentation.ui.dto.ProductRequestDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ProductDTO[] getProducts() {
        return service.getAllProducts();
    }

    @PostMapping
    public ProductDTO createProduct(@RequestBody ProductRequestDTO dto) {
        return service.createProduct(dto);
    }

    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable Long id) {
        return service.getProduct(id);
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id,
                                    @RequestBody ProductRequestDTO dto) {
        return service.updateProduct(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
    }
}