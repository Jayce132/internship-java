package ro.nymphis.controller.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ro.nymphis.dto.pagination.PaginatedResponseDTO;
import ro.nymphis.dto.product.ProductResponseDTO;
import ro.nymphis.exception.ProductNotFoundException;
import ro.nymphis.mapper.Mapper;
import ro.nymphis.model.product.Product;
import ro.nymphis.service.product.ProductService;

import java.util.List;

@Getter
@RequiredArgsConstructor
@Controller
@RequestMapping("products")
public class ProductController {
    private final Mapper<Product, ProductResponseDTO> productToProductResponseDTOMapper;
    private final ProductService productService;

    @GetMapping
    @ResponseBody
    public PaginatedResponseDTO<ProductResponseDTO> getProducts(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "16") Integer pageSize,
            @RequestParam(value = "searchTerms", required = false) List<String> searchTerms)
    {
        final Page<Product> productPage = getProductService().getProducts(PageRequest.of(page, pageSize), searchTerms);

        return PaginatedResponseDTO.<ProductResponseDTO>builder()
                .currentPage(productPage.getNumber())
                .totalPages(productPage.getTotalPages())
                .totalEntries(productPage.getTotalElements())
                .responseDTOs(productPage
                        .map(productToProductResponseDTOMapper::map).getContent())
                .build();
    }

    @GetMapping("/{id}")
    public ModelAndView showProductDetails(@PathVariable("id") final Long id) {
        final Product product = getProductService().getProductById(id)
                .orElseThrow(() -> new ProductNotFoundException(String.format("Product with id : %d not found!", id)));
        final ModelAndView modelAndView = new ModelAndView("product/product-details");
        modelAndView.addObject("productDetails",
                getProductToProductResponseDTOMapper().map(product));
        return modelAndView;
    }

    @GetMapping("product-listing")
    public ModelAndView getProductListingPage() {
        return new ModelAndView("product/product-listing");
    }
}
