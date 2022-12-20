package com.bigdata6.spring_mybatis.controller;

import com.bigdata6.spring_mybatis.dto.PagingDto;
import com.bigdata6.spring_mybatis.dto.ReplyDto;
import com.bigdata6.spring_mybatis.service.ReplyService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/reply")
public class ReplyController {
    private ReplyService replyService;
    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @GetMapping("/{boardNo}/list.do")
    public String list(@PathVariable int boardNo,
                       PagingDto paging,
                       HttpServletRequest req,
                       Model model){
        paging.setQueryString(req.getParameterMap());
        List<ReplyDto> replyList=replyService.boardDetailList(boardNo,paging);
        model.addAttribute("replyList",replyList);
        model.addAttribute("paging",paging);
        return "/reply/list";
    }
}
