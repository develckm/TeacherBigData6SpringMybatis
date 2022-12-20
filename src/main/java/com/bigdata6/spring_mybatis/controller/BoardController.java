package com.bigdata6.spring_mybatis.controller;

import com.bigdata6.spring_mybatis.dto.BoardDto;
import com.bigdata6.spring_mybatis.dto.PagingDto;
import com.bigdata6.spring_mybatis.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    private BoardService boardService;
    private Logger log= LoggerFactory.getLogger(this.getClass().getSimpleName());
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
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
                         Model model){
        //pathVariable : 파라미터가 쿼리스트링으로 오는 것이 보기 좋지 않고 명시적이지 않아서 등장
        //pathVariable : required=true 를 무조건 갖는다.
        BoardDto board=boardService.detail(boardNo);
        model.addAttribute("board",board);
        return  "/board/detail";
    }
}
