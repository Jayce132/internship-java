package ro.nymphis.mapper.product;

import org.springframework.stereotype.Component;
import ro.nymphis.dto.product.ProductResponseDTO;
import ro.nymphis.mapper.Mapper;
import ro.nymphis.model.product.Product;

import static ro.nymphis.dto.product.StockStatusEnumeration.*;

@Component
public class ProductToProductResponseDTOMapper implements Mapper<Product, ProductResponseDTO> {


    @Override
    public ProductResponseDTO map(final Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .title(product.getTitle())
                .price(product.getPrice())
                .thumbnail(product.getThumbnail())
                .description(product.getDescription())
                .stock(product.getStock())
                .stockStatus(getStockStatus(product.getStock()))
                .build();
    }

    public static String getStockStatus(Integer productStock) {
        if (productStock > 10) {
            return IN_STOCK.getStockName();
        } else if (productStock > 0) {
            return LIMITED_STOCK.getStockName();
        } else {
            return OUT_OF_STOCK.getStockName();
        }
    }

}
