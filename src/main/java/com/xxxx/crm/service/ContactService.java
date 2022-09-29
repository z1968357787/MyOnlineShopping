package com.xxxx.crm.service;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.crm.base.BaseService;
import com.xxxx.crm.dao.ContactMapper;
import com.xxxx.crm.model.OrderModel;
import com.xxxx.crm.query.ContactQuery;
import com.xxxx.crm.utils.AssertUtil;
import com.xxxx.crm.vo.Contact;
import com.xxxx.crm.vo.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ContactService extends BaseService<Contact,Integer> {

    @Resource
    private ContactMapper contactMapper;

    public Map<String,Object> queryContactByParams(ContactQuery contactQuery){
        //return productService.queryProductByParams(productQuery);
        Map<String,Object> map=new HashMap<>();

        /*
         *开始分页
         */

        PageHelper.startPage(contactQuery.getPage(),contactQuery.getLimit());

        PageInfo<Contact> pageInfo=new PageInfo<>(contactMapper.selectByParams(contactQuery));

        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());

        return map;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertContact(Contact contact) throws IOException {
        /*
         * 逻辑判断
         */
        CheckContactParams(contact);

        /*
         *插入数据
         */
        int num=contactMapper.insertSelective(contact);
        AssertUtil.isTrue(num!=1,"插入失败");
    }

    private void CheckContactParams(Contact contact) {
        AssertUtil.isTrue(contact.getPhone()==null,"联系电话不能为空");
        AssertUtil.isTrue(contact.getAddress()==null,"联系地址不能为空");
        AssertUtil.isTrue(contact.getContactMan()==null,"联系人不能为空");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateContact(Contact contact) throws IOException {
        /*
         *插入数据
         */
        int num=contactMapper.updateByPrimaryKeySelective(contact);

        AssertUtil.isTrue(num!=1,"更新失败");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteContact(Integer contactIds[]) throws IOException {
        /*
         *删除数据
         */

        int num=contactMapper.deleteBatch(contactIds);

        AssertUtil.isTrue(num<1,"删除失败");
    }

}
