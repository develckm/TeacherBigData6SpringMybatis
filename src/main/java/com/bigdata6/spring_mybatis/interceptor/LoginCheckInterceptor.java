package com.bigdata6.spring_mybatis.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    Logger log= LoggerFactory.getLogger(this.getClass());
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session=request.getSession();
        Object loginUser_obj=session.getAttribute("loginUser");
        String url=request.getRequestURI();
        String querString=request.getQueryString();
        url+=(querString!=null)?"?"+querString:"";
        log.info("preHandle(url):"+url);
        if(loginUser_obj==null){
            session.setAttribute("msg","로그인 후 이용가능 한 서비스 입니다.");
            session.setAttribute("redirectUri",url);
            response.sendRedirect("/user/login.do");
            return false;
        }
        return true;
    }
    //동적 페이지 에서 view 를 렌더링 하기 전
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle :"+modelAndView.getViewName());
    }
    //동적 페이지에서 view 를 렌더링한 후 , 요청 처리가 다끝나서 응답하기 직전
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //response.getWriter().append("<h1>ㅎㅎㅎㅎㅎㅎㅎㅎ</h1>");
        log.info("afterCompletion");
    }
}
