package com.sparta.mini_prj.service;


import com.sparta.mini_prj.dto.BoardRequestDto;
import com.sparta.mini_prj.models.Board;
import com.sparta.mini_prj.repositoty.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {


    private final BoardRepository boardRepository;

    @Transactional
    public Long updateBoard(Long id, BoardRequestDto requestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 게시물이 존재하지 않습니다."));


        board.update(requestDto);
        return board.getId();
    }
}
