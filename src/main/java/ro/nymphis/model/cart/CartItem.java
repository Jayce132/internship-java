package ro.nymphis.model.cart;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ro.nymphis.model.BaseModel;
import ro.nymphis.model.product.Product;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "cart_items")
public class CartItem extends BaseModel<Long> {

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    @ToString.Exclude
    private Cart cart;

}
