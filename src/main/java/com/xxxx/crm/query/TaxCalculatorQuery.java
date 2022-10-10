package com.xxxx.crm.query;

import com.xxxx.crm.base.BaseQuery;

public class TaxCalculatorQuery extends BaseQuery {
    private String taxDescription;
    private String state;

    public String getTaxDescription() {
        return taxDescription;
    }

    public void setTaxDescription(String taxDescription) {
        this.taxDescription = taxDescription;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
