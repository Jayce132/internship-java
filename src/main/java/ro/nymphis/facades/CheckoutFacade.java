package ro.nymphis.facades;

import ro.nymphis.exception.checkout.CheckoutException;

public interface CheckoutFacade {
    void checkOut() throws CheckoutException;
}
