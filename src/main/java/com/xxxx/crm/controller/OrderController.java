package com.xxxx.crm.controller;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xxxx.crm.base.BaseController;
import com.xxxx.crm.base.ResultInfo;
import com.xxxx.crm.model.OrderKeyModel;
import com.xxxx.crm.model.OrderModel;
import com.xxxx.crm.query.OrderQuery;
import com.xxxx.crm.query.ProductQuery;
import com.xxxx.crm.service.OrderService;
import com.xxxx.crm.utils.LoginUserUtil;
import com.xxxx.crm.vo.Order;
import com.xxxx.crm.vo.OrderKey;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("order")
public class OrderController extends BaseController {

    @Resource
    private OrderService orderService;

    /*@PostMapping("pay_order")
    @ResponseBody
    public ResultInfo payOrder(HttpServletRequest request,@RequestParam("list")String orderString) throws IOException {
        Integer userId= LoginUserUtil.releaseUserIdFromCookie(request);
        List<Order> orderList=orderService.getOrders(userId,orderString);
        request.getSession().setAttribute("orderList",orderList);
        request.getSession().setAttribute("isOrder",0);
        return success("操作成功");
    }*/

    @PostMapping("pay_order")
    @ResponseBody
    public ResultInfo payOrder(HttpServletRequest request,Integer productId,Integer quantity) throws IOException {
        Integer userId= LoginUserUtil.releaseUserIdFromCookie(request);
        System.out.println(productId+"????????????");
        List<Order> orderList=orderService.getOrders(userId,productId,quantity);
        request.getSession().setAttribute("orderList",orderList);
        request.getSession().setAttribute("isOrder",0);
        return success("操作成功");
    }

    @PostMapping("pay_cart")
    @ResponseBody
    public ResultInfo payCart(HttpServletRequest request,@RequestParam("list")String orderString) throws IOException, ParseException {
        List<Order> orderList=orderService.getCart(orderString);
        request.getSession().setAttribute("orderList",orderList);
        request.getSession().setAttribute("isOrder",1);
        return success("操作成功");
    }

    /*@PostMapping("add_order")
    @ResponseBody
    public ResultInfo addOrder(HttpServletRequest request,@RequestParam("list")String orderString) throws IOException {
        Integer userId= LoginUserUtil.releaseUserIdFromCookie(request);
        orderService.insertOrder(userId,orderString);
        return success("操作成功");
    }*/

    @PostMapping("add_order")
    @ResponseBody
    public ResultInfo addOrder(HttpServletRequest request,Integer productId,Integer quantity) throws IOException {
        Integer userId= LoginUserUtil.releaseUserIdFromCookie(request);
        orderService.insertOrder(userId,productId,quantity);
        return success("操作成功");
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryProductByParams(HttpServletRequest request,OrderQuery orderQuery){
        Integer userId=LoginUserUtil.releaseUserIdFromCookie(request);
        orderQuery.setUserId(userId);
        return orderService.queryProductByParams(orderQuery);
    }

    @PostMapping("update_order")
    @ResponseBody
    public ResultInfo updateOrder(Order order) throws IOException, ParseException {
        //Integer userId= LoginUserUtil.releaseUserIdFromCookie(request);
        //Order order=new Order();
        //order.setUserId(orderKeyModel.getUserId());
        //order.setProductId(orderKeyModel.getProductId());
        //order.setPayDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(orderKeyModel.getPayDate()));
        //order.setQuantity(orderKeyModel.getQuantity());
        orderService.updateOrder(order);
        return success("操作成功");
    }

    @PostMapping("delete_order")
    @ResponseBody
    public ResultInfo deleteOrder(HttpServletRequest request,@RequestParam("list")String orderKeyList) throws IOException, ParseException {
        //Integer userId= LoginUserUtil.releaseUserIdFromCookie(request);
        System.out.println(orderKeyList);
        orderService.deleteOrder(orderKeyList);
        return success("操作成功");
    }

    @RequestMapping("index")
    public String index(){
        return "order/orders_checking";
    }

    @RequestMapping("toAddOrUpdatePage")
    public String toAddOrUpdatePage(HttpServletRequest request, OrderKey orderKey) throws ParseException {
        if(orderKey.getUserId()!=null&&orderKey.getProductId()!=null){
            //OrderKey orderKey=new OrderKey();
            //orderKey.setUserId(orderKeyModel.getUserId());
            //orderKey.setProductId(orderKeyModel.getProductId());
            //orderKey.setPayDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(orderKeyModel.getPayDate()));
            Order order=orderService.selectByPrimaryKey(orderKey);
            request.setAttribute("order",order);
        }
        return "order/add_update";
    }

    @RequestMapping("toAddCountPage")
    public String toAddCountPage(HttpServletRequest request,Integer productId,Integer isOrder){
        request.setAttribute("productId",productId);
        request.setAttribute("isOrder",isOrder);
        return "productDescription/add_count";
    }


}
