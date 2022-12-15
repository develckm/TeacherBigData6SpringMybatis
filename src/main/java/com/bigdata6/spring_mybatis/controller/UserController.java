package com.bigdata6.spring_mybatis.controller;

import com.bigdata6.spring_mybatis.dto.UserDto;
import com.bigdata6.spring_mybatis.mapper.UserMapper;
import com.bigdata6.spring_mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("list.do")
    public String list(Model model){//void,model.addAttribute Type return "/user/list.html";
        List<UserDto> userDtoList=userService.listAllTest();
        model.addAttribute("userList",userDtoList);
        return "/user/list";
    }
}
