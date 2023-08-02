package ro.nymphis.model.cart;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ro.nymphis.model.BaseModel;
import ro.nymphis.model.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "carts")
public class Cart extends BaseModel<Long> {

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "cart",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems.addAll(cartItems);
    }
}
