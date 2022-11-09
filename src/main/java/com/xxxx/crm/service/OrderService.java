package com.xxxx.crm.service;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.crm.adapter.ITaxCalculatorAdapter;
import com.xxxx.crm.base.BaseService;
import com.xxxx.crm.dao.DiscountUserMapper;
import com.xxxx.crm.dao.OrderMapper;
import com.xxxx.crm.dao.ProductDescriptionMapper;
import com.xxxx.crm.dao.TaxCalculatorMapper;
import com.xxxx.crm.factory.ServicesFactory;
import com.xxxx.crm.model.MoneyModel;
import com.xxxx.crm.model.OrderModel;
import com.xxxx.crm.query.OrderQuery;
import com.xxxx.crm.strategy.ISalePricingStrategy;
import com.xxxx.crm.utils.AssertUtil;
import com.xxxx.crm.vo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@Service
public class OrderService extends BaseService<Order,OrderKey> {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private ProductDescriptionMapper productDescriptionMapper;

    @Resource
    private TaxCalculatorMapper taxCalculatorMapper;

    private ISalePricingStrategy salePricingStrategy;

    private ITaxCalculatorAdapter taxCalculatorAdapter;

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateOrder(Order newOrder){
        AssertUtil.isTrue(newOrder.getProductId()==null,"该商品号不存在");
        AssertUtil.isTrue(newOrder.getQuantity()<=0,"商品数量小于为0，请重输");
        ProductDescription productDescription=productDescriptionMapper.selectByPrimaryKey(newOrder.getProductId());
        AssertUtil.isTrue(newOrder.getQuantity()>productDescription.getStock(),"库存数量不够，无法进行更改");
        newOrder.setSubtotal(productDescription.getPrice()*newOrder.getQuantity());
        int num=orderMapper.updateByPrimaryKeySelective(newOrder);
        AssertUtil.isTrue(num!=1,"更新失败");
    }

    public List<Order> getOrders(Integer userId,OrderModel orderModel){
        Integer productId=orderModel.getProductId();
        Integer quantity=orderModel.getQuantity();
        ProductDescription productDescription=productDescriptionMapper.selectByPrimaryKey(productId);
        Order order=transform(userId,productDescription,quantity);
        List<Order> orderList=new ArrayList<>();
        orderList.add(order);
        return orderList;
    }

    private Order transform(Integer userId,ProductDescription productDescription,Integer quantity){
        AssertUtil.isTrue(quantity<=0,"购物数量小于0，请重选！");
        AssertUtil.isTrue(productDescription.getStock()<quantity,"商品库存已不足");
        Order order=new Order();
        order.setUserId(userId);
        order.setProductId(productDescription.getProductId());
        order.setProductName(productDescription.getProductName());
        order.setPrice(productDescription.getPrice());
        order.setQuantity(quantity);
        order.setSubtotal(productDescription.getPrice()*quantity);
        return order;
    }

    /*
    public List<Order> getOrders(Integer userId,String orderString) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(orderString);
        JavaType jt = mapper.getTypeFactory().constructParametricType(ArrayList.class, OrderModel.class);
        //List<OrderModel> list =  (List<OrderModel>)mapper.readValue(orderModelList, jt);
        List<OrderModel> list =  (List<OrderModel>)mapper.readValue(orderString, jt);

        List<Order> orderList=transform(userId,list);

        return orderList;
    }*/
    /*
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertOrder(Integer userId,String orderString) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(orderString);
        JavaType jt = mapper.getTypeFactory().constructParametricType(ArrayList.class, OrderModel.class);
        //List<OrderModel> list =  (List<OrderModel>)mapper.readValue(orderModelList, jt);
        List<OrderModel> list =  (List<OrderModel>)mapper.readValue(orderString, jt);

        List<Order> orderList=transform(userId,list);

        List<Order> newOrderList=checkDuplicateProducts(orderList);

        if(newOrderList.size()!=0){
            orderMapper.insertBatch(newOrderList);
        }
    }*/
    /*
    private List<Order> checkDuplicateProducts(List<Order> orderList){
        List<Order> newOrderList=new ArrayList<>();
        for(Order newOrder:orderList){
            Order oldOlder=orderMapper.selectByPrimaryKey(newOrder);
            if(oldOlder==null){
                newOrderList.add(newOrder);
            }else {
                newOrder.setQuantity(newOrder.getQuantity()+oldOlder.getQuantity());
                newOrder.setSubtotal(newOrder.getSubtotal()+oldOlder.getSubtotal());
                orderMapper.updateByPrimaryKeySelective(newOrder);
            }
        }
        return newOrderList;
    }*/


    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteOrder(String orderKeyString) throws IOException, ParseException {
        ObjectMapper mapper = new ObjectMapper();
        JavaType jt = mapper.getTypeFactory().constructParametricType(ArrayList.class, OrderKey.class);
        List<OrderKey> orderKeyList =  (List<OrderKey>)mapper.readValue(orderKeyString, jt);

        //List<OrderKey> orderKeys=transform(list);

        int num=orderMapper.deleteBatch(orderKeyList);

        AssertUtil.isTrue(num==0,"删除失败");
    }

