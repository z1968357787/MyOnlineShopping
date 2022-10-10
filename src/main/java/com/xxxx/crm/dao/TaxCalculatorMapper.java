package com.xxxx.crm.dao;

import com.xxxx.crm.base.BaseMapper;
import com.xxxx.crm.vo.TaxCalculator;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaxCalculatorMapper extends BaseMapper<TaxCalculator,Integer> {
    List<TaxCalculator> selectByState();

    Integer updateTaxCalculator(@Param("array")Integer[] ids, @Param("state")String state);

    //Integer cancelTaxCalculator(Integer[] ids);
}