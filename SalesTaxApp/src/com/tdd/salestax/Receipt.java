package com.tdd.salestax;

import java.math.BigDecimal;
import java.math.MathContext;

public class Receipt {
    private final Order order;
    private BigDecimal total = BigDecimal.valueOf(0);
    private BigDecimal preTotal = BigDecimal.valueOf(0);
    private Receipt(Order order) {
        this.order = order;
    }

    public static Receipt getInstance(Order order) {
        return new Receipt(order);
    }

    public Iterable<? extends Item> getItems() {
        for (Item item : order.getItems()){
            BigDecimal noTaxPrice = item.getPrice();
            preTotal = preTotal.add(noTaxPrice);
            BigDecimal newPrice = TaxCalculator.calculateTax(item.getPrice(),item.getTaxRate());
            item.setPrice(newPrice);
            total = total.add(newPrice);
        }
        return order.getItems();
    }

    public BigDecimal getTotal(){
        return total;
    }

    public BigDecimal getSalesTaxTotal(){
        preTotal = total.subtract(preTotal);
        return preTotal;
    }

}