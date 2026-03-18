package at.fhv.orderservice.infrastructure.client;

import at.fhv.orderservice.presentation.ui.dto.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public UserDTO getUser(Long id) {
        String url = "http://localhost:8081/users/" + id;
        return restTemplate.getForObject(url, UserDTO.class);
    }
}