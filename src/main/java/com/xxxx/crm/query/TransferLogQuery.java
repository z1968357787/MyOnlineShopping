package com.xxxx.crm.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xxxx.crm.base.BaseQuery;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransferLogQuery extends BaseQuery {
    private Integer userId;

    private String transferMode;

    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private String transferDate;

    private String cardHolder;

    public String getTransferMode() {
        return transferMode;
    }

    public void setTransferMode(String transferMode) {
        this.transferMode = transferMode;
    }

    /*public Date getTransferDate() {
        return transferDate;
    }*

    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }*/

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /*public void setTransferDate(String transferDate) throws ParseException {
        this.transferDate = new SimpleDateFormat("yyyy-MM-dd").parse(transferDate);
    }*/

    public String getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(String transferDate) {
        this.transferDate = transferDate;
    }
}
