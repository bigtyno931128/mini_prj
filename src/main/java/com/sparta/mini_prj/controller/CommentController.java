package com.sparta.mini_prj.controller;


import com.sparta.mini_prj.dto.CommentRequestDto;

import com.sparta.mini_prj.models.Board;
import com.sparta.mini_prj.models.Comment;
import com.sparta.mini_prj.repositoty.BoardRepository;
import com.sparta.mini_prj.repositoty.CommentRepository;
import com.sparta.mini_prj.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성합니다.
@RestController
public class CommentController {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    //댓글 등록
    // 신규 게시글 등록

    @PostMapping("/api/board/{id}/comment")
    public Comment createComment(@RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id) {

         boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("null"));

        requestDto.setWriter(userDetails.getUsername());
        requestDto.setBoardId(id);

        Comment comment= new Comment(requestDto);
        System.out.println(comment);
        commentRepository.save(comment);
        return comment;
    }

    // 게시물에 해당하는 댓글 목록 가져오기
//    @GetMapping("/api/board/{id}/comment")
//    public List<Comment> getComment( @PathVariable Long id) {
//        return commentRepository.findAll();
//    }
}