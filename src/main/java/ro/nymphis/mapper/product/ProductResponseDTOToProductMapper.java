package ro.nymphis.mapper.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.nymphis.dto.product.ProductResponseDTO;
import ro.nymphis.mapper.Mapper;
import ro.nymphis.model.product.Product;

@Component
@RequiredArgsConstructor
@Getter
public class ProductResponseDTOToProductMapper implements Mapper<ProductResponseDTO, Product> {
	@Override
	public Product map(ProductResponseDTO source) {

		Product product = new Product();
		product.setId(source.getId());
		product.setPrice(source.getPrice());
		product.setStock(source.getStock());
		product.setTitle(source.getTitle());
		product.setDescription(source.getDescription());
		product.setThumbnail(source.getThumbnail());
		return product;
	}
}
