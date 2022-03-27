package com.sparta.mini_prj.repositoty;

import com.sparta.mini_prj.models.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {

    List<Board> findAllByOrderByModifiedAtDesc();
    List<Board> findAllByModifiedAtBetweenOrderByCreatedAtDesc(LocalDateTime start, LocalDateTime end);
}