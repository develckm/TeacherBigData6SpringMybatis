package com.bigdata6.spring_mybatis.mapper;

import com.bigdata6.spring_mybatis.dto.BoardDto;
import com.bigdata6.spring_mybatis.dto.BoardPreferViewDto;
import com.bigdata6.spring_mybatis.dto.PagingDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface BoardMapper extends CRUD<BoardDto,Integer> {
    List<BoardDto> findAll();

    List<BoardDto> findPaging(PagingDto paging);

    int count(PagingDto paging);

    BoardDto findById(Integer id);

    int deleteById(Integer id);

    int updateById(BoardDto dto);
    int updateViews(int boardNo);

    int insert(BoardDto dto);
    BoardPreferViewDto countPreferById(int boardNo);

}
