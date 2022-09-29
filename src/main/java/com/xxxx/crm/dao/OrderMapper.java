package com.xxxx.crm.dao;

import com.xxxx.crm.base.BaseMapper;
import com.xxxx.crm.vo.Order;
import com.xxxx.crm.vo.OrderKey;

import java.util.List;

public interface OrderMapper extends BaseMapper<Order,OrderKey> {
    int deleteBatch(List<OrderKey> orderKeyList);
}