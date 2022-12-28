package com.bigdata6.spring_mybatis.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
public class MsgRemoveInterceptor implements HandlerInterceptor{
    Logger log= LoggerFactory.getLogger(this.getClass());
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Object msg_obj=request.getSession().getAttribute("msg");
        log.info(msg_obj+"");
        if(request.getMethod().equals("GET")&&msg_obj!=null)request.getSession().removeAttribute("msg");
    }
}
