package com.xxxx.crm.service;

import com.xxxx.crm.dao.UserMapper;
import com.xxxx.crm.model.PaymentModel;
import com.xxxx.crm.utils.AssertUtil;
import com.xxxx.crm.vo.Order;
import com.xxxx.crm.vo.PayLog;
import com.xxxx.crm.vo.ProductDescription;
import com.xxxx.crm.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CashPaymentService extends PaymentService<User,Integer>{
    @Resource
    private UserMapper userMapper;


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void makePayment(Integer userId,PaymentModel paymentModel, List<Order> orderList,Double total) {
        /*
         *获取账号信息
         */
        User user= (User) checkUserParams(paymentModel.getUserName(),paymentModel.getUserPwd());
        AssertUtil.isTrue(!user.getId().equals(userId),"账号与用户id不对应");
        /*
         *检查金额是否充足
         */
        AssertUtil.isTrue(user.getBalance()<total,"用户余额已不足");
        /*
         *判断商品内存是否充足
         */
        insertPayLogsBatch(userId,paymentModel,orderList);
        /*
         *支付金额
         */
        user.setBalance(user.getBalance()-total);

        int num=userMapper.updateByPrimaryKeySelective(user);
        AssertUtil.isTrue(num<1,"支付失败");

    }

    @Override
    public Object checkUserParams(String userName, String userPwd) {
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户账号不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(userPwd),"用户密码不能为空!");
        User user=userMapper.queryUserByName(userName);
        AssertUtil.isTrue(user==null,"用户姓名不存在!");
        AssertUtil.isTrue(!user.getUserPwd().equals(userPwd),"用户密码不正确");
        return user;
    }


}
