package com.bigdata6.spring_mybatis.mapper;

import com.bigdata6.spring_mybatis.dto.BoardPreferDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BoardPreferMapperTest {
    @Autowired
    private BoardPreferMapper boardPreferMapper;
    private static int insertBoardPreferNo;
    Logger log= LoggerFactory.getLogger(this.getClass());
    @Order(0)
    @Test
    void countByBoardNoAndLike() {
        int likesConut=boardPreferMapper.countByBoardNoAndPreferIsTrue(1);
        int badsConut=boardPreferMapper.countByBoardNoAndPreferIsFalse(1);
        log.info("1번글의 좋아요 수: "+likesConut);
        log.info("1번글의 싫어요 수: "+badsConut);
    }
    @Order(1)
    @Test
    void insert() {
        BoardPreferDto boardPrefer=new BoardPreferDto();
        boardPrefer.setPrefer(true);
        boardPrefer.setBoardNo(4);
        boardPrefer.setUserId("user1");
        boardPreferMapper.insert(boardPrefer);
        insertBoardPreferNo=boardPrefer.getBoardPreferNo();
        log.info("등록된 좋아요 번호:"+insertBoardPreferNo);
    }
    @Order(2)
    @Test
    void update() {
        BoardPreferDto boardPrefer=new BoardPreferDto();
        boardPrefer.setBoardPreferNo(insertBoardPreferNo);
        boardPrefer.setPrefer(false);
        boardPrefer.setBoardNo(4);
        boardPrefer.setUserId("user1");
        int update=boardPreferMapper.update(boardPrefer);
        log.info("싫어요로 수정:"+update);

    }

    @Order(3)
    @Test
    void findByBoardNoAndUserId() {
        log.info("user1이 4번글에 누른 버튼:"+boardPreferMapper.findByBoardNoAndUserId(4,"user1").toString());
    }
    @Order(4)
    @Test
    void delete() {

        log.info("user1이 싫어요 취소 (삭제):"+boardPreferMapper.delete(insertBoardPreferNo));
    }
}