package com.tdd.salestax;

import java.math.BigDecimal;
import java.math.MathContext;

public class TaxCalculator {
    private TaxCalculator() {
    }

    public static TaxCalculator getInstance() {

        return new TaxCalculator();
    }

    public static BigDecimal calculateTax(BigDecimal price, double taxRate) {
        MathContext mc = new MathContext(4);
        return price.add(price.multiply(BigDecimal.valueOf(taxRate))).round(mc);
    }
}