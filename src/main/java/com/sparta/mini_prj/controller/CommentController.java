package com.sparta.mini_prj.controller;


import com.sparta.mini_prj.dto.CommentRequestDto;

import com.sparta.mini_prj.models.Comment;
import com.sparta.mini_prj.repositoty.BoardRepository;
import com.sparta.mini_prj.repositoty.CommentRepository;
import com.sparta.mini_prj.service.CommentService;
import com.sparta.mini_prj.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;



@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성합니다.
@RestController
public class CommentController {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final CommentService commentService;
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
    // 댓글 수정하기
    @PutMapping("/api/comment/{id}")
    public Long updateComment(@PathVariable("id") long commentId,@RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        requestDto.setWriter(userDetails.getUsername());
        return commentService.update(commentId, requestDto);
    }
    // 댓글 삭제하기
    @DeleteMapping("/api/comment/{id}")
    public void deleteComment(@PathVariable("id") long commentId){
        commentRepository.deleteById(commentId);
    }
}