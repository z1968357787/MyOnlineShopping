package com.xxxx.crm.query;

import com.xxxx.crm.base.BaseQuery;

/*
 *营销机会查询类
 */
public class ProductQuery extends BaseQuery {

    //分页参数

    //条件参数

    private String productName;
    private String productDescription;
    private String classification;
    private String supplier;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
}
