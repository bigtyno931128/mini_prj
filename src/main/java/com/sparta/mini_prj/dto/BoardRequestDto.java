package com.sparta.mini_prj.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BoardRequestDto {

    private String title;
    private String writer;
    private String content;

    public BoardRequestDto(String title, String writer, String content) {
    }
}
