package com.xxxx.crm.service;

import com.xxxx.crm.base.BaseService;
import com.xxxx.crm.dao.ClassificationMapper;
import com.xxxx.crm.dao.ProductDescriptionMapper;
import com.xxxx.crm.model.ClassificationModel;
import com.xxxx.crm.query.ProductQuery;
import com.xxxx.crm.vo.Classification;
import com.xxxx.crm.vo.ProductDescription;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ClassificationService extends BaseService<Classification,String> {
    @Resource
    private ClassificationMapper classificationMapper;

    @Resource
    private ProductDescriptionMapper productDescriptionMapper;

    public List<Map<String,Object>> queryAllClassifications(){
        return classificationMapper.queryAllClassifications();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addClassification(Classification classification){
        classificationMapper.insertSelective(classification);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteClassification(String classification){
        ProductQuery productQuery=new ProductQuery();
        productQuery.setClassification(classification);
        List<ProductDescription> productDescriptions=productDescriptionMapper.selectByParams(productQuery);
        Integer ids[]=new Integer[productDescriptions.size()];
        int i=0;
        for(ProductDescription  productDescription:productDescriptions){
            ids[i]=productDescription.getProductId();
            i++;
        }
        productDescriptionMapper.updateBatch(ids);
        classificationMapper.deleteByPrimaryKey(classification);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateClassification(ClassificationModel classificationModel){
        classificationMapper.updateByPrimaryKey(classificationModel);
    }

}
