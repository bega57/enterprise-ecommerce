package at.fhv.ecommerce.infrastructure.persistence.user;

import at.fhv.ecommerce.domain.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}