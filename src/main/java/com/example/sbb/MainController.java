package com.example.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody; // html파일처럼 넣기

@Controller
public class MainController{

    @GetMapping("/sbb")
    @ResponseBody
    public String index() {
        return "sbb에 오신걸 환영합니다!";
    }

    @GetMapping("/")
    public String root(){
        return "redirect:/community/list";
    }
}