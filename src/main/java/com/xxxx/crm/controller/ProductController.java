package com.xxxx.crm.controller;

import com.xxxx.crm.base.BaseController;
import com.xxxx.crm.base.ResultInfo;
import com.xxxx.crm.query.ProductQuery;
import com.xxxx.crm.service.ProductService;
import com.xxxx.crm.vo.Contact;
import com.xxxx.crm.vo.ProductDescription;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("product_description")
public class ProductController extends BaseController {

    @Resource
    private ProductService productService;

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryProductByParams(ProductQuery productQuery){
        return productService.queryProductByParams(productQuery);
    }

    @RequestMapping("index")
    public String index(){
        return "productDescription/product_description";
    }

    @RequestMapping("management")
    public String management(){
        return "productManagement/product_description";
    }

    @RequestMapping("addOrUpdateProductPage")
    public String addOrUpdateContactPage(HttpServletRequest request, Integer productId){
        if(productId!=null){
            ProductDescription productDescription=productService.selectByPrimaryKey(productId);
            request.setAttribute("product",productDescription);
        }
        return "productManagement/add_update";
    }

    @PostMapping("save")
    @ResponseBody
    public ResultInfo insertProduct(ProductDescription productDescription) throws IOException {
        productService.insertProduct(productDescription);
        return success("添加成功");
    }

    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateProduct(ProductDescription productDescription) throws IOException {
        productService.updateProduct(productDescription);
        return success("更新成功");
    }

    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteProduct(Integer ids[]) throws IOException {
        productService.deleteProduct(ids);
        return success("删除成功");
    }

}
