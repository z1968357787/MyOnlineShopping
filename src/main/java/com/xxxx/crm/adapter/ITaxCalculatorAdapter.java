package com.xxxx.crm.adapter;

import com.xxxx.crm.model.MoneyModel;
import com.xxxx.crm.vo.Order;
import com.xxxx.crm.vo.PayLog;
import com.xxxx.crm.vo.TaxCalculator;
import com.xxxx.crm.vo.User;

import java.util.List;

public interface ITaxCalculatorAdapter {
    void getTaxes(User user, List<PayLog> payLogList, MoneyModel moneyModel);
}
