package com.tdd.salestax;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class Item {
    private final String description;
    private final int quantity;
    private BigDecimal price;
    private double taxRate;
    private String foreign = "";

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

        if(status=="imported"){
            this.taxRate = 0.15;
            this.foreign = " imported";
        }if(status == "exempt"){
            this.taxRate = 0;
        } else if(status == "both"){
            this.taxRate = 0.05;
            this.foreign = " imported";
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
        return String.format("%d\t%s %s:\t%s", quantity, foreign, description, NumberFormat.getCurrencyInstance().format(price));
    }
}
