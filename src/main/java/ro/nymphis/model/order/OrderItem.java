package ro.nymphis.model.order;

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
@Entity
@Table(name = "order_item_details")
public class OrderItem extends BaseModel<Long> {

    private Double price;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", price=" + price +
                ", quantity=" + quantity +
                ", product=" + product +
                ", orderId=" + order.getId() +
                '}';
    }
}
