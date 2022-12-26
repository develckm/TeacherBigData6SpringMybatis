package com.bigdata6.spring_mybatis.dto;

import lombok.Data;

@Data
public class BoardPreferViewDto {
    private int likes;
    private int bads;
    private BoardPreferDto loginUserPrefer;
}
