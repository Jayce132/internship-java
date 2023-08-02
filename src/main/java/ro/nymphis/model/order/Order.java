package ro.nymphis.model.order;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import ro.nymphis.model.BaseModel;
import ro.nymphis.model.user.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "orders")
public class Order extends BaseModel<Long> {
    @Column(name = "order_number", unique = true, nullable = false, updatable = false, length = 70)
    private String orderNumber;

    @Column(name = "total")
    private Double totalSum;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "status")
    private String orderStatus;

    @Column(name = "billing_address")
    private String billingAddress;

    @Column(name = "shipping_address")
    private String shippingAddress;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_user_details_id")
    private OrderUserDetails orderUserDetails;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(Double totalSum) {
        this.totalSum = totalSum;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Collection<OrderItem> orderItem) {
        this.orderItems.addAll(orderItem);
    }

    public OrderUserDetails getOrderUserDetails() {
        return orderUserDetails;
    }

    public void setOrderUserDetails(OrderUserDetails orderUserDetails) {
        this.orderUserDetails = orderUserDetails;
    }
}
