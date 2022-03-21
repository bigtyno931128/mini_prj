package com.sparta.mini_prj.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/board/view")
    public String detail(@RequestParam("id") Long id) {
        System.out.println(id);
        return "/detail.html";
    }

}
