package at.fhv.ecommerce.infrastructure.client;

import at.fhv.ecommerce.presentation.ui.dto.UserDTO;
import at.fhv.ecommerce.presentation.ui.dto.UserRequestDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserClient {

    private final RestTemplate restTemplate;
    private final String BASE_URL = "http://user-service/users";

    public UserClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDTO[] getAllUsers() {
        return restTemplate.getForObject(BASE_URL, UserDTO[].class);
    }

    public UserDTO getUserById(Long id) {
        return restTemplate.getForObject(BASE_URL + "/" + id, UserDTO.class);
    }

    public UserDTO createUser(UserRequestDTO dto) {
        return restTemplate.postForObject(BASE_URL, dto, UserDTO.class);
    }

    public UserDTO updateUser(Long id, UserRequestDTO dto) {
        restTemplate.put(BASE_URL + "/" + id, dto);
        return getUserById(id);
    }

    public void deleteUser(Long id) {
        restTemplate.delete(BASE_URL + "/" + id);
    }
}