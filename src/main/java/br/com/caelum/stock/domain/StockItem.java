package br.com.caelum.stock.domain;

import java.util.StringJoiner;

public class StockItem {
    private String code;
    private Integer quantity;

    public StockItem(String code, Integer quantity) {
        this.code = code;
        this.quantity = quantity;
    }

    public String getCode() {
        return code;
    }

    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", StockItem.class.getSimpleName() + "[", "]")
            .add("code='" + code + "'")
            .add("quantity=" + quantity)
            .toString();
    }
}
