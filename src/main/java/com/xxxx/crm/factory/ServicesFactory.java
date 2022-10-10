package com.xxxx.crm.factory;

import com.xxxx.crm.adapter.ITaxCalculatorAdapter;
import com.xxxx.crm.adapter.taxCalculatorAdapter.*;
import com.xxxx.crm.strategy.ISalePricingStrategy;
import com.xxxx.crm.strategy.salePricingStrategy.*;
import com.xxxx.crm.utils.AssertUtil;
import com.xxxx.crm.vo.DiscountUser;
import com.xxxx.crm.vo.TaxCalculator;

import java.util.List;

public class ServicesFactory {
    private static ServicesFactory instance;
    private ITaxCalculatorAdapter taxCalculatorAdapter;
    private ISalePricingStrategy salePricingStrategy;

    private static final int strategy1=1;
    private static final int strategy2=2;
    private static final int strategy3=3;
    private static final int strategy4=4;
    private static final int strategy5=5;

    private static final int tax1=1;
    private static final int tax2=2;
    private static final int tax3=3;
    private static final int tax4=4;
    private static final int tax5=5;

    public static ServicesFactory getInstance(){
        if(instance==null){
            instance=new ServicesFactory();
        }
        return instance;
    }

    public ITaxCalculatorAdapter getTaxCalculatorAdapter(List<TaxCalculator> taxCalculatorList) {
        if(taxCalculatorList.size()==1){
            taxCalculatorAdapter=createTaxCalculatorAdapter(taxCalculatorList.get(0));
        }else {
            taxCalculatorAdapter=createTaxCalculatorAdapterComposite(taxCalculatorList);
        }
        return taxCalculatorAdapter;
    }

    public ISalePricingStrategy getSalePricingStrategy(List<DiscountUser> discountUserList) {
        if(discountUserList.size()==1){
            salePricingStrategy=createSalePricingStrategy(discountUserList.get(0));
        }else {
            salePricingStrategy=createSalePricingStrategyComposite(discountUserList);
        }
        return salePricingStrategy;
    }
    private ISalePricingStrategy createSalePricingStrategy(DiscountUser discountUser){
        switch(discountUser.getDiscountId()){
            case strategy1:return new SalePricingStrategy1(discountUser);
            case strategy2:return new SalePricingStrategy2(discountUser);
            case strategy3:return new SalePricingStrategy3(discountUser);
            case strategy4:return new SalePricingStrategy4(discountUser);
            case strategy5:return new SalePricingStrategy5(discountUser);
            default:return null;
        }
    }
    private ISalePricingStrategy createSalePricingStrategyComposite(List<DiscountUser> discountUserList){
        SalePricingStrategyComposite salePricingStrategyComposite=new SalePricingStrategyComposite();
        for(DiscountUser discountUser:discountUserList){
            salePricingStrategyComposite.addSalePricingStrategyList(createSalePricingStrategy(discountUser));
        }
        return salePricingStrategyComposite;
    }

    private ITaxCalculatorAdapter createTaxCalculatorAdapter(TaxCalculator taxCalculator){
        switch(taxCalculator.getTaxId()){
            case tax1:return new TaxCalculatorAdapter1(taxCalculator);
            case tax2:return new TaxCalculatorAdapter2(taxCalculator);
            case tax3:return new TaxCalculatorAdapter3(taxCalculator);
            case tax4:return new TaxCalculatorAdapter4(taxCalculator);
            case tax5:return new TaxCalculatorAdapter5(taxCalculator);
            default:return null;
        }
    }
    private ITaxCalculatorAdapter createTaxCalculatorAdapterComposite(List<TaxCalculator> taxCalculatorList){
        TaxCalculatorAdapterComposite taxCalculatorAdapterComposite=new TaxCalculatorAdapterComposite();
        for(TaxCalculator taxCalculator:taxCalculatorList){
            taxCalculatorAdapterComposite.addTaxCalculatorAdapterList(createTaxCalculatorAdapter(taxCalculator));
        }
        return taxCalculatorAdapterComposite;
    }
}
