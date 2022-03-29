package com.sparta.mini_prj.repositoty;


import com.sparta.mini_prj.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment,Long> {

}