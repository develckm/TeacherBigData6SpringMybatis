package com.bigdata6.spring_mybatis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Controller
@RequestMapping("/user")
public class UserController {
    //@Autowired  //권장하지 않고 생성자를 정의해한다.
    private DataSource dataSource;

    public UserController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping("list.do")
    public void list(){//return "/user/list.html";
        try {
            Connection conn =dataSource.getConnection();
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM  USER");
            while (rs.next()){
                System.out.println(rs.getString("user_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
