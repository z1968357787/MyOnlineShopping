package com.xxxx.crm.controller;

import com.xxxx.crm.base.BaseController;
import com.xxxx.crm.service.UserService;
import com.xxxx.crm.utils.LoginUserUtil;
import com.xxxx.crm.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController extends BaseController {
    /**
     * 系统登录⻚
     * @return
     */
    @Resource
    private UserService userService;

    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @RequestMapping("register")
    public String register(){
        return "register";
    }

    // 系统界⾯欢迎⻚
    @RequestMapping("welcome")
    public String welcome(){
        return "welcome";
    }
    /**
     * 后端管理主页面⻚⾯
     * @return
     */
    @RequestMapping("main")
    public String main(HttpServletRequest request){
        Integer userId= LoginUserUtil.releaseUserIdFromCookie(request);
        User user=userService.selectByPrimaryKey(userId);
        request.getSession().setAttribute("user",user);
        if(user.getRole().equals("普通用户")){
            return "main1";
        }else if(user.getRole().equals("管理员")){
            return "main2";
        }else {
            return "main3";
        }
    }

}
