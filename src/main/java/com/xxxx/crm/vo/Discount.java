package com.xxxx.crm.vo;

public class Discount {
    private Integer discountId;

    private String discountDescription;

    public Integer getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Integer discountId) {
        this.discountId = discountId;
    }

    public String getDiscountDescription() {
        return discountDescription;
    }

    public void setDiscountDescription(String discountDescription) {
        this.discountDescription = discountDescription == null ? null : discountDescription.trim();
    }
}