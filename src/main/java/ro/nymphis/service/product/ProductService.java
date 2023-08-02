package ro.nymphis.service.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ro.nymphis.model.product.Product;
import ro.nymphis.service.BaseService;

import java.util.Optional;
import java.util.List;

public interface ProductService extends BaseService<Long, Product> {
    Page<Product> getProducts(Pageable pageable, List<String> searchTerms);

    Optional<Product> getProductById(final Long id);

    public void upsertProduct(Product product);
}
