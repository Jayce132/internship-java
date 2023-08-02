package ro.nymphis.exception.checkout;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CheckoutExceptionMessages {
    EMPTY_CART("The cart must contain at least one item in order to check out."),
    ITEMS_OUT_OF_STOCK("The cart contains items that are out of stock."),

    ORDER_NOT_FILLED("The order details must be filled."),

    NO_PENDING_ORDER("No pending order was found!");

    private final String message;
}
