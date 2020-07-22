package com.itguigu.eduService.controller.interceptor;

import com.itguigu.commonutils.JwtUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
// TODO 支持跨域访问

        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }

        String memberIdByJwtToken = JwtUtils.getMemberIdByJwtToken(request);
        if (""==memberIdByJwtToken){
            request.getRequestDispatcher("/unLogin").forward(request,response);
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
