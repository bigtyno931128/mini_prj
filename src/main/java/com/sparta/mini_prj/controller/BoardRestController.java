package com.sparta.mini_prj.controller;

import com.sparta.mini_prj.models.Board;
import com.sparta.mini_prj.repositoty.BoardRepository;
import com.sparta.mini_prj.dto.BoardRequestDto;
import com.sparta.mini_prj.service.BoardService;
import com.sparta.mini_prj.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성합니다.
@RestController // JSON으로 데이터를 주고받음을 선언합니다.
@RequestMapping("/api")
public class BoardRestController {

    private final BoardRepository boardRepository;
    private final BoardService boardService;

    // 전체 게시글 목록 조회
    @GetMapping("/board")
    public List<Board> getBoard() {
        return boardRepository.findAllByOrderByModifiedAtDesc();
    }

    // 신규 게시글 등록
    @PostMapping("/board")
    public Board createProduct(@RequestBody BoardRequestDto requestDto,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        requestDto.setWriter(userDetails.getUsername());
        return boardService.creatBoard(requestDto);
    }

     //상세 게시글 목록 조회
    @GetMapping("/board/detail")
    public ModelAndView getDetail(@RequestParam Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return boardService.getComments(id, userDetails);
    }
}
