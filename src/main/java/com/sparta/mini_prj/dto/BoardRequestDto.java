package com.sparta.mini_prj.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardRequestDto {

    private String title;
    private String writer;
    private String content;

}
