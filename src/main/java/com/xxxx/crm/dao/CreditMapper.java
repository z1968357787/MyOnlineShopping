package com.xxxx.crm.dao;

import com.xxxx.crm.base.BaseMapper;
import com.xxxx.crm.vo.Credit;

public interface CreditMapper extends BaseMapper<Credit,String> {
    Credit queryUserByName(String userName);
}