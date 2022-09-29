package com.xxxx.crm.controller;

import com.xxxx.crm.base.BaseController;
import com.xxxx.crm.base.ResultInfo;
import com.xxxx.crm.model.UserModel;
import com.xxxx.crm.service.UserService;
import com.xxxx.crm.utils.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {
    @Resource
    private UserService userService;

    @PostMapping("login")
    @ResponseBody
    public ResultInfo userLogin(String userName,String userPwd){
        ResultInfo resultInfo=new ResultInfo();
        UserModel userModel=userService.UserLogin(userName,userPwd);
        resultInfo.setResult(userModel);
        /*try{

        }catch (ParamsException p){
            resultInfo.setCode(p.getCode());
            resultInfo.setMsg(p.getMsg());
            p.printStackTrace();
        }catch (Exception e){
            resultInfo.setCode(500);
            resultInfo.setMsg("登录失败");
            e.printStackTrace();
        }*/
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
}