    public List<Order> getCart(String orderString) throws IOException, ParseException {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(orderString);
        JavaType jt = mapper.getTypeFactory().constructParametricType(ArrayList.class, Order.class);
        List<Order> list =  (List<Order>)mapper.readValue(orderString, jt);

        return list;
    }
    /*private List<OrderKey> transform(List<OrderKeyModel> list) throws ParseException {
        List<OrderKey> orderKeyList=new ArrayList<>();
        for(OrderKeyModel orderKeyModel:list){
            OrderKey orderKey=new OrderKey();
            orderKey.setUserId(orderKeyModel.getUserId());
            orderKey.setProductId(orderKeyModel.getProductId());
            //orderKey.setPayDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(orderKeyModel.getPayDate()));
            orderKeyList.add(orderKey);
        }
        return orderKeyList;
    }*/

    /*
    private List<Order> transform(Integer userId, List<OrderModel> list) {
        List<Order> orderList=new ArrayList<>();
        for(OrderModel orderModel:list){
            Order order=new Order();
            order.setUserId(userId);
            order.setProductId(orderModel.getProductId());
            order.setProductName(orderModel.getProductName());
            order.setPrice(orderModel.getPrice());
            AssertUtil.isTrue(orderModel.getQuantity()==null,"购物数量为0，请重选！");
            AssertUtil.isTrue(orderModel.getQuantity()>orderModel.getStock(),"库存剩余量不足！");
            order.setQuantity(orderModel.getQuantity());
            order.setSubtotal(orderModel.getPrice()*orderModel.getQuantity());
            orderList.add(order);
        }
        return orderList;
    }*/

    public Map<String,Object> queryProductByParams(OrderQuery orderQuery){
        //return productService.queryProductByParams(productQuery);
        Map<String,Object> map=new HashMap<>();

        /*
         *开始分页
         */

        PageHelper.startPage(orderQuery.getPage(),orderQuery.getLimit());

        PageInfo<Order> pageInfo=new PageInfo<>(orderMapper.selectByParams(orderQuery));

        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());

        return map;
    }

    public Order selectByPrimaryKey(OrderKey orderKey){
        return orderMapper.selectByPrimaryKey(orderKey);
    }

    public MoneyModel getTotal(User user, List<Order> orderList, List<DiscountUser> discountUserList, List<PayLog> payLogList){
        checkDiscountUsers(discountUserList);
        MoneyModel moneyModel=new MoneyModel();
        Double total=0.0;
        for(Order order:orderList){
            PayLog payLog=new PayLog();
            payLog.setProductId(order.getProductId());
            payLog.setPayDate(new Date());
            payLog.setProductName(order.getProductName());
            payLog.setQuantity(order.getQuantity());
            payLog.setSubtotal(order.getSubtotal());
            payLog.setPrice(order.getPrice());
            payLogList.add(payLog);
            total+=order.getSubtotal();
        }
        moneyModel.setSumTotal(total);
        /*
         *计算优惠
         */
        if(discountUserList!=null){
            salePricingStrategy=ServicesFactory.getInstance().getSalePricingStrategy(discountUserList);
            salePricingStrategy.getTotal(user,payLogList,moneyModel);
        }
        /*
         *计算税金
         */
        List<TaxCalculator> taxCalculatorList =taxCalculatorMapper.selectByState();
        if(taxCalculatorList !=null){
            taxCalculatorAdapter=ServicesFactory.getInstance().getTaxCalculatorAdapter(taxCalculatorList);
            taxCalculatorAdapter.getTaxes(user,payLogList,moneyModel);
        }
        return moneyModel;
    }

    private void checkDiscountUsers(List<DiscountUser> discountUserList){
        for(DiscountUser discountUser:discountUserList){
            AssertUtil.isTrue(discountUser.getCount()==0,"优惠券数量为0");
        }

    }

    public void insertOrder(Integer userId, OrderModel orderModel) {
        Integer productId=orderModel.getProductId();
        Integer quantity=orderModel.getQuantity();
        ProductDescription productDescription=productDescriptionMapper.selectByPrimaryKey(productId);
        Order order=transform(userId,productDescription,quantity);
        Order oldOlder=orderMapper.selectByPrimaryKey(order);
        int num;
        if(oldOlder==null){
            num=orderMapper.insertSelective(order);
        }else {
            AssertUtil.isTrue(order.getQuantity()>productDescription.getStock(),"库存数量不够，无法进行更改");
            order.setQuantity(order.getQuantity()+oldOlder.getQuantity());
            order.setSubtotal(order.getSubtotal()+oldOlder.getSubtotal());
            num=orderMapper.updateByPrimaryKeySelective(order);
        }
        AssertUtil.isTrue(num!=1,"加入失败");
    }
}
