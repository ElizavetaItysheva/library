package com.skypro.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
@RequestMapping("/skypro")
public class SpringMVCController implements WebMvcConfigurer {
    @RequestMapping("/web")
    public String getView(){
        return "dashboard";

    }
}
