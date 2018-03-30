package com.onlineExam.controller;

import com.onlineExam.entity.Admin;
import com.onlineExam.entity.Student;
import com.onlineExam.entity.Teacher;
import com.onlineExam.service.Admin.IAdminService;
import com.onlineExam.service.Student.IStudentService;
import com.onlineExam.service.Teacher.ITeacherService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.hibernate.Session;
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
@RequestMapping("/adminPage")
public class AdminController {

    @Autowired
    IAdminService adminService;
    @Autowired
    IStudentService studentService;
    @Autowired
    ITeacherService teacherService;

    //页面显示辅助
    @RequestMapping("homePage")
    public String homePage(){
        return "home";
    }
    @RequestMapping("footPage")
    public String footPage(){
        return "home";
    }

    /********************************************************************************************************/
    //管理员主页
    @RequestMapping("adminHome")
    public String adminHome(ModelMap model){
        model.addAttribute("message","success");
        return "AdminPage/adminHome";
    }
    //注销
    @RequestMapping("adminLogOut")
    public String logOut(HttpSession session){
        session.setAttribute("currentUser",null);
        return "LoginPage/adminLogin";
    }

    /******************************管理员信息管理****************************************************************/
    @RequestMapping("adminInfoByPage/{index}")
    public String adminInfo(@PathVariable(value="index")int index, ModelMap model){

        int pageSize = 2;
        int adminNum = adminService.findRecordNumByPage(DetachedCriteria.forClass(Admin.class));
        int pageNum = adminNum/pageSize;
        pageNum = adminNum % pageSize == 0 ? pageNum : pageNum + 1;

        List<Admin> list = adminService.findAllByPage((index-1)*pageSize,pageSize);

        model.addAttribute("admins", list);
        model.addAttribute("num", pageNum);

        return "AdminPage/admin_info";
    }
    //管理员删除操作
    @RequestMapping("adminDelete/{id}")
    public String adminDelete(@PathVariable(value="id")String id){
        adminService.delete(id);
        return "redirect:/adminPage/adminInfoByPage/1";
    }
    //管理员添加页面
    @RequestMapping("adminAddPage")
    public String adminAddPage(){
        return "AdminPage/adminAdd";
    }
    //用户名唯一性
    @RequestMapping("adminIdCheck")
    @ResponseBody
    public Boolean adminIdCheck(@RequestBody String id){
        id = id.substring(3);
        return adminService.idUnique(id);
    }
    //管理员添加操作
    @RequestMapping("adminAdd")
    @ResponseBody
    public Boolean adminAdd(@RequestBody Admin admin){
        if(adminService.allowToSave(admin)){
            adminService.save(admin);
            return true;
        }
        else
            return false;
    }
    //管理员修改页面
    @RequestMapping("adminUpdatePage")
    public String adminUpdatePage(){
        return "AdminPage/adminUpdate";
    }
    //管理员修改操作
    @RequestMapping("adminUpdate")
    @ResponseBody
    public Boolean adminUpdate(@RequestBody Admin admin, HttpSession session){
        Admin oldAdmin = (Admin)session.getAttribute("currentUser");
        admin.setUuid(oldAdmin.getUuid());
        admin.setId(oldAdmin.getId());
        adminService.update(admin);
        session.setAttribute("currentUser", admin);
        return true;
    }

    /******************************学生信息管理****************************************************************/
    //学生信息管理
    @RequestMapping("studentInfoByPage/{index}")
    public String studentInfo(@PathVariable(value="index")int index, ModelMap model){

        int pageSize = 2;
        int studentNum = studentService.findRecordNumByPage(DetachedCriteria.forClass(Student.class));
        int pageNum = studentNum/pageSize;
        pageNum = studentNum % pageSize == 0 ? pageNum : pageNum+ 1;

        List<Student> list = studentService.findAllByPage((index-1)*pageSize,pageSize);

        model.addAttribute("students", list);
        model.addAttribute("num", pageNum);

        return "AdminPage/student_info";
    }
    //学生删除操作
    @RequestMapping("studentDelete/{id}")
    public String studentDelete(@PathVariable(value="id")String id){
        studentService.delete(id);
        return "redirect:/adminPage/studentInfoByPage/1";
    }
    //学生添加页面
    @RequestMapping("studentAddPage")
    public String studentAdd(){
        return "AdminPage/studentAdd";
    }
    //用户名唯一性
    @RequestMapping("studentIdCheck")
    @ResponseBody
    public Boolean studentIdCheck(@RequestBody String id){
        id = id.substring(3);
        return studentService.idUnique(id);
    }
    //学生添加操作
    @RequestMapping("studentAdd")
    @ResponseBody
    public Boolean studentAdd(@RequestBody Student student){
        if(studentService.allowToSave(student)){
            studentService.save(student);
            return true;
        }
        else
            return false;
    }

    /******************************教师信息管理****************************************************************/
    //教师信息管理
    @RequestMapping("teacherInfoByPage/{index}")
    public String teacherInfo(@PathVariable(value="index")int index, ModelMap model){

        int pageSize = 2;
        int teacherNum = teacherService.findRecordNumByPage(DetachedCriteria.forClass(Student.class));
        int pageNum = teacherNum/pageSize;
        pageNum = teacherNum % pageSize == 0 ? pageNum : pageNum+ 1;

        List<Teacher> list = teacherService.findAllByPage((index-1)*pageSize,pageSize);

        model.addAttribute("teacher", list);
        model.addAttribute("num", pageNum);

        return "AdminPage/teacher_info";
    }
    //教师删除操作
    @RequestMapping("teacherDelete/{id}")
    public String teacherDelete(@PathVariable(value="id")String id){
        teacherService.delete(id);
        return "redirect:/adminPage/teacherInfoByPage/1";
    }
    //教师添加页面
    @RequestMapping("teacherAddPage")
    public String teacherAdd(){
        return "AdminPage/teacherAdd";
    }
    //用户名唯一性
    @RequestMapping("teacherIdCheck")
    @ResponseBody
    public Boolean teacherIdCheck(@RequestBody String id){
        id = id.substring(3);
        return teacherService.idUnique(id);
    }
    //教师添加操作
    @RequestMapping("teacherAdd")
    @ResponseBody
    public Boolean teacherAdd(@RequestBody Teacher teacher){
        if(teacherService.allowToSave(teacher)){
            teacherService.save(teacher);
            return true;
        }
        else
            return false;
    }
}
