package com.xxxx.crm.service;

import com.xxxx.crm.dao.CreditMapper;
import com.xxxx.crm.model.MoneyModel;
import com.xxxx.crm.model.PaymentModel;
import com.xxxx.crm.utils.AssertUtil;
import com.xxxx.crm.vo.Credit;
import com.xxxx.crm.vo.Order;
import com.xxxx.crm.vo.PayLog;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CreditPaymentService extends PaymentService<Credit,String>{
    @Resource
    private CreditMapper creditMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void makePayment(Integer userId, PaymentModel paymentModel, List<Order> orderList, MoneyModel moneyModel, List<PayLog> payLogList) {
        /*
         *获取账号信息
         */
        Credit credit= (Credit) checkUserParams(paymentModel.getUserName(),paymentModel.getUserPwd());
        /*
         *检查金额是否充足
         */
        AssertUtil.isTrue(credit.getCreditScore()<80,"用户信誉分数太低");
        /*
         *判断商品内存是否充足
         */
        insertPayLogsBatch(userId,paymentModel,orderList,payLogList,moneyModel);
        /*
         *支付金额
         */
        credit.setLoan(credit.getLoan()+moneyModel.getTaxTotal());

        int num=creditMapper.updateByPrimaryKeySelective(credit);
        AssertUtil.isTrue(num<1,"支付失败");
    }

    @Override
    public Object checkUserParams(String userName, String userPwd) {
        AssertUtil.isTrue(StringUtils.isBlank(userName),"信用卡账号不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(userPwd),"信用卡密码不能为空!");
        Credit credit=creditMapper.queryUserByName(userName);
        AssertUtil.isTrue(credit==null,"信用卡账号不存在!");
        AssertUtil.isTrue(!credit.getCreditPassword().equals(userPwd),"信用卡密码不正确");
        return credit;
    }
}
