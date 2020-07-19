package com.tdd.salestax;

import java.math.BigDecimal;

public class Receipt {
    private final Order order;

    private Receipt(Order order) {
        this.order = order;
    }

    public static Receipt getInstance(Order order) {
        return new Receipt(order);
    }

    public Iterable<? extends Item> getItems() {
        for (Item item : order.getItems()){
            BigDecimal newPrice = TaxCalculator.calculateTax(item.getPrice(),item.getTaxRate());
            item.setPrice(newPrice);
        }
        return order.getItems();
    }


}