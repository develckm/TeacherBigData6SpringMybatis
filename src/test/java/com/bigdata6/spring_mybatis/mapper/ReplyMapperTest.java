package com.bigdata6.spring_mybatis.mapper;

import com.bigdata6.spring_mybatis.dto.PagingDto;
import com.bigdata6.spring_mybatis.dto.ReplyDto;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        replyMapper.findById(1);
    }


    @Test
    void insert() {
        ReplyDto reply=new ReplyDto();
        reply.setTitle("댓글");
        reply.setUserId("user1");
        reply.setContents("안녕하세요!");
        reply.setImgPath("origin.jpeg");
        reply.setBoardNo(2);
        log.info(replyMapper.insert(reply)+"");
        log.info(replyMapper.findById(reply.getReplyNo()).toString());
    }
    @Test
    void updateById() {
        ReplyDto reply=new ReplyDto();
        reply.setReplyNo(14);
        reply.setTitle("수정 수정");
        reply.setContents("수정 안녕하세요!!!!");
        reply.setImgPath("a.jpeg");
        log.info(replyMapper.updateById(reply)+"");
        log.info(replyMapper.findById(14).toString());
    }
    @Test
    void findByBoardNo(){
        replyMapper.findByBoardNo(1);
    }
    @Test
    void deleteById() {
        log.info(replyMapper.deleteById(14)+"");
    }
}