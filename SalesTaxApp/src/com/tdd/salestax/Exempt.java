package com.tdd.salestax;

public class Exempt implements TaxStatus{

    public String taxStatus = "";


    @Override
    public double adjustTaxRate(double initialTaxRate) {
        return initialTaxRate-0.1;
    }
}
