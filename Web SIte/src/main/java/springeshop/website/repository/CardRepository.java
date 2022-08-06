package springeshop.website.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springeshop.website.domain.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
}
