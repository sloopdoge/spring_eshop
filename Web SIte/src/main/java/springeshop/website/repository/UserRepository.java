package springeshop.website.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springeshop.website.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByEmail(String email);
}
