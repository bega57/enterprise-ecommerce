package at.fhv.ecommerce.infrastructure.persistence.order;

import at.fhv.ecommerce.domain.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser_Id(Long userId);

}
