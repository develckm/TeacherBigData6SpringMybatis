package com.bigdata6.spring_mybatis.service;

import com.bigdata6.spring_mybatis.dto.BoardPreferDto;
import com.bigdata6.spring_mybatis.dto.BoardPreferViewDto;
import com.bigdata6.spring_mybatis.mapper.BoardMapper;
import com.bigdata6.spring_mybatis.mapper.BoardPreferMapper;
import com.bigdata6.spring_mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardPreferServiceImp implements BoardPreferService {
    BoardMapper boardMapper;
    BoardPreferMapper boardPreferMapper;
    UserMapper userMapper;

    public BoardPreferServiceImp(BoardMapper boardMapper, BoardPreferMapper boardPreferMapper, UserMapper userMapper) {
        this.boardMapper = boardMapper;
        this.boardPreferMapper = boardPreferMapper;
        this.userMapper = userMapper;
    }
    @Transactional
    @Override
    public BoardPreferViewDto detailBoardPreferView(int boardNo, String loginUserId) {
        BoardPreferViewDto detailBoardPreferView;
        detailBoardPreferView=boardMapper.countPreferById(boardNo);
        if (loginUserId!=null){
            BoardPreferDto loginUserPrefer=boardPreferMapper.findByBoardNoAndUserId(boardNo,loginUserId);
            detailBoardPreferView.setLoginUserPrefer(loginUserPrefer);
        }
        return detailBoardPreferView;
    }

    @Override
    public int register(BoardPreferDto boardPrefer) {
        return boardPreferMapper.insert(boardPrefer);
    }

    @Override
    public int modify(BoardPreferDto boardPrefer) {
        return boardPreferMapper.update(boardPrefer);
    }

    @Override
    public int remove(int boardPreferNo) {
        return boardPreferMapper.delete(boardPreferNo);
    }

    @Override
    public BoardPreferDto detail(int boardNo, String loginUserId) {
        BoardPreferDto detail=boardPreferMapper.findByBoardNoAndUserId(boardNo,loginUserId);
        return detail;
    }
}
