package com.bigdata6.spring_mybatis.controller;

import com.bigdata6.spring_mybatis.dto.PagingDto;
import com.bigdata6.spring_mybatis.dto.UserDto;
import com.bigdata6.spring_mybatis.mapper.UserMapper;
import com.bigdata6.spring_mybatis.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private Logger log= LoggerFactory.getLogger(this.getClass().getSimpleName());
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("list.do")
    public String list(PagingDto paging,
                       Model model,
                       HttpServletRequest req){
        paging.setQueryString(req.getParameterMap());
        List<UserDto> userDtoList=userService.list(paging);
        log.info(paging.toString());
        model.addAttribute("userList",userDtoList);
        model.addAttribute("paging",paging);
        return "/user/list";
    }
    @GetMapping("/detail.do")
    public ModelAndView detail(
            @RequestParam(name = "user_id") String userId,
            ModelAndView model
        )  {
        UserDto user=userService.detail(userId);
        model.addObject("user",user);
        model.setViewName("/user/detail");
        return model;
    }
    @GetMapping("/modify.do")
    public String modify(
            @RequestParam(name = "user_id",required = false) String userId,
            Model model,
            @SessionAttribute UserDto loginUser
        ){
        if(userId!=null){
            UserDto user=userService.detail(userId);
            model.addAttribute("user",user);
            return "/user/modify";
        }else{
            return "redirect:/user/list.do";
        }
    }
    @PostMapping("/modify.do")
    public String modify(UserDto user,
                         @SessionAttribute UserDto loginUser){
        int modify=0;
        System.out.println(user);
        try {
            modify=userService.adminModify(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(modify>0){
            return "redirect:/user/detail.do?user_id="+user.getUser_id();
        }else{
            return "redirect:/user/modify.do?user_id="+user.getUser_id();
        }
    }
    @GetMapping("/delete.do")
    public String delete(@RequestParam(name = "user_id")String userId,
                         @SessionAttribute UserDto loginUser){
        int delete=0;
        try {
            delete=userService.remove(userId);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(delete>0){
            return "redirect:/user/list.do";
        }else {
            return "redirect:/user/modify.do?user_id="+userId;
        }
    }
    @GetMapping("/register.do")
    public void register(@SessionAttribute(name = "loginUser") UserDto loginUser) {

    }
    @PostMapping("/register.do")
    public String register(UserDto user,
                           @SessionAttribute UserDto loginUser){
        System.out.println(user);
        int register=0;
        try {
            register= userService.register(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(register>0){
            return "redirect:/user/detail.do?user_id="+user.getUser_id();
        }else{
            return "redirect:/user/register.do";
        }
    }
    @GetMapping("/login.do")
    public void login(HttpServletRequest req,
                      HttpSession session,
                      @SessionAttribute(required = false) String redirectUri){
        String referer=req.getHeader("referer");
        if (redirectUri==null&&!(referer.equals("http://localhost:8888/") || referer.equals("http://localhost:8888/user/login.do"))){
            session.setAttribute("redirectUri",referer);
        }

    }
    @PostMapping("/login.do")
    public String login(
            @RequestParam(name = "user_id") String userId,
            String pw,
            HttpSession session,
            @SessionAttribute(required = false) String redirectUri){
        UserDto user=userService.login(userId,pw);
        session.setAttribute("loginUser",user);
        if(user==null){
            session.setAttribute("msg","아이디나 비밀번호를 확인하세요!");
            return "redirect:/user/login.do";
        }else{
            if(redirectUri==null){
                return "redirect:/";
            }
            session.removeAttribute("redirectUri");
            return "redirect:"+redirectUri;
        }
    }
    @GetMapping("/logout.do")
    public String logout(HttpSession session){
        //session.invalidate();
        session.removeAttribute("loginUser");
        return "redirect:/";
    }
}
