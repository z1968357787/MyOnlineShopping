package com.xxxx.crm.strategy.salePricingStrategy;

import com.xxxx.crm.model.MoneyModel;
import com.xxxx.crm.strategy.ISalePricingStrategy;
import com.xxxx.crm.vo.DiscountUser;
import com.xxxx.crm.vo.PayLog;
import com.xxxx.crm.vo.User;

import java.util.ArrayList;
import java.util.List;

public class SalePricingStrategyComposite implements ISalePricingStrategy {

    private List<ISalePricingStrategy> salePricingStrategyList=new ArrayList<>();

    @Override
    public void getTotal(User user, List<PayLog> payLogList, MoneyModel moneyModel) {
        Double discountTotal=moneyModel.getSumTotal()*2;
        ISalePricingStrategy salePricingStrategyTemp=null;
        for(ISalePricingStrategy salePricingStrategy:salePricingStrategyList){
            /*
             *选出优惠最好的优惠券
             */
            salePricingStrategy.getTotal(user,payLogList,moneyModel);
            if(discountTotal>moneyModel.getDiscountTotal()){
                discountTotal= moneyModel.getDiscountTotal();
                salePricingStrategyTemp=salePricingStrategy;
            }
        }
        if(salePricingStrategyTemp!=null){
            salePricingStrategyTemp.getTotal(user,payLogList,moneyModel);
        }
    }

    public void addSalePricingStrategyList(ISalePricingStrategy salePricingStrategy){
        salePricingStrategyList.add(salePricingStrategy);
    }
}
