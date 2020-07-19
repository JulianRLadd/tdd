package com.tdd.salestax;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class Item {
    private final String description;
    private final int quantity;
    private BigDecimal price;
    private double taxRate;
    private TaxStatus condition;


    private Item(String description, int quantity, BigDecimal itemPrice) {
        this.description = description;
        this.quantity = quantity;
        this.price = itemPrice;
        this.taxRate = 0.1;
    }

    private Item(String description, int quantity, BigDecimal itemPrice, String status) {
        this.description = description;
        this.quantity = quantity;
        this.price = itemPrice;
        this.taxRate = this.condition.adjustTaxRate(0.1);

        if(status=="import"){
            this.condition = new Imported();
        }if(status == "exempt"){
            this.condition = new Exempt();
        } else if(status == "both"){
            this.condition = new ImportAndExempt();
        }

    }


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public static Item getInstance(String description, int quantity, BigDecimal itemPrice) {
        return new Item(description, quantity, itemPrice);
    }

        public static Item getInstance(String description, int quantity, BigDecimal itemPrice, String status) {
        return new Item(description, quantity, itemPrice, status);
    }

    @Override
    public String toString() {
        return String.format("%d\t%s:\t%s", quantity, description, NumberFormat.getCurrencyInstance().format(price));
    }
}
