package at.fhv.ecommerce.application.service;

import at.fhv.ecommerce.infrastructure.client.UserClient;
import at.fhv.ecommerce.presentation.ui.dto.UserDTO;
import at.fhv.ecommerce.presentation.ui.dto.UserRequestDTO;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserClient userClient;

    public UserService(UserClient userClient) {
        this.userClient = userClient;
    }

    public UserDTO[] getUsers() {
        return userClient.getAllUsers();
    }

    public UserDTO getUser(Long id) {
        return userClient.getUserById(id);
    }

    public UserDTO createUser(UserRequestDTO dto) {
        return userClient.createUser(dto);
    }

    public UserDTO updateUser(Long id, UserRequestDTO dto) {
        return userClient.updateUser(id, dto);
    }

    public void deleteUser(Long id) {
        userClient.deleteUser(id);
    }
}