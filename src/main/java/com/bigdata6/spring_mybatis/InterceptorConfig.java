package com.bigdata6.spring_mybatis;

import com.bigdata6.spring_mybatis.interceptor.LoginCheckInterceptor;
import com.bigdata6.spring_mybatis.interceptor.MsgRemoveInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    //@Autowired
    LoginCheckInterceptor loginCheckInterceptor;
    MsgRemoveInterceptor msgRemoveInterceptor;

    public InterceptorConfig(LoginCheckInterceptor loginCheckInterceptor, MsgRemoveInterceptor msgRemoveInterceptor) {
        this.loginCheckInterceptor = loginCheckInterceptor;
        this.msgRemoveInterceptor = msgRemoveInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(loginCheckInterceptor).order(1)
                .addPathPatterns("/user/*.do")
                .excludePathPatterns("/user/login.do")
                .excludePathPatterns("/user/signup.do")
                .addPathPatterns("/board/*.do")
                .addPathPatterns("/board/*/*.do")
                .excludePathPatterns("/board/list.do")
                .excludePathPatterns("/board/*/detail.do")
                .excludePathPatterns("/board/prefer/*.do");
        registry.addInterceptor(msgRemoveInterceptor).order(2)
                .addPathPatterns("/")
                .addPathPatterns("/**/*.do");


    }

}
