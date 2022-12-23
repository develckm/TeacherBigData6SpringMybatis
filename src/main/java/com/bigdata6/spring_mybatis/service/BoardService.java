package com.bigdata6.spring_mybatis.service;

import com.bigdata6.spring_mybatis.dto.BoardDto;
import com.bigdata6.spring_mybatis.dto.BoardImgDto;
import com.bigdata6.spring_mybatis.dto.PagingDto;

import java.util.List;

public interface BoardService {
    List<BoardDto> list(PagingDto paging);
    BoardDto detail(int boardNo);
    List<BoardImgDto> modify(BoardDto board, int[]delImgNos);
    int remove(int boardNo);
    int register(BoardDto board);
}
