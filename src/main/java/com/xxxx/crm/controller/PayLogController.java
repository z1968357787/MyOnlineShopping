package com.xxxx.crm.controller;

import com.xxxx.crm.base.BaseController;
import com.xxxx.crm.base.ResultInfo;
import com.xxxx.crm.model.PayLogKeyModel;
import com.xxxx.crm.query.PayLogQuery;
import com.xxxx.crm.query.TransferLogQuery;
import com.xxxx.crm.service.PayLogService;
import com.xxxx.crm.utils.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

@Controller
@RequestMapping("payLog")
public class PayLogController extends BaseController {
    @Resource
    private PayLogService payLogService;

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryProductByParams(HttpServletRequest request, PayLogQuery payLogQuery){
        Integer userId= LoginUserUtil.releaseUserIdFromCookie(request);
        payLogQuery.setUserId(userId);
        return payLogService.queryProductByParams(payLogQuery);
    }

    @RequestMapping("list2")
    @ResponseBody
    public Map<String,Object> queryProductByParams2(PayLogQuery payLogQuery){
        return payLogService.queryProductByParams(payLogQuery);
    }

    @RequestMapping("index")
    public String index(){
        return "payLog/paylog_showing";
    }

    @RequestMapping("toRefundPage")
    public String toRefundPage(HttpServletRequest request,PayLogKeyModel payLogKeyModel){
        request.setAttribute("userId",payLogKeyModel.getUserId());
        request.setAttribute("productId",payLogKeyModel.getProductId());
        request.setAttribute("contactId",payLogKeyModel.getContactId());
        request.setAttribute("payDate",payLogKeyModel.getPayDate());
        return "payLog/request_refund";
    }

    @PostMapping("processRefund")
    @ResponseBody
    public ResultInfo processRefund(PayLogKeyModel payLogKeyModel) throws ParseException {
        payLogService.processRefund(payLogKeyModel);
        return success("申请退款成功");
    }

    @RequestMapping("postSale")
    public String postSale(){
        return "postSale/post_sale";
    }

    @RequestMapping("toProcessStatePage")
    public String toProcessStatePage(HttpServletRequest request,PayLogKeyModel payLogKeyModel){
        request.setAttribute("userId",payLogKeyModel.getUserId());
        request.setAttribute("productId",payLogKeyModel.getProductId());
        request.setAttribute("contactId",payLogKeyModel.getContactId());
        request.setAttribute("payDate",payLogKeyModel.getPayDate());
        return "postSale/process_state";
    }

    @PostMapping("processState")
    @ResponseBody
    public ResultInfo processState(@RequestParam("list")String postSaleModelString) throws ParseException, IOException {
        payLogService.processState(postSaleModelString);
        return success("订单状态处理成功");
    }
}
