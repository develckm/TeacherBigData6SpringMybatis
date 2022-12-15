package com.bigdata6.spring_mybatis.controller;

import com.bigdata6.spring_mybatis.dto.PagingDto;
import com.bigdata6.spring_mybatis.dto.UserDto;
import com.bigdata6.spring_mybatis.mapper.UserMapper;
import com.bigdata6.spring_mybatis.service.UserService;
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
    public String list(
                       //@RequestParam(defaultValue = "1") int page,
                       //@RequestParam(defaultValue = "10")int rows,
                       //@RequestParam(defaultValue = "user_id")String orderField,
                       //@RequestParam(defaultValue = "DESC")String direct,
                       PagingDto paging,
                       Model model){//void,model.addAttribute Type return "/user/list.html";
        //System.out.println(page+","+rows);
        //PagingDto pagingDto=new PagingDto(page,rows,orderField,direct);
        //if(paging.getOrderField()==null)paging.setOrderField("user_id"); //다이나믹 쿼리로 처리
        System.out.println(paging);
        List<UserDto> userDtoList=userService.list(paging);
        model.addAttribute("userList",userDtoList);
        model.addAttribute("paging",paging);

        return "/user/list";
    }
}
