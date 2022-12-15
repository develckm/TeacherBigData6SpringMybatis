package com.bigdata6.spring_mybatis.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
@Data
@ToString
public class UserDto {
    private String user_id;
    private String name;
    private String pw;
    private String phone;
    private String email;
    private Date birth;
    private Date signup;
}
