package com.xxxx.crm.service;

import com.xxxx.crm.dao.AccountMapper;
import com.xxxx.crm.model.PaymentModel;
import com.xxxx.crm.utils.AssertUtil;
import com.xxxx.crm.vo.Account;
import com.xxxx.crm.vo.Credit;
import com.xxxx.crm.vo.Order;
import com.xxxx.crm.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccountPaymentService extends PaymentService<Account,String>{
    @Resource
    private AccountMapper accountMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void makePayment(Integer userId,PaymentModel paymentModel, List<Order> orderList,Double total) {
        /*
         *获取账号信息
         */
        Account account= (Account) checkUserParams(paymentModel.getUserName(),paymentModel.getUserPwd());
        /*
         *检查金额是否充足
         */
        AssertUtil.isTrue(account.getBalance()<total,"银行卡余额已不足");
        /*
         *判断商品内存是否充足
         */
        insertPayLogsBatch(userId,paymentModel,orderList);
        /*
         *支付金额
         */
        account.setBalance(account.getBalance()-total);

        int num=accountMapper.updateByPrimaryKeySelective(account);
        AssertUtil.isTrue(num<1,"支付失败");
    }

    @Override
    public Object checkUserParams(String userName, String userPwd) {
        AssertUtil.isTrue(StringUtils.isBlank(userName),"银行卡账号不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(userPwd),"银行卡密码不能为空!");
        Account account=accountMapper.queryUserByName(userName);
        AssertUtil.isTrue(account==null,"银行卡账号不存在!");
        AssertUtil.isTrue(!account.getAccountPassword().equals(userPwd),"银行卡密码不正确");
        return account;
    }
}
