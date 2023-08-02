package ro.nymphis.model.product;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ro.nymphis.model.BaseModel;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "products")
public class Product extends BaseModel<Long> {

    private String title;
    private Double price;
    private String thumbnail;
    private String description;
    private Integer stock;

}
