package at.fhv.orderservice.infrastructure.client;

import at.fhv.orderservice.presentation.ui.dto.ProductDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public ProductDTO getProduct(Long id) {
        String url = "http://localhost:8082/products/" + id;
        return restTemplate.getForObject(url, ProductDTO.class);
    }
}