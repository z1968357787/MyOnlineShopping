package com.xxxx.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.crm.base.BaseService;
import com.xxxx.crm.dao.DiscountMapper;
import com.xxxx.crm.dao.DiscountUserMapper;
import com.xxxx.crm.query.DiscountQuery;
import com.xxxx.crm.utils.AssertUtil;
import com.xxxx.crm.vo.Discount;
import com.xxxx.crm.vo.DiscountUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class DiscountService extends BaseService<Discount,Integer> {
    @Resource
    private DiscountMapper discountMapper;

    @Resource
    private DiscountUserMapper discountUserMapper;

    public Map<String,Object> queryDiscountByParams(DiscountQuery discountQuery){
        Map<String,Object> map=new HashMap<>();

        /*
         *开始分页
         */

        PageHelper.startPage(discountQuery.getPage(),discountQuery.getLimit());

        PageInfo<Discount> pageInfo=new PageInfo<>(discountMapper.selectByParams(discountQuery));

        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());

        return map;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void assignDiscount(DiscountUser newDiscountUser) {
        DiscountUser oldDiscountUser=discountUserMapper.selectByPrimaryKey(newDiscountUser);
        if(oldDiscountUser==null){
            int num=discountUserMapper.insertSelective(newDiscountUser);
            AssertUtil.isTrue(num!=1,"发配失败");
        }else {
            oldDiscountUser.setCount(oldDiscountUser.getCount()+newDiscountUser.getCount());
            int num=discountUserMapper.updateByPrimaryKeySelective(oldDiscountUser);
            AssertUtil.isTrue(num!=1,"发配失败");
        }
    }
}
