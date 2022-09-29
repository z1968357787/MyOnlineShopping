package com.xxxx.crm.dao;

import com.xxxx.crm.base.BaseMapper;
import com.xxxx.crm.vo.Account;

public interface AccountMapper extends BaseMapper<Account,String> {
    Account queryUserByName(String userName);
}