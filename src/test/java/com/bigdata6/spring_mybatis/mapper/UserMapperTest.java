package com.bigdata6.spring_mybatis.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest //단위 테스트에 객체 의존성 주입
class UserMapperTest {
    @Autowired //단위 테스트는 생성자로 주입받을 수 없다.
    private UserMapper userMapper;
    @Test
    void findAll() {
        System.out.println(userMapper.findAll());
    }
    @Test
    void findByUserIdAndPw() {
        System.out.println(userMapper.findByUserIdAndPw("develckm","1234"));
    }

    @Test
    void findPaging() {
    }

    @Test
    void count() {
    }

    @Test
    void findById() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void updateById() {
    }

    @Test
    void insert() {
    }
}