package com.xxxx.crm.dao;

import com.xxxx.crm.base.BaseMapper;
import com.xxxx.crm.model.ClassificationModel;
import com.xxxx.crm.vo.Classification;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface ClassificationMapper extends BaseMapper<Classification,String> {
    @MapKey("classification")
    List<Map<String,Object>> queryAllClassifications();

    Integer updateByPrimaryKey(ClassificationModel classificationModel);
}