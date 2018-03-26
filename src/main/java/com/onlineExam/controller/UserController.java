package com.onlineExam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/login")
    public String login(String userName, String passWord, ModelMap model){
        model.addAttribute("message", userName);
        return "index";
    }

}
