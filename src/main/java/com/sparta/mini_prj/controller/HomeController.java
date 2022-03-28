package com.sparta.mini_prj.controller;


import com.sparta.mini_prj.models.Board;
import com.sparta.mini_prj.repositoty.BoardRepository;
import com.sparta.mini_prj.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final BoardRepository boardRepository;
    //메인 페이지
    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
        } else {
            model.addAttribute("username","");
        }
        return "index";
    }
    //상세 페이지
    @GetMapping("/board/view")
    public String detail(@RequestParam("id") Long id,Model model) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("null"));
        model.addAttribute("writer",board.getWriter());
       ;
        return "detail";
    }
}
