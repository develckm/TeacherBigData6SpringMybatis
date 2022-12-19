package com.bigdata6.spring_mybatis.mapper;

import com.bigdata6.spring_mybatis.dto.PagingDto;
import com.bigdata6.spring_mybatis.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends CRUD<UserDto,String>{
    List<UserDto> findAll();
    UserDto findByUserIdAndPw(String userId,String pw);

    List<UserDto> findPaging(PagingDto paging);

    int count(PagingDto paging);

    UserDto findById(String id);

    int deleteById(String id);

    int updateById(UserDto dto);

    int insert(UserDto dto);
}
