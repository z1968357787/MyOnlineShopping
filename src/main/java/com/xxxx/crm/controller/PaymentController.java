package com.xxxx.crm.controller;

import com.xxxx.crm.base.BaseController;
import com.xxxx.crm.base.ResultInfo;
import com.xxxx.crm.model.PaymentModel;
import com.xxxx.crm.service.*;
import com.xxxx.crm.utils.LoginUserUtil;
import com.xxxx.crm.vo.Contact;
import com.xxxx.crm.vo.Order;
import com.xxxx.crm.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("payment")
public class PaymentController extends BaseController {

    @Resource
    private CashPaymentService cashPaymentService;

    @Resource
    private AccountPaymentService accountPaymentService;

    @Resource
    private CreditPaymentService creditPaymentService;

    @Resource
    private ContactService contactService;

    @Resource
    private OrderService orderService;

    @Resource
    private UserService userService;

    @RequestMapping("toPayPage")
    public String toPayPage(HttpServletRequest request){
        //Integer userId= LoginUserUtil.releaseUserIdFromCookie(request);
        //Double total=orderService.getOrderTotal(userId);
        List<Order> orderList= (List<Order>) request.getSession().getAttribute("orderList");
        Double total=orderService.getOrderTotal(orderList);
        request.getSession().setAttribute("total",total);
        return "payment/pay_for_order";
    }

    @RequestMapping("changePayment")
    public String changePayment(HttpServletRequest request, Integer contactId){
        Contact contact=contactService.selectByPrimaryKey(contactId);
        request.setAttribute("contact",contact);
        return "payment/pay_by_change";
    }

    @RequestMapping("accountPayment")
    public String accountPayment(HttpServletRequest request, Integer contactId){
        Contact contact=contactService.selectByPrimaryKey(contactId);
        request.setAttribute("contact",contact);
        return "payment/pay_by_account";
    }

    @RequestMapping("creditPayment")
    public String creditPayment(HttpServletRequest request, Integer contactId){
        Contact contact=contactService.selectByPrimaryKey(contactId);
        request.setAttribute("contact",contact);
        return "payment/pay_by_credit";
    }

    @RequestMapping("recharge")
    public String recharge(HttpServletRequest request, Integer contactId){
        return "payment/wallet_recharge";
    }

    @PostMapping("makePayment")
    @ResponseBody
    public ResultInfo makePayment(HttpServletRequest request, PaymentModel paymentModel){
        System.out.println(paymentModel.getPayMode());
        Integer userId=LoginUserUtil.releaseUserIdFromCookie(request);
        Double total= (Double) request.getSession().getAttribute("total");
        List<Order> orderList= (List<Order>) request.getSession().getAttribute("orderList");

        switch (paymentModel.getPayMode()){
            case "change":cashPaymentService.makePayment(userId,paymentModel,orderList,total);break;
            case "account":accountPaymentService.makePayment(userId,paymentModel,orderList,total);break;
            case "credit":creditPaymentService.makePayment(userId,paymentModel,orderList,total);break;
        }
        User user=userService.selectByPrimaryKey(userId);
        request.getSession().setAttribute("user",user);
        return success("操作成功了!");
    }
}
