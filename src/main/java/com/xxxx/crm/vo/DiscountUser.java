package com.xxxx.crm.vo;

public class DiscountUser extends DiscountUserKey {
    private String discountDescription;

    private Integer count;

    public String getDiscountDescription() {
        return discountDescription;
    }

    public void setDiscountDescription(String discountDescription) {
        this.discountDescription = discountDescription == null ? null : discountDescription.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}