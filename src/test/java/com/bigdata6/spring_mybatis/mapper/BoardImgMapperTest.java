package com.bigdata6.spring_mybatis.mapper;

import com.bigdata6.spring_mybatis.dto.BoardImgDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BoardImgMapperTest {
    @Autowired
    private BoardImgMapper boardImgMapper;

    @Test
    void findByBoardNo() {
        boardImgMapper.findByBoardNo(1);
    }

    @Test
    void findOne() {
        boardImgMapper.findOne(33);
    }

    @Test
    void deleteOne() {
        boardImgMapper.deleteOne(33);
    }

    @Test
    void insertOne() {
        BoardImgDto boardImg=new BoardImgDto();
        boardImg.setBoardNo(1);
        boardImg.setImgPath("b.png");
        boardImgMapper.insertOne(boardImg);
    }
}