package at.fhv.ecommerce.infrastructure.client;

import at.fhv.ecommerce.presentation.ui.dto.OrderDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrderClient {

    private final RestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8083/orders";

    public OrderClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public OrderDTO placeOrder(Long userId) {
        return restTemplate.postForObject(
                BASE_URL + "/" + userId,
                null,
                OrderDTO.class
        );
    }

    public OrderDTO[] getOrdersByUser(Long userId) {
        return restTemplate.getForObject(
                BASE_URL + "/user/" + userId,
                OrderDTO[].class
        );
    }

    public OrderDTO getOrderById(Long orderId) {
        return restTemplate.getForObject(
                BASE_URL + "/" + orderId,
                OrderDTO.class
        );
    }
}