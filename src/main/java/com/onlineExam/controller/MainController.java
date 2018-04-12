package com.onlineExam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {


    @RequestMapping(value = "/studentLogin", method = RequestMethod.GET)
    public String stuLoginPage(){
        return "LoginPage/studentLogin";
    }

    @RequestMapping(value = "/teacherLogin", method = RequestMethod.GET)
    public String tchLoginPage(){
        return "LoginPage/teacherLogin";
    }

    @RequestMapping(value = "/adminLogin", method = RequestMethod.GET)
    public String adminLoginPage(){
        return "LoginPage/adminLogin";
    }

}
