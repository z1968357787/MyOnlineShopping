package com.xxxx.crm.controller;

import com.xxxx.crm.base.BaseController;
import com.xxxx.crm.base.ResultInfo;
import com.xxxx.crm.model.ClassificationModel;
import com.xxxx.crm.service.ClassificationService;
import com.xxxx.crm.vo.Classification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("classification")
public class ClassificationController extends BaseController {
    @Resource
    private ClassificationService classificationService;

    @RequestMapping("queryAllClassifications")
    @ResponseBody
    public List<Map<String,Object>> queryAllClassifications(){
        return classificationService.queryAllClassifications();
    }
    @RequestMapping("toAddClassificationPage")
    public String toAddClassificationPage(){
        return "classification/add_classification";
    }

    @RequestMapping("toUpdateClassificationPage")
    public String toUpdateClassificationPage(){
        return "classification/update_classification";
    }

    @RequestMapping("toDeleteClassificationPage")
    public String toDeleteClassificationPage(){
        return "classification/delete_classification";
    }

    @PostMapping("add")
    @ResponseBody
    public ResultInfo addClassification(Classification classification){
        classificationService.addClassification(classification);
        return success("添加成功");
    }

    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateClassification(ClassificationModel classificationModel){
        classificationService.updateClassification(classificationModel);
        return success("更新成功");
    }

    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteClassification(String classification){
        classificationService.deleteClassification(classification);
        return success("删除成功");
    }
}

