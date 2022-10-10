package com.xxxx.crm.query;

import com.xxxx.crm.base.BaseQuery;

public class DiscountQuery extends BaseQuery {
    private Integer userId;
    private String discountDescription;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDiscountDescription() {
        return discountDescription;
    }

    public void setDiscountDescription(String discountDescription) {
        this.discountDescription = discountDescription;
    }
}
