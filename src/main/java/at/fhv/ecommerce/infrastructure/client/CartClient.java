package at.fhv.ecommerce.infrastructure.client;

import at.fhv.ecommerce.presentation.ui.dto.AddItemRequestDTO;
import at.fhv.ecommerce.presentation.ui.dto.CartDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CartClient {

    private final RestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8083/carts";

    public CartClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CartDTO getCart(Long userId) {
        return restTemplate.getForObject(BASE_URL + "/" + userId, CartDTO.class);
    }

    public CartDTO addItem(Long userId, AddItemRequestDTO request) {
        return restTemplate.postForObject(
                BASE_URL + "/" + userId + "/items",
                request,
                CartDTO.class
        );
    }

    public void removeItem(Long cartId, Long productId) {
        restTemplate.delete(BASE_URL + "/" + cartId + "/items/" + productId);
    }
}