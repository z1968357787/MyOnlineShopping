package com.xxxx.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.crm.base.BaseService;
import com.xxxx.crm.dao.AccountMapper;
import com.xxxx.crm.dao.TransferLogMapper;
import com.xxxx.crm.dao.UserMapper;
import com.xxxx.crm.model.TransferLogModel;
import com.xxxx.crm.query.OrderQuery;
import com.xxxx.crm.query.TransferLogQuery;
import com.xxxx.crm.utils.AssertUtil;
import com.xxxx.crm.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TransferLogService extends BaseService<TransferLog, TransferLogKey> {

    @Resource
    private TransferLogMapper transferLogMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private AccountMapper accountMapper;

    public Map<String,Object> queryProductByParams(TransferLogQuery transferLogQuery){
        //return productService.queryProductByParams(productQuery);
        Map<String,Object> map=new HashMap<>();

        /*
         *开始分页
         */

        PageHelper.startPage(transferLogQuery.getPage(),transferLogQuery.getLimit());

        PageInfo<TransferLog> pageInfo=new PageInfo<>(transferLogMapper.selectByParams(transferLogQuery));

        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());

        return map;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void transferMoney(Integer userId,TransferLogModel transferLogModel){
        User user=checkUserParams(transferLogModel.getUserName(),transferLogModel.getUserPwd());
        AssertUtil.isTrue(!user.getId().equals(userId),"账号与用户id不对应");
        Account account=checkAccountParams(transferLogModel.getAccountNumber(),transferLogModel.getAccountPassword());

        if(transferLogModel.getTransferMode().equals("充值")){
            AssertUtil.isTrue(account.getBalance()<transferLogModel.getMoney(),"银行卡余额不足");
            account.setBalance(account.getBalance()-transferLogModel.getMoney());
            user.setBalance(user.getBalance()+transferLogModel.getMoney());
        }else {
            AssertUtil.isTrue(user.getBalance()<transferLogModel.getMoney(),"钱包余额不足");
            account.setBalance(account.getBalance()+transferLogModel.getMoney());
            user.setBalance(user.getBalance()-transferLogModel.getMoney());
        }

        TransferLog transferLog=transform(userId,transferLogModel,account.getCardHolder());
        int num=0;

        num=transferLogMapper.insertSelective(transferLog);
        AssertUtil.isTrue(num!=1,"插入失败");

        num=userMapper.updateByPrimaryKeySelective(user);
        AssertUtil.isTrue(num!=1,"更新User失败");

        num=accountMapper.updateByPrimaryKeySelective(account);
        AssertUtil.isTrue(num!=1,"更新Account失败");
    }

    private User checkUserParams(String userName, String userPwd) {
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户账号不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(userPwd),"用户密码不能为空!");
        User user=userMapper.queryUserByName(userName);
        AssertUtil.isTrue(user==null,"用户姓名不存在!");
        AssertUtil.isTrue(!user.getUserPwd().equals(userPwd),"用户密码不正确");
        return user;
    }

    private Account checkAccountParams(String userName, String userPwd) {
        AssertUtil.isTrue(StringUtils.isBlank(userName),"银行账号不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(userPwd),"银行密码不能为空!");
        Account account=accountMapper.queryUserByName(userName);
        AssertUtil.isTrue(account==null,"银行账号不存在!");
        AssertUtil.isTrue(!account.getAccountPassword().equals(userPwd),"银行密码不正确");
        return account;
    }

    private TransferLog transform(Integer userId,TransferLogModel transferLogModel,String cardHolder){
        TransferLog transferLog=new TransferLog();
        transferLog.setUserId(userId);
        transferLog.setAccountNumber(transferLogModel.getAccountNumber());
        transferLog.setTransferMode(transferLogModel.getTransferMode());
        transferLog.setCardHolder(cardHolder);
        transferLog.setMoney(transferLog.getMoney());
        transferLog.setTransferDate(new Date());
        transferLog.setMoney(transferLogModel.getMoney());
        return transferLog;
    }
}
