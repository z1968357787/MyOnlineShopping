package com.xxxx.crm.interceptor;

import com.xxxx.crm.exceptions.NoLoginException;
import com.xxxx.crm.service.UserService;
import com.xxxx.crm.utils.LoginUserUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 *非法访问拦截
 * 判断用户是否属于登录状态
 * 在目标资源执行前所执行的方法
 * 如果返回true，表示目标方法可以被执行，否则，目标方法无法执行
 * 如果处于登录状态，则可以执行目标方法，否则，会跳转到登录页面
 */
public class NoLoginInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Integer userId= LoginUserUtil.releaseUserIdFromCookie(request);
        if(userId==null||userService.selectByPrimaryKey(userId)==null){
            //抛出未登录异常
            throw new NoLoginException();
        }
        return true;
    }
}
