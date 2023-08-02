package ro.nymphis.service.product.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import ro.nymphis.model.product.Product;
import ro.nymphis.repository.ProductRepository;
import ro.nymphis.service.product.ProductService;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    private boolean matchesSearchTerm(Product product, String searchTerm) {
        return product.getTitle().toLowerCase().matches(".*\\b" + searchTerm.toLowerCase() + "\\b.*") ||
                product.getDescription().toLowerCase().matches(".*\\b" + searchTerm.toLowerCase() + "\\b.*");
    }

    private boolean matchesSearchTerms(Product product, List<String> searchTerms) {
        for (String searchTerm : searchTerms)
            if (matchesSearchTerm(product, searchTerm)) return true;

        return false;
    }

    public Page<Product> getProducts(Pageable pageable, List<String> searchTerms) {
        if (searchTerms == null)
            return productRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "id")));

        List<Product> matchingProducts = productRepository.findAll()
                .stream()
                .filter(product -> matchesSearchTerms(product, searchTerms))
                .sorted(Comparator.comparing(a -> a.getTitle().toLowerCase()))
                .collect(Collectors.toList());

        final int start = Math.min(pageable.getPageSize() * pageable.getPageNumber(), matchingProducts.size());
        final int end = Math.min(start + pageable.getPageSize(), matchingProducts.size());

        return new PageImpl<>(matchingProducts.subList(start, end), pageable, matchingProducts.size());
    }

    @Override
    public Optional<Product> getProductById(final Long id) {
        return getProductRepository().findById(id);
    }

    @Override
    public void upsertProduct(Product product) {
        if(product == null)
            throw new IllegalArgumentException("Argument 'product' must not be null!");

        if(product.getId() != null)
            getProductRepository().findById(product.getId()).orElseThrow(() -> new RuntimeException("Product not found!"));

        getProductRepository().save(product);
    }
}
