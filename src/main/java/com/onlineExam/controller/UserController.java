package com.onlineExam.controller;

import com.onlineExam.entity.Admin;
import com.onlineExam.entity.Student;
import com.onlineExam.entity.Teacher;
import com.onlineExam.service.Admin.IAdminService;
import com.onlineExam.service.Student.IStudentService;
import com.onlineExam.service.Teacher.ITeacherService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    IAdminService adminService;
    @Autowired
    IStudentService studentService;
    @Autowired
    ITeacherService teacherService;

    @RequestMapping("/test")
    public String login(String userName, String passWord, ModelMap model){

        return "index";
    }

    @RequestMapping("/studentLogin")
    @ResponseBody
    public boolean stuLogin(@RequestBody Student user, HttpSession session){

        Student student = studentService.login(user.getId(), user.getPw());
        if (student != null){
            session.setAttribute("currentUser", student);
            return true;
        }
        else
            return false;
    }

    @RequestMapping("/teacherLogin")
    @ResponseBody
    public boolean tchLogin(@RequestBody Teacher user, HttpSession session){
        Teacher teacher = teacherService.login(user.getId(), user.getPw());
        if (teacher != null){
            session.setAttribute("currentUser", teacher);
            return true;
        }
        else
            return false;
    }

    @RequestMapping("/adminLogin")
    @ResponseBody
    public boolean adminLogin(@RequestBody Admin user, HttpSession session){

        Admin admin = adminService.login(user.getId(), user.getPw());
        if (admin != null){
            session.setAttribute("currentUser", admin);
            return true;
        }
        else
            return false;
    }


}
