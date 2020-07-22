package com.tdd.salestax;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.NumberFormat;

import static org.junit.Assert.fail;

public class exampleTests {

    private String getItemFormat(BigDecimal price, String description, int quantity) {
        return String.format("\n\t%d\t %s:\t%s", quantity, description, NumberFormat.getCurrencyInstance().format(price));
    }

//    private String getItemFormat(BigDecimal price, String description, int quantity, String status) {
//        return String.format("\n\t%d\t%s %s:\t%s", quantity, status, description, NumberFormat.getCurrencyInstance().format(price));
//    }

    private String getReadOutFormat(BigDecimal taxTotal,BigDecimal totalPrice) {
        return String.format("\n\t%s %s\t%s %s","Sales Taxes:", NumberFormat.getCurrencyInstance().format(taxTotal),"Total:", NumberFormat.getCurrencyInstance().format(totalPrice));
    }

    @Test
    public void testSalesTax_singleExemptItem() {
        String expectedBook = getItemFormat(BigDecimal.valueOf(12.49), "book", 1);
        String expectedReadOut = getReadOutFormat(BigDecimal.valueOf(0.00),BigDecimal.valueOf(12.49));
        Order order = Order.getInstance();
        Item book = Item.getInstance("book", 1, BigDecimal.valueOf(12.49),"exempt");
        order.addItem(book);
        Receipt receipt = Receipt.getInstance(order);
        String expected = String.format("%s%s", expectedBook,expectedReadOut);
        String actual = ReceiptView.getInstance().prePrint(receipt);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSalesTax_multipleExemptItem() {
        String expectedBook = getItemFormat(BigDecimal.valueOf(12.49), "book", 1);
        String expectedChocolate = getItemFormat(BigDecimal.valueOf(0.85), "chocolate bar", 1);
        String expectedReadOut = getReadOutFormat(BigDecimal.valueOf(0.00),BigDecimal.valueOf(13.34));
        Order order = Order.getInstance();
        Item book = Item.getInstance("book", 1, BigDecimal.valueOf(12.49),"exempt");
        Item chocolate = Item.getInstance("chocolate bar", 1, BigDecimal.valueOf(0.85),"exempt");
        order.addItem(book);
        order.addItem(chocolate);
        Receipt receipt = Receipt.getInstance(order);
        String expected = String.format("%s%s%s", expectedBook, expectedChocolate,expectedReadOut);
        String actual = ReceiptView.getInstance().prePrint(receipt);
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testSingleItemImported() {
        String expectedChocolate = getItemFormat(BigDecimal.valueOf(10.50), "imported chocolate", 1);
        String expectedReadOut = getReadOutFormat(BigDecimal.valueOf(0.50),BigDecimal.valueOf(10.50));
        Order order = Order.getInstance();
        Item chocolate = Item.getInstance("chocolate", 1, BigDecimal.valueOf(10.00),"both");
        order.addItem(chocolate);
        Receipt receipt = Receipt.getInstance(order);
        String expected = String.format("%s%s", expectedChocolate, expectedReadOut);
        String actual = ReceiptView.getInstance().prePrint(receipt);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testMultipleItemImported() {
        String expectedChocolate = getItemFormat(BigDecimal.valueOf(10.50), "imported chocolate", 1);
        String expectedCologne = getItemFormat(BigDecimal.valueOf(23.00), "imported cologne", 1);
        String expectedReadOut = getReadOutFormat(BigDecimal.valueOf(3.50),BigDecimal.valueOf(33.50));
        Order order = Order.getInstance();
        Item chocolate = Item.getInstance("chocolate", 1, BigDecimal.valueOf(10.00),"both");
        Item cologne = Item.getInstance("cologne", 1, BigDecimal.valueOf(20.00),"imported");
        order.addItem(chocolate);
        order.addItem(cologne);
        Receipt receipt = Receipt.getInstance(order);
        String expected = String.format("%s%s%s", expectedChocolate, expectedCologne, expectedReadOut);
        String actual = ReceiptView.getInstance().prePrint(receipt);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testMixedItems() {
        String expectedImportedPerfume = getItemFormat(BigDecimal.valueOf(32.19), "imported bottle of perfume", 1);
        String expectedPerfume = getItemFormat(BigDecimal.valueOf(20.89), "bottle of perfume", 1);
        String expectedPills = getItemFormat(BigDecimal.valueOf(9.75), "packet of headache pills", 1);
        String expectedChocolate = getItemFormat(BigDecimal.valueOf(11.85), "imported box of chocolates", 1);
        String expectedReadOut = getReadOutFormat(BigDecimal.valueOf(6.70),BigDecimal.valueOf(74.68));
        Order order = Order.getInstance();
        Item importedPerfume = Item.getInstance("bottle of perfume", 1, BigDecimal.valueOf(27.99),"imported");
        Item perfume = Item.getInstance("bottle of perfume", 1, BigDecimal.valueOf(18.99));
        Item pills = Item.getInstance("packet of headache pills", 1, BigDecimal.valueOf(9.75),"exempt");
        Item chocolate = Item.getInstance("box of chocolates", 1, BigDecimal.valueOf(11.25),"both");
        order.addItem(importedPerfume);
        order.addItem(perfume);
        order.addItem(pills);
        order.addItem(chocolate);
        Receipt receipt = Receipt.getInstance(order);
        String expected = String.format("%s%s%s%s%s", expectedImportedPerfume, expectedPerfume, expectedPills, expectedChocolate, expectedReadOut);
        String actual = ReceiptView.getInstance().prePrint(receipt);
        Assert.assertEquals(expected, actual);
    }
}
