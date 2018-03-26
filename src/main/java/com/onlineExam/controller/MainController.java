package com.onlineExam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homePage(){
        return "login";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String addAdmin(ModelMap model){
        model.addAttribute("message","success");
        return "index";
    }
}
