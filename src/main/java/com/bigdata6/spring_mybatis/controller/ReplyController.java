package com.bigdata6.spring_mybatis.controller;

import com.bigdata6.spring_mybatis.dto.PagingDto;
import com.bigdata6.spring_mybatis.dto.ReplyDto;
import com.bigdata6.spring_mybatis.dto.UserDto;
import com.bigdata6.spring_mybatis.service.ReplyService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/reply")
public class ReplyController {
    private ReplyService replyService;
    @Value("${spring.servlet.multipart.location}")
    private String imgPath;

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
                                                  @SessionAttribute UserDto loginUser,
                                                  MultipartFile imgFile){ //임시 저장된 파일(blob) 온다
        if(!imgFile.isEmpty()){
            Path path= Paths.get(imgPath+"/a.jpeg");
            try {
                imgFile.transferTo(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        AjaxSateHandler ajaxSateHandler=new AjaxSateHandler();
        int register=0;
        register=replyService.registerOne(reply);
        ajaxSateHandler.setState(register);
        return ajaxSateHandler;
        //return "{\"state\":"+register+"}";
    }
    @GetMapping("/modify.do")
    public String modify(int replyNo,
                         @SessionAttribute UserDto loginUser,
                         Model model){
        ReplyDto reply=replyService.detail(replyNo);
        model.addAttribute("reply",reply);
        return "/reply/modify";
    }
    @PutMapping("/modify.do")
    public @ResponseBody AjaxSateHandler modify(ReplyDto reply,
                                                @SessionAttribute UserDto loginUser){
        AjaxSateHandler ajaxSateHandler=new AjaxSateHandler();
        int modify=replyService.modifyOne(reply);
        ajaxSateHandler.setState(modify);
        return  ajaxSateHandler;
    }
}
