package com.xxxx.crm.controller;

import com.xxxx.crm.base.BaseController;
import com.xxxx.crm.query.EvaluationQuery;
import com.xxxx.crm.service.EvaluationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("evaluation")
public class EvaluationController extends BaseController {

    @Resource
    private EvaluationService evaluationService;

    @RequestMapping("index")
    public String index(){
        return "evaluation/evaluation_showing";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryEvaluationByParams(EvaluationQuery evaluationQuery){
        return evaluationService.queryEvaluationByParams(evaluationQuery);
    }
}
