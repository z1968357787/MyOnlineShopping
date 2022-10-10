package com.xxxx.crm.adapter.taxCalculatorAdapter;

import com.xxxx.crm.adapter.ITaxCalculatorAdapter;
import com.xxxx.crm.model.MoneyModel;
import com.xxxx.crm.vo.PayLog;
import com.xxxx.crm.vo.TaxCalculator;
import com.xxxx.crm.vo.User;

import java.util.List;

public class TaxCalculatorAdapter1 implements ITaxCalculatorAdapter {
    private TaxCalculator taxCalculator;
    private static final double taxRate=1.02;

    public TaxCalculatorAdapter1(TaxCalculator taxCalculator) {
        this.taxCalculator = taxCalculator;
    }

    @Override
    public void getTaxes(User user, List<PayLog> payLogList, MoneyModel moneyModel) {
        moneyModel.setTaxCalculator(taxCalculator);
        Double taxTotal=0.0;
        for(PayLog payLog:payLogList){
            payLog.setTaxTotal(payLog.getDiscountTotal()*taxRate);
            payLog.setTaxDescription(taxCalculator.getTaxDescription());
            taxTotal+=payLog.getTaxTotal();
        }
        moneyModel.setTaxTotal(taxTotal);
    }

}
