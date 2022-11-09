package com.xxxx.crm.service;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.crm.base.BaseService;
import com.xxxx.crm.dao.PayLogMapper;
import com.xxxx.crm.dao.UserMapper;
import com.xxxx.crm.model.EvaluationModel;
import com.xxxx.crm.model.PayLogKeyModel;
import com.xxxx.crm.model.PostSaleModel;
import com.xxxx.crm.query.PayLogQuery;
import com.xxxx.crm.utils.AssertUtil;
import com.xxxx.crm.vo.OrderKey;
import com.xxxx.crm.vo.PayLog;
import com.xxxx.crm.vo.PayLogKey;
import com.xxxx.crm.vo.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PayLogService extends BaseService<PayLog, PayLogKey> {
    @Resource
    private PayLogMapper payLogMapper;

    @Resource
    private UserMapper userMapper;


    public Map<String,Object> queryPayLogByParams(PayLogQuery payLogQuery){
        //return productService.queryProductByParams(productQuery);
        Map<String,Object> map=new HashMap<>();

        /*
         *开始分页
         */
        PageHelper.startPage(payLogQuery.getPage(),payLogQuery.getLimit());

        PageInfo<PayLog> pageInfo=new PageInfo<>(payLogMapper.selectByParams(payLogQuery));

        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());

        return map;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void processRefund(PayLogKeyModel payLogKeyModel) throws ParseException {
        PayLogKey payLogKey=transform(payLogKeyModel);
        PayLog payLog=payLogMapper.selectByPrimaryKey(payLogKey);
        AssertUtil.isTrue(!(payLog.getState().equals("未完成")||payLog.getState().equals("已完成")),"申请失败");
        payLog.setState("申请退款");
        payLog.setRefundReason(payLogKeyModel.getRefundReason());
        int num=payLogMapper.updateByPrimaryKeySelective(payLog);
        AssertUtil.isTrue(num!=1,"申请退款失败");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addEvaluation(EvaluationModel evaluationModel) throws ParseException {
        double score=evaluationModel.getScore();
        AssertUtil.isTrue(score<0||score>10,"评分不能小于0或不能大于10");
        PayLog payLog=transform(evaluationModel);
        int num=payLogMapper.updateByPrimaryKeySelective(payLog);
        AssertUtil.isTrue(num!=1,"申请退款失败");
    }

    private PayLogKey transform(PayLogKeyModel payLogKeyModel) throws ParseException {
        PayLogKey payLogKey=new PayLogKey();
        payLogKey.setUserId(payLogKeyModel.getUserId());
        payLogKey.setProductId(payLogKeyModel.getProductId());
        payLogKey.setContactId(payLogKeyModel.getContactId());
        payLogKey.setPayDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(payLogKeyModel.getPayDate()));
        return payLogKey;
    }

    private PayLog transform(EvaluationModel evaluationModel) throws ParseException {
        PayLog payLog=new PayLog();
        payLog.setUserId(evaluationModel.getUserId());
        payLog.setProductId(evaluationModel.getProductId());
        payLog.setContactId(evaluationModel.getContactId());
        payLog.setPayDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(evaluationModel.getPayDate()));
        payLog.setScore(evaluationModel.getScore());
        payLog.setEvaluation(evaluationModel.getEvaluation());
        return payLog;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void processState(String postSaleModelString) throws IOException, ParseException {
        List<PayLog> payLogList=transform(postSaleModelString);
        for(PayLog payLog:payLogList){
            processState(payLog);
        }
    }
    private void processState(PayLog payLog){
        switch (payLog.getState()){
            case "允许退款":processApprove(payLog);break;
            case "拒绝退款": processRefuse(payLog);break;
            case "已完成": processComplete(payLog);break;
        }
    }
    private void processComplete(PayLog newPayLog){
        PayLog oldPayLog=payLogMapper.selectByPrimaryKey(newPayLog);
        AssertUtil.isTrue(payLogMapper==null,"无此订单记录");
        AssertUtil.isTrue(!oldPayLog.getState().equals("未完成"),"该订单不是处于未完成状态");
        int num=payLogMapper.updateByPrimaryKeySelective(newPayLog);
        AssertUtil.isTrue(num!=1,"操作失败");
    }
    private void processApprove(PayLog newPayLog){
        PayLog oldPayLog=payLogMapper.selectByPrimaryKey(newPayLog);
        AssertUtil.isTrue(payLogMapper==null,"无此订单记录");
        AssertUtil.isTrue(!oldPayLog.getState().equals("申请退款"),"该订单不是处于申请退款状态");
        User user=userMapper.selectByPrimaryKey(newPayLog.getUserId());
        AssertUtil.isTrue(payLogMapper==null,"无此用户信息");
        user.setBalance(user.getBalance()+oldPayLog.getTaxTotal());
        int num=userMapper.updateByPrimaryKeySelective(user);
        AssertUtil.isTrue(num!=1,"操作失败");
        num=payLogMapper.updateByPrimaryKeySelective(newPayLog);
        AssertUtil.isTrue(num!=1,"操作失败");
    }
    private void processRefuse(PayLog newPayLog){
        PayLog oldPayLog=payLogMapper.selectByPrimaryKey(newPayLog);
        AssertUtil.isTrue(payLogMapper==null,"无此订单记录");
        AssertUtil.isTrue(!oldPayLog.getState().equals("申请退款"),"该订单不是处于申请退款状态");
        int num=payLogMapper.updateByPrimaryKeySelective(newPayLog);
        AssertUtil.isTrue(num!=1,"操作失败");
    }

    private List<PayLog> transform(String postSaleModelString) throws IOException, ParseException {
        ObjectMapper mapper = new ObjectMapper();
        JavaType jt = mapper.getTypeFactory().constructParametricType(ArrayList.class, PostSaleModel.class);
        List<PostSaleModel> postSaleModelList =  (List<PostSaleModel>)mapper.readValue(postSaleModelString, jt);
        return transform(postSaleModelList);
    }

    private List<PayLog> transform(List<PostSaleModel> postSaleModelList) throws IOException, ParseException {
        List<PayLog> payLogList=new ArrayList<>();
        for(PostSaleModel postSaleModel:postSaleModelList){
            PayLog payLog=new PayLog();
            payLog.setUserId(postSaleModel.getUserId());
            payLog.setProductId(postSaleModel.getProductId());
            payLog.setContactId(postSaleModel.getContactId());
            payLog.setPayDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(postSaleModel.getPayDate()));
            payLog.setState(postSaleModel.getState());
            payLogList.add(payLog);
        }
        return payLogList;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteEvaluation(String payLogKeyString) throws IOException, ParseException {
        ObjectMapper mapper = new ObjectMapper();
        JavaType jt = mapper.getTypeFactory().constructParametricType(ArrayList.class, PayLogKey.class);
        List<PayLogKey> payLogKeyList =  (List<PayLogKey>)mapper.readValue(payLogKeyString, jt);

        //List<OrderKey> orderKeys=transform(list);
        PayLogKey payLogKey=payLogKeyList.get(0);


        int num=payLogMapper.deleteEvaluation(payLogKey);

        AssertUtil.isTrue(num==0,"删除失败");
    }
}
