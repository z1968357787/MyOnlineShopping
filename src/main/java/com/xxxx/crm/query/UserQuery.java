package com.xxxx.crm.query;

import com.xxxx.crm.base.BaseQuery;

public class UserQuery extends BaseQuery {
    String userName;
    String trueName;
    String role;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
