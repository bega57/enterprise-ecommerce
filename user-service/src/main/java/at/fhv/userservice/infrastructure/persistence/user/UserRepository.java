package at.fhv.userservice.infrastructure.persistence.user;

import at.fhv.userservice.domain.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}