package com.sparta.mini_prj.controller;


import com.sparta.mini_prj.service.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {

    //메인 페이지.
    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
        } else {
            model.addAttribute("username","");
        }
        return "index";
    }

    //상세페이지 이동.
    @RequestMapping("/board/view")
    public ModelAndView detail(@RequestParam("id") Long id) throws Exception{
        ModelAndView mav = new ModelAndView("detail");
        return mav;
    }
}
