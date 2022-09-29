package com.xxxx.crm.model;

public class OrderModel {
    private String productName;

    private Double price;

    private Integer quantity;

    private Integer stock;

    private Integer productId;

    public OrderModel() {
    }

    public OrderModel(Integer productId, String productName, Double price, Integer quantity, Integer stock) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.stock = stock;
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }


}
