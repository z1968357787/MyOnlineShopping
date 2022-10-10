package com.xxxx.crm.model;

import com.xxxx.crm.vo.Discount;
import com.xxxx.crm.vo.DiscountUser;
import com.xxxx.crm.vo.TaxCalculator;

public class MoneyModel {
    private Double sumTotal;
    private Double discountTotal;
    private Double taxTotal;

    private TaxCalculator taxCalculator;
    private DiscountUser discountUser;

    public Double getSumTotal() {
        return sumTotal;
    }

    public void setSumTotal(Double sumTotal) {
        this.sumTotal = sumTotal;
        this.discountTotal=sumTotal;
        this.taxTotal=sumTotal;
    }

    public Double getDiscountTotal() {
        return discountTotal;
    }

    public void setDiscountTotal(Double discountTotal) {
        this.discountTotal = discountTotal;
        this.taxTotal=discountTotal;
    }

    public Double getTaxTotal() {
        return taxTotal;
    }

    public void setTaxTotal(Double taxTotal) {
        this.taxTotal = taxTotal;
    }

    public TaxCalculator getTaxCalculator() {
        return taxCalculator;
    }

    public void setTaxCalculator(TaxCalculator taxCalculator) {
        this.taxCalculator = taxCalculator;
    }

    public DiscountUser getDiscountUser() {
        return discountUser;
    }

    public void setDiscountUser(DiscountUser discountUser) {
        this.discountUser = discountUser;
    }
}
