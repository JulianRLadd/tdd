package com.tdd.salestax;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        Order order = Order.getInstance();
        Item book = Item.getInstance("book", 1, BigDecimal.valueOf(12.49),"exempt");
        Item musicCD = Item.getInstance("music cd", 1, BigDecimal.valueOf(14.99));
        Item chocolate = Item.getInstance("chocolate bar", 1, BigDecimal.valueOf(0.85),"exempt");
        order.addItem(book);
        order.addItem(musicCD);
        order.addItem(chocolate);
        Receipt receipt = Receipt.getInstance(order);
        String actual = ReceiptView.getInstance().prePrint(receipt);

        System.out.println(actual);


        Order order2 = Order.getInstance();
        Item chocolate2 = Item.getInstance("box of chocolates", 1, BigDecimal.valueOf(10.00),"both");
        Item perfume = Item.getInstance("bottle of perfume", 1, BigDecimal.valueOf(47.50),"imported");
        order2.addItem(chocolate2);
        order2.addItem(perfume);
        Receipt receipt2 = Receipt.getInstance(order2);
        String actual2 = ReceiptView.getInstance().prePrint(receipt2);

        System.out.println(actual2);


        Order order3 = Order.getInstance();
        Item importedPerfume = Item.getInstance("bottle of perfume", 1, BigDecimal.valueOf(27.99),"imported");
        Item perfume2 = Item.getInstance("bottle of perfume", 1, BigDecimal.valueOf(18.99));
        Item pills = Item.getInstance("packet of headache pills", 1, BigDecimal.valueOf(9.75),"exempt");
        Item chocolate3 = Item.getInstance("box of chocolates", 1, BigDecimal.valueOf(11.25),"both");
        order3.addItem(importedPerfume);
        order3.addItem(perfume2);
        order3.addItem(pills);
        order3.addItem(chocolate3);
        Receipt receipt3 = Receipt.getInstance(order3);
        String actual3 = ReceiptView.getInstance().prePrint(receipt3);

        System.out.println(actual3);

    }

}
