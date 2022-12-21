package com.bigdata6.spring_mybatis.controller;

import com.bigdata6.spring_mybatis.dto.PagingDto;
import com.bigdata6.spring_mybatis.dto.ReplyDto;
import com.bigdata6.spring_mybatis.dto.UserDto;
import com.bigdata6.spring_mybatis.service.ReplyService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @Data
    class AjaxSateHandler{
        private int state=0; //0:실패 1:성공  (statusCode : 400(badRequest),500(db 통신 오류), 405(Method 오류))
    }
    //pathVariable + (delete,patch,put,post,get)
    @DeleteMapping ("/delete.do")
    public @ResponseBody AjaxSateHandler delete(int replyNo,
                                                @SessionAttribute UserDto loginUser){
        AjaxSateHandler ajaxSateHandler=new AjaxSateHandler();
        int remove=replyService.removeOne(replyNo);
        ajaxSateHandler.setState(remove);
        return  ajaxSateHandler;
    }

    @PostMapping("/register.do")
    public @ResponseBody AjaxSateHandler register(ReplyDto reply,
                                                  @SessionAttribute UserDto loginUser){
        AjaxSateHandler ajaxSateHandler=new AjaxSateHandler();
        int register=0;
        register=replyService.registerOne(reply);
        ajaxSateHandler.setState(register);
        return ajaxSateHandler;
        //return "{\"state\":"+register+"}";
    }
}
