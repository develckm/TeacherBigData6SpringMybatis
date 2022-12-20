package com.bigdata6.spring_mybatis.service;

import com.bigdata6.spring_mybatis.dto.PagingDto;
import com.bigdata6.spring_mybatis.dto.ReplyDto;
import com.bigdata6.spring_mybatis.mapper.ReplyMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReplyService {
    List<ReplyDto> boardDetailList(int boardNo, PagingDto paging);
    List<ReplyDto> userDetailList(String userId, PagingDto paging);
    int removeOne(int replyNo);
    int modifyOne(ReplyDto reply);
    int registerOne(ReplyDto reply);
}
