package com.bigdata6.spring_mybatis.mapper;

import com.bigdata6.spring_mybatis.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends CRUD<UserDto,String>{
    List<UserDto> findAll();
    UserDto findByUserIdAndPw(String userId,String pw);
}
