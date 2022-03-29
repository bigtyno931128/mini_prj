package com.sparta.mini_prj.models;

import com.sparta.mini_prj.dto.BoardRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    @Test
    @DisplayName("정상 케이스")
    void createBoard() {
        //given
//        Long id = 1L;
        String title = "게시글 제목입니다";
        String writer = "문현상";
        String content = "게시글 내용입니다";

        BoardRequestDto requestDto = new BoardRequestDto(
                title,
                writer,
                content
        );
        //when
        Board board = new Board(requestDto);

        //then
            assertNull(board.getId());

            assertEquals(title,board.getTitle() );
            assertEquals(writer,board.getWriter());
            assertEquals(content,board.getContent());
    }
}


