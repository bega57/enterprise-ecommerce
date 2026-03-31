package at.fhv.ecommerce.infrastructure.client;

import at.fhv.ecommerce.presentation.ui.dto.OrderResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrderClient {

    private final RestTemplate restTemplate;
    private final String BASE_URL = "http://order-service/orders";

    public OrderClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public OrderResponseDTO placeOrder(Long userId) {
        return restTemplate.postForObject(
                BASE_URL + "/" + userId,
                null,
                OrderResponseDTO.class
        );
    }

    public OrderResponseDTO[] getOrdersByUser(Long userId) {
        return restTemplate.getForObject(
                BASE_URL + "/user/" + userId,
                OrderResponseDTO[].class
        );
    }

    public OrderResponseDTO getOrderById(Long orderId) {
        return restTemplate.getForObject(
                BASE_URL + "/" + orderId,
                OrderResponseDTO.class
        );
    }
}