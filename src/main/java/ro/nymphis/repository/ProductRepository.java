package ro.nymphis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.nymphis.model.product.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
