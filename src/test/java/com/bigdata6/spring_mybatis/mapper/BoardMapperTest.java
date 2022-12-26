package com.bigdata6.spring_mybatis.mapper;

import com.bigdata6.spring_mybatis.dto.BoardDto;
import com.bigdata6.spring_mybatis.dto.PagingDto;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BoardMapperTest {
    @Autowired
    private  BoardMapper boardMapper;
    private Logger log= LoggerFactory.getLogger(this.getClass().getSimpleName());
    @Test
    void countPreferById(){
        log.info(boardMapper.countPreferById(1).toString());
    }
    @Test
    void findAll() {
        //log.info(boardMapper.findAll().toString());
        //fetch.lazy의 트리거가 되기때문에 board 출력시 무조건 user를 호출한다.
        boardMapper.findAll();
    }

    @Test
    void findPaging() {
        PagingDto paging=new PagingDto(1,5,"board_no","DESC");
        boardMapper.findPaging(paging);
    }

    @Test
    void count() {
        PagingDto paging=new PagingDto(1,5,"board_no","DESC");
        boardMapper.count(paging);
    }

    @Test
    void findById() {
       log.info(boardMapper.findById(1).toString());
    }

    @Test
    void deleteById() {
    }

    @Test
    void updateById() {
    }

    @Test
    void updateViews() {
    }

    @Test
    void insert() {
        BoardDto board=new BoardDto();
        board.setTitle("테스트");
        board.setUserId("user1");
        board.setContents("내용입니다.");
        boardMapper.insert(board);
        log.info(board.toString());
    }
}