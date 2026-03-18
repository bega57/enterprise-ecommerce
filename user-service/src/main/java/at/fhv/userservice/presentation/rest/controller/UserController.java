package at.fhv.userservice.presentation.rest.controller;

import at.fhv.userservice.application.user.UserService;
import at.fhv.userservice.domain.model.user.User;
import at.fhv.userservice.presentation.ui.dto.UserRequestDTO;
import at.fhv.userservice.presentation.ui.dto.UserResponseDTO;
import at.fhv.userservice.presentation.ui.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    @GetMapping
    public List<UserResponseDTO> getUsers(){
        return service.getUsers()
                .stream()
                .map(UserMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUser(@PathVariable Long id){
        return UserMapper.toDTO(service.getUser(id));
    }

    @PostMapping
    public UserResponseDTO createUser(@RequestBody UserRequestDTO dto){
        User user = UserMapper.toEntity(dto);
        return UserMapper.toDTO(service.createUser(user));
    }

    @PutMapping("/{id}")
    public UserResponseDTO updateUser(@PathVariable Long id,
                                      @RequestBody UserRequestDTO dto){
        User user = UserMapper.toEntity(dto);
        return UserMapper.toDTO(service.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        service.deleteUser(id);
    }
}