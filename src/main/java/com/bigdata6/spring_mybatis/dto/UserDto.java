package com.bigdata6.spring_mybatis.dto;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Data
@ToString
public class UserDto {
    private String user_id;
    private String name;
    private String pw;
    private String phone;
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date signup;
}
