package com.xxxx.crm.service;

import com.xxxx.crm.base.BaseService;
import com.xxxx.crm.dao.OrderMapper;
import com.xxxx.crm.dao.PayLogMapper;
import com.xxxx.crm.dao.ProductDescriptionMapper;
import com.xxxx.crm.model.PaymentModel;
import com.xxxx.crm.utils.AssertUtil;
import com.xxxx.crm.vo.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public abstract class PaymentService<T,TD> extends BaseService<T,TD>{

    @Resource
    protected OrderMapper orderMapper;

    @Resource
    protected PayLogMapper payLogMapper;

    @Resource
    protected ProductDescriptionMapper productDescriptionMapper;

    public abstract void makePayment(Integer userId,PaymentModel paymentModel, List<Order> orderList,Double total);

    public abstract Object checkUserParams(String userName, String userPwd);

    private void checkProductAmple(List<Order> orderList){
        List<ProductDescription> productDescriptionList=new ArrayList<>();
        for(Order order:orderList){
            ProductDescription productDescription=productDescriptionMapper.selectByPrimaryKey(order.getProductId());
            AssertUtil.isTrue(productDescription.getStock()<order.getQuantity(),"商品库存已不足");
            productDescription.setStock(productDescription.getStock()-order.getQuantity());
            productDescriptionList.add(productDescription);
            //productDescriptionMapper.updateByPrimaryKeySelective(productDescription);
        }
        for(ProductDescription productDescription:productDescriptionList){
            productDescriptionMapper.updateByPrimaryKeySelective(productDescription);
        }

    }

    public void deleteOrders(List<Order> orderList){
        List<OrderKey> orderKeyList=new ArrayList<>();
        for(Order order:orderList){
            OrderKey orderKey=order;
            orderKeyList.add(orderKey);
        }
        orderMapper.deleteBatch(orderKeyList);
    }


    protected void insertPayLogsBatch(Integer userId,PaymentModel paymentModel,List<Order> orderList){
        checkProductAmple(orderList);
        List<PayLog> payLogList=new ArrayList<>();
        for(Order order:orderList){
            PayLog payLog=new PayLog();
            payLog.setUserId(userId);
            payLog.setProductId(order.getProductId());
            payLog.setContactId(paymentModel.getContactId());
            payLog.setPayDate(new Date());
            payLog.setPayMode(paymentModel.getPayMode());
            payLog.setAddress(paymentModel.getAddress());
            payLog.setPhone(paymentModel.getPhone());
            payLog.setContactMan(paymentModel.getContactMan());
            payLog.setProductName(order.getProductName());
            payLog.setQuantity(order.getQuantity());
            payLog.setSubtotal(order.getSubtotal());
            payLog.setPrice(order.getPrice());
            payLogList.add(payLog);
        }
        int num=payLogMapper.insertBatch(payLogList);
        AssertUtil.isTrue(num<1,"支付失败");
        deleteOrders(orderList);
    }


}
