package ro.nymphis.model.order;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OrderStatus {
    CREATED("CREATED"),
    FILLED("FILLED"),
    PLACED("PLACED");

    private final String orderStatus;

    @Override
    public String toString() {
        return orderStatus;
    }
}
