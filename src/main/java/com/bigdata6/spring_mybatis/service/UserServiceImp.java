package com.bigdata6.spring_mybatis.service;

import com.bigdata6.spring_mybatis.dto.PagingDto;
import com.bigdata6.spring_mybatis.dto.UserDto;
import com.bigdata6.spring_mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//SPRING BEAN 컨테이너(팩터리) 에서 관리 받는다. @Component > @Mapper,@Service
@Service
public class UserServiceImp  implements  UserService{
    private UserMapper userMapper;
    public UserServiceImp(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDto> listAllTest() {
        return userMapper.findAll();
    }

    @Override
    public int signup(UserDto user) {
        return 0;
    }

    @Override
    public int register(UserDto user) {
        return userMapper.insert(user);
    }

    @Override
    public int userModify(UserDto user) {
        return 0;
    }

    @Override
    public int adminModify(UserDto user) {
        int modify=0;
        modify=userMapper.updateById(user);
        return modify;
    }

    @Override
    public UserDto login(String id, String pw) {
        return userMapper.findByUserIdAndPw(id,pw);
    }

    @Override
    public List<UserDto> list(PagingDto paging) {
        int totalRows=userMapper.count(paging);
        paging.setTotalRows(totalRows); //startRow 를 정의
        List<UserDto> list=userMapper.findPaging(paging);
        return list;
    }

    @Override
    public UserDto detail(String id) {
        UserDto detail=userMapper.findById(id);
        return detail;
    }

    @Override
    public int idCheck(String id) {
        return 0;
    }

    @Override
    public int remove(String id) {
        return userMapper.deleteById(id);
    }
}
