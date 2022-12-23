package com.bigdata6.spring_mybatis.service;

import com.bigdata6.spring_mybatis.dto.BoardDto;
import com.bigdata6.spring_mybatis.dto.BoardImgDto;
import com.bigdata6.spring_mybatis.dto.PagingDto;
import com.bigdata6.spring_mybatis.mapper.BoardImgMapper;
import com.bigdata6.spring_mybatis.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardServiceImp implements  BoardService{
    private BoardMapper boardMapper;
    private BoardImgMapper boardImgMapper;
    @Value("${img.upload.path}")
    private String imgPath;

    public BoardServiceImp(BoardMapper boardMapper, BoardImgMapper boardImgMapper) {
        this.boardMapper = boardMapper;
        this.boardImgMapper = boardImgMapper;
    }

    @Override
    public List<BoardDto> list(PagingDto paging) {
        int totalRows=boardMapper.count(paging);
        paging.setTotalRows(totalRows);
        return boardMapper.findPaging(paging);
    }

    @Override
    public BoardDto detail(int boardNo) {
        boardMapper.updateViews(boardNo);
        return boardMapper.findById(boardNo);
    }
    @Transactional
    @Override
    public List<BoardImgDto> modify(BoardDto board, int[] delImgNos) {
        int modify=0;
        List<BoardImgDto> delBoardImgDtoList=new ArrayList<BoardImgDto>();
        if(delImgNos!=null){ //이미지 삭제미 보드 이미지 레코드
            for(int delImgNo:delImgNos){
                BoardImgDto boardImgDto=boardImgMapper.findOne(delImgNo);
                delBoardImgDtoList.add(boardImgDto);
                boardImgMapper.deleteOne(delImgNo);
            }
        }
        if(board.getBoardImgList()!=null){
            for(BoardImgDto boardImg:board.getBoardImgList()){
                boardImg.setBoardNo(board.getBoardNo());
                boardImgMapper.insertOne(boardImg);
            }
        }
        boardMapper.updateById(board);
        return delBoardImgDtoList;
    }

    @Override
    public int remove(int boardNo) {
        return 0;
    }

    @Transactional //해당함수의 커밋과 롤백을 자동으로 하도록 정의해줌
    @Override
    public int register(BoardDto board) {
        //savepoint or conn.commit();
        int register=0;
        register+=boardMapper.insert(board);
        if (board.getBoardImgList()!=null){
            for(BoardImgDto boardImg : board.getBoardImgList()){
                boardImg.setBoardNo(board.getBoardNo());
                register+=boardImgMapper.insertOne(boardImg);
            }
        }
        //try{}catch(){  conn.rollBack();  } //오류가 발생하면 commit 으로 실행을 모두 취소
        return register;
    }
}
