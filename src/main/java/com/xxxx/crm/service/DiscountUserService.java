package com.xxxx.crm.service;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.crm.base.BaseService;
import com.xxxx.crm.dao.DiscountUserMapper;
import com.xxxx.crm.model.OrderModel;
import com.xxxx.crm.query.DiscountQuery;
import com.xxxx.crm.query.ProductQuery;
import com.xxxx.crm.vo.DiscountUser;
import com.xxxx.crm.vo.DiscountUserKey;
import com.xxxx.crm.vo.Order;
import com.xxxx.crm.vo.ProductDescription;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DiscountUserService extends BaseService<DiscountUser, DiscountUserKey> {
    @Resource
    private DiscountUserMapper discountUserMapper;

    public Map<String,Object> queryDiscountByParams(DiscountQuery discountQuery){
        Map<String,Object> map=new HashMap<>();

        /*
         *开始分页
         */

        PageHelper.startPage(discountQuery.getPage(),discountQuery.getLimit());

        PageInfo<DiscountUser> pageInfo=new PageInfo<>(discountUserMapper.selectByParams(discountQuery));

        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());

        return map;
    }

    public List<DiscountUser> transform(String discountUserString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(discountUserString);
        JavaType jt = mapper.getTypeFactory().constructParametricType(ArrayList.class, DiscountUser.class);
        List<DiscountUser> discountUserList =  (List<DiscountUser>)mapper.readValue(discountUserString, jt);
        /*
         *插入数据
         */
        return discountUserList;
    }
}
