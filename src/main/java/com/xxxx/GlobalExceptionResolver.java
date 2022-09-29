package com.xxxx;

import com.alibaba.fastjson.JSON;
import com.xxxx.crm.base.ResultInfo;
import com.xxxx.crm.exceptions.NoLoginException;
import com.xxxx.crm.exceptions.ParamsException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/*全局一场统一处理
* 1.返回视图
* 2.返回json数据
*
* */
@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception ex) {

        /*
         * 非法请求拦截
         * 判断是否抛出未登录异常，应跳转到登录界面
         */
        if(ex instanceof NoLoginException){
            ModelAndView mv=new ModelAndView("redirect:/index");
            return mv;
        }


        /*
         * 默认返回视图
         */
        ModelAndView modelAndView=new ModelAndView("error");
        modelAndView.addObject("code",500);
        modelAndView.addObject("msg","系统异常，请重试...");

        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod= (HandlerMethod) handler;
            //获取方法上的Response注解
            ResponseBody responseBody=handlerMethod.getMethod().getDeclaredAnnotation(ResponseBody.class);
            //判断ResponseBody对象是否为空，若为空，则返回视图，若不为空，则返回json数据
            if(responseBody==null){

                if(ex instanceof ParamsException){
                    ParamsException p= (ParamsException) ex;
                    modelAndView.addObject("code",p.getCode());
                    modelAndView.addObject("msg",p.getMsg());
                }
                return modelAndView;
            }
            else{
                //设置默认一场处理
                ResultInfo resultInfo=new ResultInfo();
                resultInfo.setCode(500);
                resultInfo.setMsg("系统异常，请重试...");

                //判断一场是否是自定义异常
                if(ex instanceof ParamsException){
                    ParamsException p= (ParamsException) ex;
                    resultInfo.setCode(p.getCode());
                    resultInfo.setMsg(p.getMsg());
                }
                //设置响应类型以及编码格式
                httpServletResponse.setContentType("application/json;charset=UTF-8");
                PrintWriter out=null;
                try {
                    out=httpServletResponse.getWriter();
                    String json= JSON.toJSONString(resultInfo);
                    out.write(json);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if(out!=null){
                        out.close();
                    }
                }

                return null;
            }
        }

        return modelAndView;
    }
}
