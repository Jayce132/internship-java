package ro.nymphis.facades;

import ro.nymphis.exception.checkout.CheckoutException;
import ro.nymphis.model.order.Order;

public interface PlaceOrderFacade {

    Order placeOrder() throws CheckoutException;
}
