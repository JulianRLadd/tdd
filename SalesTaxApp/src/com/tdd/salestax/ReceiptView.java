package com.tdd.salestax;

import java.math.BigDecimal;

public class ReceiptView {
   // BigDecimal price = BigDecimal.valueOf(12.49);

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
        return formattedReceipt.toString();
    }
}