package com.xxxx.crm.vo;

public class Classification {
    private String classification;

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification == null ? null : classification.trim();
    }
}