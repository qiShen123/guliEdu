package com.itguigu.eduService.config;

import com.itguigu.eduService.controller.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class loginConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/eduService/**").excludePathPatterns("/eduService/admin/login")
        .excludePathPatterns("/eduService/admin/info").excludePathPatterns("/eduService/front/**");
    }
}
