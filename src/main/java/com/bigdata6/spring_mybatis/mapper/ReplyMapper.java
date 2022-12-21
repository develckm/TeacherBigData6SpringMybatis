package com.bigdata6.spring_mybatis.mapper;

import com.bigdata6.spring_mybatis.dto.PagingDto;
import com.bigdata6.spring_mybatis.dto.ReplyDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ReplyMapper {

    List<ReplyDto> findAll();
    List<ReplyDto> findByBoardNo(int boardNo); //board 의 collection fetch 조인 하기 위해 작성
    List<ReplyDto> findByBoardNoPaging(int boardNo, PagingDto paging);
    int countByBoardNo(int boardNo, PagingDto paging);

    List<ReplyDto> findByUserIdPaging(String userId, PagingDto paging); //유저 상세에서 작성한 댓글 리스트
    int countByUserId(String userId, PagingDto paging);

    ReplyDto findById( int replyNo );
    int deleteById(int replyNo);
    int updateById(ReplyDto dto);
    int insert(ReplyDto dto);

    List<ReplyDto> findByFkReplyNo(int replyNo);
}
