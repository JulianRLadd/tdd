package com.tdd.salestax;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class TaxCalculator {
    private TaxCalculator() {
    }

    public static TaxCalculator getInstance() {

        return new TaxCalculator();
    }

    public static BigDecimal calculateTax(BigDecimal price, double taxRate) {
        MathContext mc = new MathContext(4);

        BigDecimal first = price.multiply(BigDecimal.valueOf(taxRate));
        return price.add(roundUp(first,BigDecimal.valueOf(0.05),RoundingMode.UP)).round(mc);
    }

    public static BigDecimal roundUp(BigDecimal value, BigDecimal increment,
                                     RoundingMode roundingMode) {
        BigDecimal divided = value.divide(increment, 0, roundingMode);
        BigDecimal result = divided.multiply(increment);
        return result;
    }
}