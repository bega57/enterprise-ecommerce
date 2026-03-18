package at.fhv.ecommerce.presentation.rest.controller;

import at.fhv.ecommerce.application.service.UserService;
import at.fhv.ecommerce.presentation.ui.dto.UserDTO;
import at.fhv.ecommerce.presentation.ui.dto.UserRequestDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public UserDTO[] getUsers() {
        return service.getUsers();
    }

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable Long id) {
        return service.getUser(id);
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserRequestDTO dto) {
        return service.createUser(dto);
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id,
                              @RequestBody UserRequestDTO dto) {
        return service.updateUser(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
    }
}