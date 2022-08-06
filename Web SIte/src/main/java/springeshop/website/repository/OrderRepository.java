package springeshop.website.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springeshop.website.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
