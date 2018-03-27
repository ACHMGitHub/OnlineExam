package com.onlineExam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String stuLoginPage(){
        return "studentLogin";
    }

    @RequestMapping(value = "/teacherLogin", method = RequestMethod.GET)
    public String tchLoginPage(){
        return "teacherLogin";
    }

    @RequestMapping(value = "/adminLogin", method = RequestMethod.GET)
    public String adminLoginPage(){
        return "adminLogin";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String addAdmin(ModelMap model){
        model.addAttribute("message","success");
        return "index";
    }
}
