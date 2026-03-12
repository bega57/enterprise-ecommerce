package at.fhv.ecommerce.infrastructure.user;

import at.fhv.ecommerce.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}