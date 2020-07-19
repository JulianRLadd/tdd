package com.tdd.salestax;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class Main {

    private static String getItemFormat(BigDecimal price, String description, int quantity) {
        return String.format("\n\t%s\t%d\t%s", description, quantity, NumberFormat.getCurrencyInstance().format(price));
    }
    private static String getReadOutFormat(BigDecimal taxTotal,BigDecimal totalPrice) {
        return String.format("\n\t%s %s\t%s %s","Sales Taxes:", NumberFormat.getCurrencyInstance().format(taxTotal),"Total:", NumberFormat.getCurrencyInstance().format(totalPrice));
    }

    public static void main(String[] args) {
        String expectedBook = getItemFormat(BigDecimal.valueOf(12.49), "book", 1);
        String expectedMusicCD = getItemFormat(BigDecimal.valueOf(14.99), "music cd", 1);
        String expectedChocolate = getItemFormat(BigDecimal.valueOf(0.85), "chocolate bar", 1);
        String expectedReadOut = getReadOutFormat(BigDecimal.valueOf(1.50),BigDecimal.valueOf(29.83));

        Order order = Order.getInstance();
        Item book = Item.getInstance("book", 1, BigDecimal.valueOf(12.49),"exempt");
        Item musicCD = Item.getInstance("music cd", 1, BigDecimal.valueOf(14.99));
        Item chocolate = Item.getInstance("chocolate bar", 1, BigDecimal.valueOf(0.85));
        order.addItem(book);
        order.addItem(musicCD);
        order.addItem(chocolate);
        Receipt receipt = Receipt.getInstance(order);
        String expected = String.format("%s%s%s%s", expectedBook, expectedMusicCD, expectedChocolate, expectedReadOut);
        String actual = ReceiptView.getInstance().prePrint(receipt);

        System.out.println(expected);
        System.out.println(actual);
    }

}
