package com.xxxx.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.crm.base.BaseService;
import com.xxxx.crm.dao.TaxCalculatorMapper;
import com.xxxx.crm.query.TaxCalculatorQuery;
import com.xxxx.crm.vo.Discount;
import com.xxxx.crm.vo.TaxCalculator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class TaxCalculatorService extends BaseService<TaxCalculator,Integer> {
    @Resource
    private TaxCalculatorMapper taxCalculatorMapper;

    public Map<String, Object> queryTaxByParams(TaxCalculatorQuery taxCalculatorQuery) {
        Map<String,Object> map=new HashMap<>();
        PageHelper.startPage(taxCalculatorQuery.getPage(),taxCalculatorQuery.getLimit());

        PageInfo<TaxCalculator> pageInfo=new PageInfo<>(taxCalculatorMapper.selectByParams(taxCalculatorQuery));

        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());

        return map;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateTaxCalculator(Integer[] ids,String state) {
        taxCalculatorMapper.updateTaxCalculator(ids,state);
    }

}
