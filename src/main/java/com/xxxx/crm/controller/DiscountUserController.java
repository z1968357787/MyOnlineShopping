package com.xxxx.crm.controller;

import com.xxxx.crm.base.BaseController;
import com.xxxx.crm.base.ResultInfo;
import com.xxxx.crm.query.DiscountQuery;
import com.xxxx.crm.query.ProductQuery;
import com.xxxx.crm.service.DiscountUserService;
import com.xxxx.crm.utils.LoginUserUtil;
import com.xxxx.crm.vo.DiscountUser;
import com.xxxx.crm.vo.Order;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("discountUser")
public class DiscountUserController extends BaseController {

    @Resource
    private DiscountUserService discountUserService;

    @RequestMapping("index")
    public String index(HttpServletRequest request){
        return "discountUser/discount_showing";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryDiscountByParams(HttpServletRequest request,DiscountQuery discountQuery){
        Integer userId= LoginUserUtil.releaseUserIdFromCookie(request);
        discountQuery.setUserId(userId);
        return discountUserService.queryDiscountByParams(discountQuery);
    }

    @PostMapping("takeDiscounts")
    @ResponseBody
    public ResultInfo payCart(HttpServletRequest request, @RequestParam("list")String discountUserString) throws IOException, ParseException {
        List<DiscountUser> discountUserList=discountUserService.transform(discountUserString);
        request.getSession().setAttribute("discountUserList",discountUserList);
        return success("操作成功");
    }
}
