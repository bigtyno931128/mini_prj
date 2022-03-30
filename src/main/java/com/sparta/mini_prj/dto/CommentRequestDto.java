package com.sparta.mini_prj.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentRequestDto{

    private String writer;
    private Long boardId;
    private String content;


}