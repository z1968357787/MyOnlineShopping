package com.xxxx.crm.strategy.salePricingStrategy;

import com.xxxx.crm.model.MoneyModel;
import com.xxxx.crm.strategy.ISalePricingStrategy;
import com.xxxx.crm.vo.DiscountUser;
import com.xxxx.crm.vo.PayLog;
import com.xxxx.crm.vo.User;

import java.util.List;

public class SalePricingStrategy2 implements ISalePricingStrategy {
    private DiscountUser discountUser;
    private static final double discount=0.8;
    @Override
    public void getTotal(User user, List<PayLog> payLogList, MoneyModel moneyModel) {
        moneyModel.setDiscountUser(discountUser);
        Double discountTotal=0.0;
        for(PayLog payLog:payLogList){
            payLog.setDiscountTotal(payLog.getSubtotal()*discount);
            payLog.setDiscountDescription(discountUser.getDiscountDescription());
            discountTotal+=payLog.getDiscountTotal();
        }
        moneyModel.setDiscountTotal(discountTotal);
    }

    public SalePricingStrategy2(DiscountUser discountUser) {
        this.discountUser = discountUser;
    }


}
