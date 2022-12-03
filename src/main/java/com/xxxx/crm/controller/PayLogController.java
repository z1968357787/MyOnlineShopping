package com.xxxx.crm.controller;

import com.xxxx.crm.base.BaseController;
import com.xxxx.crm.base.ResultInfo;
import com.xxxx.crm.model.EvaluationModel;
import com.xxxx.crm.model.PayLogKeyModel;
import com.xxxx.crm.query.PayLogQuery;
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
    public Map<String,Object> queryPatLogByParams(HttpServletRequest request, PayLogQuery payLogQuery){
        Integer userId= LoginUserUtil.releaseUserIdFromCookie(request);
        payLogQuery.setUserId(userId);
        return payLogService.queryPayLogByParams(payLogQuery);
    }

    @RequestMapping("list2")
    @ResponseBody
    public Map<String,Object> queryPayLogByParams2(PayLogQuery payLogQuery){
        return payLogService.queryPayLogByParams(payLogQuery);
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

    @RequestMapping("toEvaluationPage")
    public String toEvaluationPage(HttpServletRequest request,PayLogKeyModel payLogKeyModel){
        request.setAttribute("userId",payLogKeyModel.getUserId());
        request.setAttribute("productId",payLogKeyModel.getProductId());
        request.setAttribute("contactId",payLogKeyModel.getContactId());
        request.setAttribute("payDate",payLogKeyModel.getPayDate());
        return "payLog/paylog_evaluation";
    }

    @PostMapping("processRefund")
    @ResponseBody
    public ResultInfo processRefund(PayLogKeyModel payLogKeyModel) throws ParseException {
        payLogService.processRefund(payLogKeyModel);
        return success("申请退款成功");
    }

    @PostMapping("addEvaluation")
    @ResponseBody
    public ResultInfo addEvaluation(EvaluationModel evaluationModel) throws ParseException {
        payLogService.addEvaluation(evaluationModel);
        return success("订单评价成功");
    }

    @PostMapping("deleteEvaluation")
    @ResponseBody
    public ResultInfo deleteEvaluation(@RequestParam("list")String payLogKeyString) throws ParseException, IOException {
        payLogService.deleteEvaluation(payLogKeyString);
        return success("订单评价成功");
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
