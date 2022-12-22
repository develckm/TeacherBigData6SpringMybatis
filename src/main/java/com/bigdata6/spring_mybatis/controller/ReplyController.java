package com.bigdata6.spring_mybatis.controller;

import com.bigdata6.spring_mybatis.dto.PagingDto;
import com.bigdata6.spring_mybatis.dto.ReplyDto;
import com.bigdata6.spring_mybatis.dto.UserDto;
import com.bigdata6.spring_mybatis.service.ReplyService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/reply")
public class ReplyController {
    private ReplyService replyService;
    private Logger log=LoggerFactory.getLogger(this.getClass());
    @Value("${img.upload.path}") //유저간의 약속된 이미지 업로드 경로
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
        ReplyDto reply=replyService.detail(replyNo);
        int remove=replyService.removeOne(replyNo);
        ajaxSateHandler.setState(remove);
        if(remove>0 && reply.getImgPath()!=null){
            File originImgFile=new File(imgPath+"/"+reply.getImgPath());
            boolean del=originImgFile.delete();
            log.info("원본 이미지 삭제: ",del);
        }

        return  ajaxSateHandler;
    }

    @PostMapping("/register.do")
    public @ResponseBody AjaxSateHandler register(ReplyDto reply,
                                                  @SessionAttribute UserDto loginUser,
                                                  MultipartFile imgFile){ //임시 저장된 파일(blob) 온다
        //MultipartFile input type=file name=imgFile 있으면 무조건 null 이 아니다.
        if(!imgFile.isEmpty()){
            String [] contentsTypes=imgFile.getContentType().split("/");//  image/jpeg => {"image","jpeg"}
            if(contentsTypes[0].equals("image")){
                String fileName="reply_"+System.currentTimeMillis()+"_"+(int)(Math.random()*10000)+"."+contentsTypes[1];//회사 규칙대로 생성
                Path path= Paths.get(imgPath+"/"+fileName);
                try {
                    imgFile.transferTo(path);
                    reply.setImgPath(fileName);
                    log.info(fileName);
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
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
    public @ResponseBody AjaxSateHandler modify(
            ReplyDto reply,
            @SessionAttribute UserDto loginUser,
            @RequestParam(required = false, name = "imgFile") MultipartFile imgFile
    ){
        AjaxSateHandler ajaxSateHandler=new AjaxSateHandler();
        String originImgPath=reply.getImgPath();  //없으면 null or ""
        if(imgFile!=null&&!imgFile.isEmpty()){
            String[]contentsTypes=imgFile.getContentType().split("/");
            if(contentsTypes[0].equals("image")){
                try {
                    String fileName="reply_"+System.currentTimeMillis()+"_"+(int)(Math.random()*10000)+"."+contentsTypes[1];
                    Path path=Paths.get(imgPath+"/"+fileName);
                    imgFile.transferTo(path); //임시저장된 파일을 실제로 저장
                    reply.setImgPath(fileName);
                }catch (Exception e){
                    log.error(e.getMessage());
                }
            }
        }
        int modify=replyService.modifyOne(reply);
        if(modify>0&&  (originImgPath!=null || !originImgPath.isEmpty())){
            File originImgFile=new File(imgPath+"/"+originImgPath);
            boolean del=originImgFile.delete();
            log.info("원본 이미지 삭제: ",del);
        }
        ajaxSateHandler.setState(modify);
        return  ajaxSateHandler;
    }
}
