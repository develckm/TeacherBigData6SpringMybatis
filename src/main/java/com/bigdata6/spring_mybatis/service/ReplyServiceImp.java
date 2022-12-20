package com.bigdata6.spring_mybatis.service;

import com.bigdata6.spring_mybatis.dto.PagingDto;
import com.bigdata6.spring_mybatis.dto.ReplyDto;
import com.bigdata6.spring_mybatis.mapper.ReplyMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyServiceImp implements ReplyService{
    private ReplyMapper replyMapper;
    public ReplyServiceImp(ReplyMapper replyMapper) {
        this.replyMapper = replyMapper;
    }

    @Override
    public List<ReplyDto> boardDetailList(int boardNo, PagingDto paging) {
        int totalRows=replyMapper.countByBoardNo(boardNo,paging);
        paging.setTotalRows(totalRows);
        return replyMapper.findByBoardNoPaging(boardNo,paging);
    }

    @Override
    public List<ReplyDto> userDetailList(String userId, PagingDto paging) {
        return null;
    }

    @Override
    public int removeOne(int replyNo) {
        return 0;
    }

    @Override
    public int modifyOne(ReplyDto reply) {
        return 0;
    }

    @Override
    public int registerOne(ReplyDto reply) {
        return 0;
    }
}
