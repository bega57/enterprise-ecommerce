package at.fhv.ecommerce.presentation.ui.mapper;

import at.fhv.ecommerce.domain.model.product.Product;
import at.fhv.ecommerce.presentation.ui.dto.ProductRequestDTO;
import at.fhv.ecommerce.presentation.ui.dto.ProductResponseDTO;

public class ProductMapper {

    public static Product toEntity(ProductRequestDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        return product;
    }

    public static ProductResponseDTO toDTO(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        return dto;
    }
}
