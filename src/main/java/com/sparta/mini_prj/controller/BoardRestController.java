package com.sparta.mini_prj.controller;

import com.sparta.mini_prj.models.Board;
import com.sparta.mini_prj.models.BoardRepository;
import com.sparta.mini_prj.models.BoardRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성합니다.
@RestController // JSON으로 데이터를 주고받음을 선언합니다.
public class BoardRestController {

    private final BoardRepository boardRepository;


    // 전체 게시글 목록 조회
    @GetMapping("/api/board")
    public List<Board> getBoard() {

        return boardRepository.findAllByOrderByModifiedAtDesc();
    }

    // 신규 게시글 등록
    @PostMapping("/api/board")
    public Board createProduct(@RequestBody BoardRequestDto requestDto) {
        Board board = new Board(requestDto);
        boardRepository.save(board);
        return board;
    }

     //상세 게시글 목록 조회
    @GetMapping("/api/board/{id}")
    public Board getBoardDetail(@PathVariable Long id) {
        return boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("null"));
    }

}
