package com.tdd.salestax;

import java.math.MathContext;
import java.text.NumberFormat;

public class ReceiptView {

    private ReceiptView() {
    }

    public static ReceiptView getInstance() {
        return new ReceiptView();
    }

    public String prePrint(Receipt receipt) {
        StringBuilder formattedReceipt = new StringBuilder();
        for (Item item : receipt.getItems()){
            formattedReceipt.append(String.format("\n\t%s", item.toString()));
        }
        formattedReceipt.append(String.format("\n\t%s %s\t%s %s","Sales Taxes:",NumberFormat.getCurrencyInstance().format(receipt.getSalesTaxTotal()),
                "Total:", NumberFormat.getCurrencyInstance().format(receipt.getTotal().round(new MathContext(4)))));
        return formattedReceipt.toString();
    }

}