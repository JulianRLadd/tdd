package com.tdd.salestax;

public class ImportAndExempt implements TaxStatus{


    public String taxStatus = "imported";

    @Override
    public double adjustTaxRate(double initialTaxRate) {
        return initialTaxRate-0.05;
    }
}
