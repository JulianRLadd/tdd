package com.tdd.salestax;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.NumberFormat;

import static org.junit.Assert.fail;

public class exampleTests {

    private String getItemFormat(BigDecimal price, String description, int quantity) {
        return String.format("\n\t%d\t%s:\t%s", quantity, description, NumberFormat.getCurrencyInstance().format(price));
    }


    @Test
    public void singleItemTest(){
        BigDecimal price = BigDecimal.valueOf(12.49);
        Order order = Order.getInstance();
        Item book = Item.getInstance("book", 1, BigDecimal.valueOf(12.49));
        order.addItem(book);
        Receipt receipt = Receipt.getInstance(order);
        String expected = getItemFormat(price, "book", 1);
        String actual = ReceiptView.getInstance().prePrint(receipt);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testPrePrint_multipleItems() {
        String expectedBook = getItemFormat(BigDecimal.valueOf(12.49), "book", 1);
        String expectedMusicCD = getItemFormat(BigDecimal.valueOf(14.99), "music cd", 1);
        String expectedChocolate = getItemFormat(BigDecimal.valueOf(0.85), "chocolate bar", 1);
        Order order = Order.getInstance();
        Item book = Item.getInstance("book", 1, BigDecimal.valueOf(12.49));
        Item musicCD = Item.getInstance("music cd", 1, BigDecimal.valueOf(14.99));
        Item chocolate = Item.getInstance("chocolate bar", 1, BigDecimal.valueOf(0.85));
        order.addItem(book);
        order.addItem(musicCD);
        order.addItem(chocolate);
        Receipt receipt = Receipt.getInstance(order);
        String expected = String.format("%s%s%s", expectedBook, expectedMusicCD,expectedChocolate);
        String actual = ReceiptView.getInstance().prePrint(receipt);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testSalesTaxSingleItem() {
        String expectedMusicCD = getItemFormat(BigDecimal.valueOf(16.49), "music cd", 1);
        Order order = Order.getInstance();
        Item book = Item.getInstance("music cd", 1, BigDecimal.valueOf(14.99));
        order.addItem(book);
        Receipt receipt = Receipt.getInstance(order);
        String expected = String.format("%s", expectedMusicCD);
        String actual = ReceiptView.getInstance().prePrint(receipt);
        Assert.assertEquals(actual, expected);
    }

}
