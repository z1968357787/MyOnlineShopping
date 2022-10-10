package com.xxxx.crm.controller;

import com.xxxx.crm.base.BaseController;
import com.xxxx.crm.base.ResultInfo;
import com.xxxx.crm.query.TaxCalculatorQuery;
import com.xxxx.crm.service.TaxCalculatorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("taxCalculator")
public class TaxCalculatorController extends BaseController {
    @Resource
    private TaxCalculatorService taxCalculatorService;

    @RequestMapping("index")
    public String index(HttpServletRequest request){
        return "taxCalculator/tax_showing";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryTaxByParams(TaxCalculatorQuery taxCalculatorQuery){
        return taxCalculatorService.queryTaxByParams(taxCalculatorQuery);
    }

    @PostMapping("take")
    @ResponseBody
    public ResultInfo takeTaxCalculator(Integer ids[]) throws IOException {
        taxCalculatorService.updateTaxCalculator(ids,"已选择");
        return success("设置成功");
    }

    @PostMapping("cancel")
    @ResponseBody
    public ResultInfo cancelTaxCalculator(Integer ids[]) throws IOException {
        taxCalculatorService.updateTaxCalculator(ids,"未选择");
        return success("取消成功");
    }
}
