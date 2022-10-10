package com.xxxx.crm.controller;

import com.xxxx.crm.base.BaseController;
import com.xxxx.crm.base.ResultInfo;
import com.xxxx.crm.query.DiscountQuery;
import com.xxxx.crm.service.DiscountService;
import com.xxxx.crm.utils.LoginUserUtil;
import com.xxxx.crm.vo.Discount;
import com.xxxx.crm.vo.DiscountUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("discount")
public class DiscountController extends BaseController {

    @Resource
    private DiscountService discountService;

    @RequestMapping("index")
    public String index(HttpServletRequest request){
        return "discount/discount_showing";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryDiscountByParams(DiscountQuery discountQuery){
        return discountService.queryDiscountByParams(discountQuery);
    }

    @RequestMapping("assignDiscountPage")
    public String assignDiscountPage(HttpServletRequest request, Discount discount){
        request.setAttribute("discount",discount);
        return "discount/assign_discount";
    }

    @PostMapping("assign")
    @ResponseBody
    public ResultInfo assignDiscount(DiscountUser discountUser){
        discountService.assignDiscount(discountUser);
        return success("发配成功");
    }
}
