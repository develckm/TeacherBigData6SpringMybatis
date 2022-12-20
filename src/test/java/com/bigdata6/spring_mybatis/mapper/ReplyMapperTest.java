package com.bigdata6.spring_mybatis.mapper;

import com.bigdata6.spring_mybatis.dto.PagingDto;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ReplyMapperTest {
    @Autowired
    private ReplyMapper replyMapper;
    private Logger log= LoggerFactory.getLogger(this.getClass());
    @Test
    void findAll() {
        replyMapper.findAll();
    }

    @Test
    void findByBoardNoPaging() {
        PagingDto pagingDto=new PagingDto(1,5,"title","DESC");
        replyMapper.findByBoardNoPaging(1,pagingDto);
    }

    @Test
    void countByBoardNo() {
        PagingDto pagingDto=new PagingDto(3,2,"title","DESC");
        int totalRows=replyMapper.countByBoardNo(1,pagingDto);
        pagingDto.setTotalRows(totalRows);
        log.info(pagingDto.toString());
    }

    @Test
    void findByUserIdPaging() {
    }

    @Test
    void countByUserId() {
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