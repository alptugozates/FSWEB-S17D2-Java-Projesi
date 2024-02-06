package com.workintech.spring.SpringProject2.tax;

public class DeveloperTax implements Taxable{
    @Override
    public double getSimpleTaxRate() {
        return 0.1;
    }

    @Override
    public double getMiddleTaxRate() {
        return 0.2;
    }

    @Override
    public double getUpperTaxRate() {
        return 0.3;
    }
}
