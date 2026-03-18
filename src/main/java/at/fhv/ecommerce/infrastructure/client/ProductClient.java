package at.fhv.ecommerce.infrastructure.client;

import at.fhv.ecommerce.presentation.ui.dto.ProductDTO;
import at.fhv.ecommerce.presentation.ui.dto.ProductRequestDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductClient {

    private final RestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8082/products";

    public ProductClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ProductDTO[] getAllProducts() {
        return restTemplate.getForObject(BASE_URL, ProductDTO[].class);
    }

    public ProductDTO getProductById(Long id) {
        return restTemplate.getForObject(BASE_URL + "/" + id, ProductDTO.class);
    }

    public ProductDTO createProduct(ProductRequestDTO dto) {
        return restTemplate.postForObject(BASE_URL, dto, ProductDTO.class);
    }

    public ProductDTO updateProduct(Long id, ProductRequestDTO dto) {
        restTemplate.put(BASE_URL + "/" + id, dto);
        return getProductById(id);
    }

    public void deleteProduct(Long id) {
        restTemplate.delete(BASE_URL + "/" + id);
    }
}