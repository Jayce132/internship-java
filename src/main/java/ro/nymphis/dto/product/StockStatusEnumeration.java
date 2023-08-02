package ro.nymphis.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StockStatusEnumeration {

    IN_STOCK("In stock"),
    LIMITED_STOCK("Limited stock"),
    OUT_OF_STOCK("Out of stock");

    public final String stockName;
}

