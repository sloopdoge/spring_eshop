package springeshop.website.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springeshop.website.domain.Products;

public interface ProductsRepository extends JpaRepository<Products, Long> {
}
