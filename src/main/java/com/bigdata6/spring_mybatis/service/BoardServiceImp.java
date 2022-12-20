package com.bigdata6.spring_mybatis.service;

import com.bigdata6.spring_mybatis.dto.BoardDto;
import com.bigdata6.spring_mybatis.dto.PagingDto;
import com.bigdata6.spring_mybatis.mapper.BoardMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardServiceImp implements  BoardService{
    private BoardMapper boardMapper;

    public BoardServiceImp(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }
    @Override
    public List<BoardDto> list(PagingDto paging) {
        int totalRows=boardMapper.count(paging);
        paging.setTotalRows(totalRows);
        return boardMapper.findPaging(paging);
    }

    @Override
    public BoardDto detail(int boardNo) {
        return boardMapper.findById(boardNo);
    }

    @Override
    public int modify(BoardDto board, int[] delImgNos) {
        return 0;
    }

    @Override
    public int remove(int boardNo) {
        return 0;
    }

    @Override
    public int register(BoardDto board) {
        return 0;
    }
}
