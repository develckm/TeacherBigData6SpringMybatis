package com.bigdata6.spring_mybatis.dto;

import lombok.Data;

@Data
public class BoardPreferDto {
    private  int boardPreferNo;//board_prefer_no;
    private  int  boardNo;//      board_no
    private  boolean prefer;
    private String userId;//user_id
}
