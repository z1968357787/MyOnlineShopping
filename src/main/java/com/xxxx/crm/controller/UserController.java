package com.xxxx.crm.controller;

import com.xxxx.crm.base.BaseController;
import com.xxxx.crm.base.ResultInfo;
import com.xxxx.crm.model.RegisterModel;
import com.xxxx.crm.model.UserModel;
import com.xxxx.crm.query.UserQuery;
import com.xxxx.crm.service.UserService;
import com.xxxx.crm.utils.LoginUserUtil;
import com.xxxx.crm.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {
    @Resource
    private UserService userService;

    @PostMapping("login")
    @ResponseBody
    public ResultInfo userLogin(String userName,String userPwd){
        ResultInfo resultInfo=new ResultInfo();
        UserModel userModel=userService.userLogin(userName,userPwd);
        resultInfo.setResult(userModel);
        return resultInfo;
    }

    @PostMapping("update")
    @ResponseBody
    public ResultInfo userUpdate(HttpServletRequest request,User user){
        ResultInfo resultInfo=new ResultInfo();
        User newUser=userService.userUpdate(user);
        request.getSession().setAttribute("user",newUser);
        return resultInfo;
    }

    @PostMapping("register")
    @ResponseBody
    public ResultInfo userRegister(RegisterModel registerModel){
        ResultInfo resultInfo=new ResultInfo();
        userService.userRegister(registerModel);
        return resultInfo;
    }

    @PostMapping("updatePwd")
    @ResponseBody
    public ResultInfo updateUserPassword(HttpServletRequest request,String oldPassword,String newPassword,String repeatPassword){
        ResultInfo resultInfo=new ResultInfo();
        Integer userId= LoginUserUtil.releaseUserIdFromCookie(request);
        userService.updatePassword(userId,oldPassword,newPassword,repeatPassword);
        return resultInfo;
    }

    @RequestMapping("toPasswordPage")
    public String toPasswordPage(){
        return "user/password";
    }

    @RequestMapping("toSettingPage")
    public String toSettingPage(){
        return "user/setting";
    }

    @RequestMapping("toManagementPage")
    public String toManagementPage(){
        return "user/management";
    }

    @RequestMapping("addOrUpdateUserPage")
    public String addOrUpdateUserPage(HttpServletRequest request,Integer id){
        if(id !=null){
            User user=userService.selectByPrimaryKey(id);
            request.setAttribute("userManage",user);
            return "user/update_user";
        }
        return "user/add_user";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryUserByParams(UserQuery userQuery){
        return userService.queryUserByParams(userQuery);
    }

    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteUser(Integer ids[]) throws IOException {
        userService.deleteUser(ids);
        return success("删除成功");
    }

}
