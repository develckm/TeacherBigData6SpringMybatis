package com.bigdata6.spring_mybatis.controller;

import com.bigdata6.spring_mybatis.dto.PagingDto;
import com.bigdata6.spring_mybatis.dto.UserDto;
import com.bigdata6.spring_mybatis.mapper.UserMapper;
import com.bigdata6.spring_mybatis.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("list.do")
    public String list(PagingDto paging,
                       Model model,
                       HttpServletRequest req){
        paging.setQueryString(req.getParameterMap());
        List<UserDto> userDtoList=userService.list(paging);
        System.out.println(paging);
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
    public void modify(
            @RequestParam(name = "user_id") String userId,
            Model model
        ){
        UserDto user=userService.detail(userId);
        model.addAttribute("user",user);
    }
    @PostMapping("/modify.do")
    public String modify(UserDto user){
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
    public String delete(@RequestParam(name = "user_id")String userId){
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
    public void register(){}
    @PostMapping("/register.do")
    public String register(UserDto user){
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
}
