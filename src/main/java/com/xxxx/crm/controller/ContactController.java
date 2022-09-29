package com.xxxx.crm.controller;

import com.xxxx.crm.base.BaseController;
import com.xxxx.crm.base.ResultInfo;
import com.xxxx.crm.query.ContactQuery;
import com.xxxx.crm.service.ContactService;
import com.xxxx.crm.utils.LoginUserUtil;
import com.xxxx.crm.vo.Contact;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("contact")
public class ContactController extends BaseController {
    @Resource
    private ContactService contactService;

    @RequestMapping("index")
    public String index(){
        return "contact/contact_management";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryProductByParams(HttpServletRequest request, ContactQuery contactQuery){
        Integer userId= LoginUserUtil.releaseUserIdFromCookie(request);
        contactQuery.setUserId(userId);
        return contactService.queryContactByParams(contactQuery);
    }

    @RequestMapping("addOrUpdateContactPage")
    public String addOrUpdateContactPage(HttpServletRequest request,Integer contactId){
        if(contactId!=null){
            Contact contact=contactService.selectByPrimaryKey(contactId);
            request.setAttribute("contact",contact);
        }
        return "contact/add_update";
    }

    @PostMapping("save")
    @ResponseBody
    public ResultInfo insertContact(HttpServletRequest request,Contact contact) throws IOException {
        Integer userId=LoginUserUtil.releaseUserIdFromCookie(request);
        contact.setUserId(userId);
        contactService.insertContact(contact);
        return success("添加成功");
    }

    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateContact(Contact contact) throws IOException {
        contactService.updateContact(contact);
        return success("更新成功");
    }

    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteContact(Integer ids[]) throws IOException {
        contactService.deleteContact(ids);
        return success("删除成功");
    }
}
