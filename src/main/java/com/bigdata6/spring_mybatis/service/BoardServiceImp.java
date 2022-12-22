package com.bigdata6.spring_mybatis.service;

import com.bigdata6.spring_mybatis.dto.BoardDto;
import com.bigdata6.spring_mybatis.dto.BoardImgDto;
import com.bigdata6.spring_mybatis.dto.PagingDto;
import com.bigdata6.spring_mybatis.mapper.BoardImgMapper;
import com.bigdata6.spring_mybatis.mapper.BoardMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardServiceImp implements  BoardService{
    private BoardMapper boardMapper;
    private BoardImgMapper boardImgMapper;

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

    @Override
    public int modify(BoardDto board, int[] delImgNos) {
        return 0;
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
