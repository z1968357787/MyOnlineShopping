package com.xxxx.crm.vo;

public class Credit {
    private String creditNumber;

    private String creditPassword;

    private Double loan;

    private Integer creditScore;

    private String cardHolder;

    public String getCreditNumber() {
        return creditNumber;
    }

    public void setCreditNumber(String creditNumber) {
        this.creditNumber = creditNumber == null ? null : creditNumber.trim();
    }

    public String getCreditPassword() {
        return creditPassword;
    }

    public void setCreditPassword(String creditPassword) {
        this.creditPassword = creditPassword == null ? null : creditPassword.trim();
    }

    public Double getLoan() {
        return loan;
    }

    public void setLoan(Double loan) {
        this.loan = loan;
    }

    public Integer getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Integer creditScore) {
        this.creditScore = creditScore;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder == null ? null : cardHolder.trim();
    }
}