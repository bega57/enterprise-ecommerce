package at.fhv.ecommerce.presentation.rest.controller;

import at.fhv.ecommerce.application.user.UserService;
import at.fhv.ecommerce.domain.model.user.User;
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
    public List<User> getUsers(){
        return service.getUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return service.getUser(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return service.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user){
        return service.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        service.deleteUser(id);
    }
}