package com.bigdata6.spring_mybatis.dto;

import lombok.Data;

import java.util.Date;
@Data
public class ReplyDto {
    private int replyNo;//reply_no
    private String title;//title
    private String contents;//contents
    private Date postTime;//post_time
    private String impPath;//img_path
    private int boardNo;//board_no
    private String userId;//user_id
    private Integer fkReplyNo;//fk_reply_no
}
