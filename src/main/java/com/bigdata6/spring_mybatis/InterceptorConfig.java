package com.bigdata6.spring_mybatis;

import com.bigdata6.spring_mybatis.interceptor.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    //@Autowired
    LoginCheckInterceptor loginCheckInterceptor;
    public InterceptorConfig(LoginCheckInterceptor loginCheckInterceptor) {
        this.loginCheckInterceptor = loginCheckInterceptor;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckInterceptor)
                .addPathPatterns("/user/*.do")
                .excludePathPatterns("/user/login.do")
                .excludePathPatterns("/user/signup.do")
                .addPathPatterns("/board/*.do")
                .addPathPatterns("/board/*/*.do")
                .excludePathPatterns("/board/list.do")
                .excludePathPatterns("/board/*/detail.do");
    }
}
