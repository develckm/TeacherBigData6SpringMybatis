package com.bigdata6.spring_mybatis.mapper;

import com.bigdata6.spring_mybatis.dto.BoardPreferDto;
import com.bigdata6.spring_mybatis.dto.BoardPreferViewDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardPreferMapper {
    int countByBoardNoAndPreferIsTrue(int boardNo);
    int countByBoardNoAndPreferIsFalse(int boardNo);


    int insert(BoardPreferDto boardPrefer);

    int update(BoardPreferDto boardPrefer);

    int delete(int boardPreferNo);

    BoardPreferDto findByBoardNoAndUserId(int boardNo, String userId);

}
