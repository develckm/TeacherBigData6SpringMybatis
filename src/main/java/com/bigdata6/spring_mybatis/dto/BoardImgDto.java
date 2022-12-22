package com.bigdata6.spring_mybatis.dto;

import lombok.Data;

@Data
public class BoardImgDto {
    private  int boardImgNo;//board_img_no
    private  int boardNo;//fk board.board_no
    private String imgPath;//img_path
}
