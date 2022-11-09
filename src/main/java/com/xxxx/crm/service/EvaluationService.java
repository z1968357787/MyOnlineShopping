package com.xxxx.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.crm.base.BaseService;
import com.xxxx.crm.dao.EvaluationMapper;
import com.xxxx.crm.query.EvaluationQuery;
import com.xxxx.crm.query.PayLogQuery;
import com.xxxx.crm.vo.Evaluation;
import com.xxxx.crm.vo.PayLog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class EvaluationService extends BaseService<Evaluation, Evaluation> {
    @Resource
    private EvaluationMapper evaluationMapper;

    public Map<String,Object> queryEvaluationByParams(EvaluationQuery evaluationQuery){
        //return productService.queryProductByParams(productQuery);
        Map<String,Object> map=new HashMap<>();

        /*
         *开始分页
         */
        PageHelper.startPage(evaluationQuery.getPage(),evaluationQuery.getLimit());

        PageInfo<Evaluation> pageInfo=new PageInfo<>(evaluationMapper.selectByParams(evaluationQuery));

        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());

        return map;
    }
}
