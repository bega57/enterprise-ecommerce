package at.fhv.ecommerce.application.user;

import at.fhv.ecommerce.domain.user.User;
import at.fhv.ecommerce.infrastructure.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository){
        this.repository = repository;
    }

    public List<User> getUsers(){
        return repository.findAll();
    }

    public User createUser(User user){
        return repository.save(user);
    }

    public User getUser(Long id){
        return repository.findById(id).orElseThrow();
    }

    public User updateUser(Long id, User user){
        User existing = repository.findById(id).orElseThrow();

        existing.setName(user.getName());
        existing.setEmail(user.getEmail());

        return repository.save(existing);
    }

    public void deleteUser(Long id){
        repository.deleteById(id);
    }
}