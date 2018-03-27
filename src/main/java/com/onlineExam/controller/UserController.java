package com.onlineExam.controller;

import com.onlineExam.User;
import com.onlineExam.entity.Admin;
import com.onlineExam.entity.Student;
import com.onlineExam.service.Admin.AdminService;
import com.onlineExam.service.Admin.IAdminService;
import com.onlineExam.service.Student.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    IAdminService adminService;
    @Autowired
    IStudentService studentService;

    @RequestMapping("/test")
    public String login(String userName, String passWord, ModelMap model){

        Admin admin = adminService.login(userName, passWord);
        if(admin != null)
            model.addAttribute("message", admin.getName() + " " + admin.getPw());
        else
            model.addAttribute("message","Fail");
        return "index";
    }

    @RequestMapping("/studentLogin")
    @ResponseBody
    public boolean stuLogin(@RequestBody User user, HttpSession session){

        Student student = studentService.login(user.getUserName(), user.getPassWord());
        if (student != null){
            session.setAttribute("currentUser", student);
            return true;
        }
        else
            return false;
    }

    @RequestMapping("/teacherLogin")
    @ResponseBody
    public boolean tchLogin(@RequestBody User user, HttpSession session){
        Admin admin = adminService.login(user.getUserName(), user.getPassWord());
        if (admin != null){
            session.setAttribute("currentUser", admin);
            return true;
        }
        else
            return false;
    }

    @RequestMapping("/adminLogin")
    @ResponseBody
    public boolean adminLogin(@RequestBody User user, HttpSession session){

        Admin admin = adminService.login(user.getUserName(), user.getPassWord());
        if (admin != null){
            session.setAttribute("currentUser", admin);
            return true;
        }
        else
            return false;
    }
}
