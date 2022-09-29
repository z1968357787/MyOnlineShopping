package com.xxxx.crm.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class OrderKeyModel {
    private Integer userId;

    private Integer productId;

    private int quantity;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
