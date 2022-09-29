package com.xxxx.crm.dao;

import com.xxxx.crm.base.BaseMapper;
import com.xxxx.crm.vo.ProductDescription;
import org.springframework.dao.DataAccessException;

public interface ProductDescriptionMapper extends BaseMapper<ProductDescription,Integer> {

    Integer updateBatch(Integer[] ids) throws DataAccessException;
}