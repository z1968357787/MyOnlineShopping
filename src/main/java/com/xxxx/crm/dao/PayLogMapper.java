package com.xxxx.crm.dao;

import com.xxxx.crm.base.BaseMapper;
import com.xxxx.crm.vo.PayLog;
import com.xxxx.crm.vo.PayLogKey;

public interface PayLogMapper extends BaseMapper<PayLog,PayLogKey> {
    Integer deleteEvaluation(PayLogKey payLogKey);
}