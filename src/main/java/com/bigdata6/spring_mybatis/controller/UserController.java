package com.bigdata6.spring_mybatis.controller;

import com.bigdata6.spring_mybatis.dto.PagingDto;
import com.bigdata6.spring_mybatis.dto.UserDto;
import com.bigdata6.spring_mybatis.mapper.UserMapper;
import com.bigdata6.spring_mybatis.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        System.out.println(paging);
        List<UserDto> userDtoList=userService.list(paging);
        model.addAttribute("userList",userDtoList);
        model.addAttribute("paging",paging);

        return "/user/list";
    }
}
