package com.xxxx.crm.adapter.taxCalculatorAdapter;

import com.xxxx.crm.adapter.ITaxCalculatorAdapter;
import com.xxxx.crm.model.MoneyModel;
import com.xxxx.crm.vo.PayLog;
import com.xxxx.crm.vo.User;

import java.util.ArrayList;
import java.util.List;

public class TaxCalculatorAdapterComposite implements ITaxCalculatorAdapter {

    private List<ITaxCalculatorAdapter> taxCalculatorAdapterList =new ArrayList<>();


    public void addTaxCalculatorAdapterList(ITaxCalculatorAdapter taxCalculatorAdapter){
        taxCalculatorAdapterList.add(taxCalculatorAdapter);
    }


    @Override
    public void getTaxes(User user, List<PayLog> payLogList, MoneyModel moneyModel) {
        Double taxTotal=moneyModel.getSumTotal()*2;
        ITaxCalculatorAdapter taxCalculatorAdapterTemp=null;
        for(ITaxCalculatorAdapter taxCalculatorAdapter: taxCalculatorAdapterList){
            /*
             *选出优惠最好的税金计算方式
             */
            taxCalculatorAdapter.getTaxes(user,payLogList,moneyModel);
            if(taxTotal>moneyModel.getTaxTotal()){
                taxTotal= moneyModel.getTaxTotal();
                taxCalculatorAdapterTemp=taxCalculatorAdapter;
            }
        }
        if(taxCalculatorAdapterTemp!=null){
            taxCalculatorAdapterTemp.getTaxes(user,payLogList,moneyModel);
        }
    }

}
