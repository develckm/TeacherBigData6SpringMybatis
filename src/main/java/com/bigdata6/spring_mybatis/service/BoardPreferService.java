package com.bigdata6.spring_mybatis.service;

import com.bigdata6.spring_mybatis.dto.BoardPreferDto;
import com.bigdata6.spring_mybatis.dto.BoardPreferViewDto;

public interface BoardPreferService {
    BoardPreferViewDto detailBoardPreferView(int boardNo, String loginUserId);
    int register(BoardPreferDto boardPrefer);
    int modify(BoardPreferDto boardPrefer);
    int remove(int boardPreferNo);
    BoardPreferDto detail(int boardNo,String loginUserId);
}
