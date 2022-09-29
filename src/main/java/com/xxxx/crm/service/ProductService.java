package com.xxxx.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xxxx.crm.base.BaseService;
import com.xxxx.crm.dao.ProductDescriptionMapper;
import com.xxxx.crm.query.ProductQuery;
import com.xxxx.crm.utils.AssertUtil;
import com.xxxx.crm.vo.Contact;
import com.xxxx.crm.vo.ProductDescription;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProductService extends BaseService<ProductDescription,Integer> {

    @Resource
    private ProductDescriptionMapper productDescriptionMapper;

    /*
     *满足的格式必须满足layui中数据表格的格式
     */
    public Map<String,Object> queryProductByParams(ProductQuery productQuery){
        Map<String,Object> map=new HashMap<>();

        /*
         *开始分页
         */

        PageHelper.startPage(productQuery.getPage(),productQuery.getLimit());

        PageInfo<ProductDescription> pageInfo=new PageInfo<>(productDescriptionMapper.selectByParams(productQuery));

        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());

        return map;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertProduct(ProductDescription productDescription) throws IOException {
        /*
         * 逻辑判断
         */
        CheckProductParams(productDescription);

        /*
         *插入数据
         */
        int num=productDescriptionMapper.insertSelective(productDescription);
        AssertUtil.isTrue(num!=1,"插入失败");
    }

    private void CheckProductParams(ProductDescription productDescription) {
        AssertUtil.isTrue(productDescription.getProductName()==null,"商品名不能为空");
        AssertUtil.isTrue(productDescription.getProductDescription()==null,"商品描述不能为空");
        AssertUtil.isTrue(productDescription.getPrice()==null,"商品单价不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(productDescription.getClassification()),"商品分类不能为空");
        AssertUtil.isTrue(productDescription.getSupplier()==null,"商品供应商不能为空");
        AssertUtil.isTrue(productDescription.getListTime()==null,"上架时间不能为空");
        AssertUtil.isTrue(productDescription.getStock()==null,"商品库存不能为空");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateProduct(ProductDescription productDescription) throws IOException {
        /*
         *更新数据
         */
        int num=productDescriptionMapper.updateByPrimaryKeySelective(productDescription);

        AssertUtil.isTrue(num!=1,"更新失败");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteProduct(Integer productIds[]) throws IOException {
        /*
         *删除数据
         */
        int num=productDescriptionMapper.deleteBatch(productIds);

        AssertUtil.isTrue(num<1,"删除失败");
    }
}
