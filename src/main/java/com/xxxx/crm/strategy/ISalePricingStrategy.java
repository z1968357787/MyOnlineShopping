package com.xxxx.crm.strategy;

import com.xxxx.crm.model.MoneyModel;
import com.xxxx.crm.vo.DiscountUser;
import com.xxxx.crm.vo.Order;
import com.xxxx.crm.vo.PayLog;
import com.xxxx.crm.vo.User;

import java.util.List;

public interface ISalePricingStrategy {
    void getTotal(User user, List<PayLog> payLogList, MoneyModel moneyModel);
}
