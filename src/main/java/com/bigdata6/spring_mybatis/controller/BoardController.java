package com.bigdata6.spring_mybatis.controller;

import com.bigdata6.spring_mybatis.dto.*;
import com.bigdata6.spring_mybatis.service.BoardService;
import com.bigdata6.spring_mybatis.service.ReplyService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    private BoardService boardService;
    private ReplyService replyService;
    private Logger log= LoggerFactory.getLogger(this.getClass().getSimpleName());
    @Value("${img.upload.path}")
    private String imgPath;
    public BoardController(BoardService boardService,ReplyService replyService) {
        this.boardService = boardService;
        this.replyService = replyService;
    }

    @GetMapping("/list.do")
    public String list(Model model,
                       PagingDto paging,
                       HttpServletRequest req){
        //PagingDto(param) : page,rows,orderFiled,direct
        //PagingDto(nav) : totalRows(set),startRow,totalPage,startPage,endPage,nextPage,prevPage,next,prev
        if(paging.getOrderField()==null)paging.setOrderField("board_no");
        paging.setQueryString(req.getParameterMap());
        List<BoardDto> boardList=boardService.list(paging);
        model.addAttribute("boardList",boardList);
        model.addAttribute("paging",paging);
        log.info(paging.toString());

        return "/board/list";
    }
    @GetMapping("/{boardNo}/detail.do")
    public String detail(@PathVariable int boardNo,
                         PagingDto paging,
                         HttpServletRequest req,
                         Model model){
        //pathVariable : 파라미터가 쿼리스트링으로 오는 것이 보기 좋지 않고 명시적이지 않아서 등장
        //pathVariable : required=true 를 무조건 갖는다.
        paging.setQueryString(req.getParameterMap());
        BoardDto board=boardService.detail(boardNo);
        List<ReplyDto> replyList=replyService.boardDetailList(boardNo,paging);
        model.addAttribute("board",board);
        model.addAttribute("replyList",replyList);
        model.addAttribute("paging",paging);
        return  "/board/detail";
    }
    @GetMapping("/register.do")
    public void register(@SessionAttribute UserDto loginUser){}
    @PostMapping("/register.do")
    public String register(
            BoardDto board,
            @SessionAttribute UserDto loginUser,
            @RequestParam(required = false, name = "imgFile") MultipartFile [] imgFiles

        ){
        int register=0;
        if(loginUser.getUser_id().equals(board.getUserId())){
            try {
                List<BoardImgDto> boardImgList=new ArrayList<BoardImgDto>();
                for(MultipartFile imgFile : imgFiles){
                    if(imgFile!=null && !imgFile.isEmpty()){
                        String []contentsTypes=imgFile.getContentType().split("/");
                        if (contentsTypes[0].equals("image")){
                            try {
                                String fileName="board_"+System.currentTimeMillis()+"_"+(int)(Math.random()*10000)+"."+contentsTypes[1];
                                Path path =Paths.get(imgPath+"/"+fileName);
                                imgFile.transferTo(path);
                                BoardImgDto boardImg=new BoardImgDto();
                                boardImg.setImgPath(fileName);
                                boardImgList.add(boardImg);
                            }catch (Exception e){
                                log.error(e.getMessage());
                            }
                        }
                    }
                }
                board.setBoardImgList(boardImgList);
                register=boardService.register(board);


            }catch (Exception e){
                log.error(e.getMessage());
            }
        }
        if(register>0){
            return "redirect:/board/"+board.getBoardNo()+"/detail.do";
        }else {
            return "redirect:/board/register.do";
        }
    }
    @GetMapping("/{boardNo}/modify.do")
    public String modify(@PathVariable int boardNo,
                         @SessionAttribute UserDto loginUser,
                         Model model){
        BoardDto board=boardService.detail(boardNo);
        model.addAttribute("board",board);
        return  "/board/modify";
    }

}
