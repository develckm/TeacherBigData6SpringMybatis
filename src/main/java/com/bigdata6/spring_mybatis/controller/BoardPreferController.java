package com.bigdata6.spring_mybatis.controller;

import com.bigdata6.spring_mybatis.dto.AjaxStateHandler;
import com.bigdata6.spring_mybatis.dto.BoardPreferDto;
import com.bigdata6.spring_mybatis.dto.BoardPreferViewDto;
import com.bigdata6.spring_mybatis.dto.UserDto;
import com.bigdata6.spring_mybatis.service.BoardPreferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping("/board/prefer/")
public class BoardPreferController {
    private BoardPreferService boardPreferService;

    public BoardPreferController(BoardPreferService boardPreferService) {
        this.boardPreferService = boardPreferService;
    }

    @GetMapping("/view.do")
    public String view (
            int boardNo,
            @SessionAttribute(required = false)UserDto loginUser,
            Model model){
        String loginUserId=(loginUser!=null)?loginUser.getUser_id():null;
        BoardPreferViewDto boardPreferView=boardPreferService.detailBoardPreferView(boardNo,loginUserId);
        model.addAttribute("prefer",boardPreferView);
        return "/board/boardPrefer";
    }
    @RequestMapping(method = {GET,PUT},path = "/handler.do")
    public @ResponseBody AjaxStateHandler handler(
            int boardNo,
            boolean preferBtn,
            @SessionAttribute UserDto loginUser){
        AjaxStateHandler ajaxStateHandler=new AjaxStateHandler();
        BoardPreferDto userLoginPrefer=boardPreferService.detail(boardNo,loginUser.getUser_id());
        int handler=0;
        if(userLoginPrefer==null){ //한번도 좋아요 싫어요를 한적 업는 경우 => 등록
            userLoginPrefer=new BoardPreferDto();
            userLoginPrefer.setBoardNo(boardNo);
            userLoginPrefer.setUserId(loginUser.getUser_id());
            userLoginPrefer.setPrefer(preferBtn);
            handler=boardPreferService.register(userLoginPrefer);
        }else {
            if (userLoginPrefer.isPrefer()==preferBtn){//좋아요(싫어요)를 했는데 다시 좋아요(싫어요)를 하는 경우 =>삭제
                handler=boardPreferService.remove(userLoginPrefer.getBoardPreferNo());
            }else {
                userLoginPrefer.setPrefer(preferBtn);
                handler=boardPreferService.modify(userLoginPrefer);
            }
        }
        ajaxStateHandler.setState(handler);
        return ajaxStateHandler;
    }
}
