package com.sparta.mini_prj.controller;

import com.sparta.mini_prj.models.Board;

import com.sparta.mini_prj.repositoty.BoardRepository;
import com.sparta.mini_prj.dto.BoardRequestDto;
import com.sparta.mini_prj.service.BoardService;
import com.sparta.mini_prj.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성합니다.
@RestController // JSON으로 데이터를 주고받음을 선언합니다.
public class BoardRestController {

    private final BoardRepository boardRepository;
    private final BoardService boardService;
//    private final CommentController commentController;
//    private final CommentRepository commentRepository;

    // 전체 게시글 목록 조회
    @GetMapping("/api/board")
    public List<Board> getBoard() {
        return boardRepository.findAllByOrderByModifiedAtDesc();
    }

    // 신규 게시글 등록
    @PostMapping("/api/board")
    public Board createBoard(@RequestBody BoardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        requestDto.setWriter(userDetails.getUsername());
        Board board = new Board(requestDto);
        boardRepository.save(board);
        return board;
    }

     //상세 게시글 조회
    @GetMapping("/api/board/{id}")
    public Board getBoardDetail(@PathVariable Long id) {

        return boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("null"));
    }

    // 게시글 수정
    @PutMapping("/api/board/{id}")
    public Long updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto){
       boardService.updateBoard(id,requestDto);

        return id;
    }

    // 게시글 삭제
    @DeleteMapping("/api/board/{id}")
    public void deleteBoard(@PathVariable Long id){
        boardRepository.deleteById(id);
    }

    //상세 페이지
//    @GetMapping("/board/view/{id}")
//    public String detail(@PathVariable Long id, Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//
//        //게시글 넘기기
//        Board board = boardRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("null"));
//
//        model.addAttribute("board", board);
//
//        //댓글 넘기기
//        List<Comment> comment = commentRepository.findAllByContentOrderByCreatedAtDesc(id);
//        if (comment.size() != 0){
//            model.addAttribute("commentResult", comment);
//        } else {
//            model.addAttribute("commentResult", "NoValue");
//        }
//
//        // 로그인 아이디 넘기기
//        if (userDetails != null) {
//            model.addAttribute("loginUsername", userDetails.getUsername());
//        } else {
//            model.addAttribute("loginUsername", null);
//        }
//        return "detail";
//    }
}
