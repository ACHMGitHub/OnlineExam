package com.onlineExam.controller;

import com.onlineExam.entity.*;
import com.onlineExam.service.Admin.IAdminService;
import com.onlineExam.service.Blank.IBlankService;
import com.onlineExam.service.Choice.IChoiceService;
import com.onlineExam.service.Course.ICourseService;
import com.onlineExam.service.Student.IStudentService;
import com.onlineExam.service.Teacher.ITeacherService;
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
    @Autowired
    ICourseService courseService;
    @Autowired
    IBlankService blankService;
    @Autowired
    IChoiceService choiceService;

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
        int adminNum = adminService.recordNum();
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
    /**
     * 学生信息分页显示
     * @param index
     * @param model
     * @return
     */
    @RequestMapping("studentInfoByPage/{index}")
    public String studentInfo(@PathVariable(value="index")int index, ModelMap model){

        int pageSize = 2;
        int studentNum = studentService.recordNum();
        int pageNum = studentNum/pageSize;
        pageNum = studentNum % pageSize == 0 ? pageNum : pageNum+ 1;

        List<Student> list = studentService.findAllByPage((index-1)*pageSize,pageSize);

        model.addAttribute("students", list);
        model.addAttribute("num", pageNum);

        return "AdminPage/student_info";
    }
    /**
     * 学生删除操作
     * @param id
     * @return
     */
    @RequestMapping("studentDelete/{id}")
    public String studentDelete(@PathVariable(value="id")String id){
        studentService.delete(id);
        return "redirect:/adminPage/studentInfoByPage/1";
    }
    /**
     * 添加学生页面
     * @return
     */
    @RequestMapping("studentAddPage")
    public String studentAdd(){
        return "AdminPage/studentAdd";
    }
    /**
     * 验证学生用户名唯一性
     * @param id
     * @return
     */
    @RequestMapping("studentIdCheck")
    @ResponseBody
    public Boolean studentIdCheck(@RequestBody String id){
        id = id.substring(3);
        return studentService.idUnique(id);
    }
    /**
     * 添加学生操作
     * @param student
     * @return
     */
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

    /**
     * 学生修改页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("studentUpdatePage/{id}")
    public String studentUpdatePage(@PathVariable(value = "id") int id, ModelMap model){
        Student student = studentService.getByUuid(id);
        model.addAttribute("student", student);
        return "AdminPage/studentUpdate";
    }

    /**
     * 学生修改操作
     * @param student
     * @return
     */
    @RequestMapping("studentUpdate")
    @ResponseBody
    public Boolean studentUpdate(@RequestBody Student student){
        if(!studentService.allowToSave(student))
            return false;
        studentService.update(student);
        return true;
    }


    /******************************教师信息管理****************************************************************/
    /**
     * 教师信息页面
     * @param index 页号
     * @param model
     * @return
     */
    @RequestMapping("teacherInfoByPage/{index}")
    public String teacherInfo(@PathVariable(value="index")int index, ModelMap model){

        int pageSize = 2;
        int teacherNum = teacherService.recordNum();
        int pageNum = teacherNum/pageSize;
        pageNum = teacherNum % pageSize == 0 ? pageNum : pageNum+ 1;

        List<Teacher> list = teacherService.findAllByPage((index-1)*pageSize,pageSize);

        model.addAttribute("teacher", list);
        model.addAttribute("num", pageNum);

        return "AdminPage/teacher_info";
    }
    /**
     * 教师删除操作
     * @param id 教师主码
     * @return
     */
    @RequestMapping("teacherDelete/{id}")
    public String teacherDelete(@PathVariable(value="id")String id){
        teacherService.delete(id);
        return "redirect:/adminPage/teacherInfoByPage/1";
    }
    /**
     * 教师添加页面
     * @return
     */
    @RequestMapping("teacherAddPage")
    public String teacherAdd(){
        return "AdminPage/teacherAdd";
    }
    /**
     * 验证教师用户名唯一性
     * @param id
     * @return
     */
    @RequestMapping("teacherIdCheck")
    @ResponseBody
    public Boolean teacherIdCheck(@RequestBody String id){
        id = id.substring(3);
        return teacherService.idUnique(id);
    }
    /**
     * 教师添加操作
     * @param teacher
     * @return
     */
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
    /**
     * 教师修改页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("teacherUpdatePage/{id}")
    public String teacherUpdatePage(@PathVariable(value = "id") int id, ModelMap model){
        Teacher teacher = teacherService.getByUuid(id);
        model.addAttribute("teacher", teacher);
        return "AdminPage/teacherUpdate";
    }

    /**
     * 学生修改操作
     * @param teacher
     * @return
     */
    @RequestMapping("teacherUpdate")
    @ResponseBody
    public Boolean teacherUpdate(@RequestBody Teacher teacher){
        if(!teacherService.allowToSave(teacher))
            return false;
        teacherService.update(teacher);
        return true;
    }
    /******************************课程信息管理****************************************************************/
    /**
     * 课程信息分页显示页面
     * @param index
     * @param model
     * @return
     */
    @RequestMapping("courseInfoByPage/{index}")
    public String courseInfoByPage(@PathVariable(value="index")int index, ModelMap model){
        int pageSize = 2;
        int courseNum = courseService.recordNum();
        int pageNum = courseNum/pageSize;
        pageNum = courseNum % pageSize == 0 ? pageNum : pageNum+ 1;

        List<Course> list = courseService.findAllByPage((index-1)*pageSize,pageSize);

        model.addAttribute("courses", list);
        model.addAttribute("num", pageNum);
        return "AdminPage/course_info";
    }

    /**
     * 删除操作
     * @param id
     * @return
     */
    @RequestMapping("courseDelete/{id}")
    public String courseDelete(@PathVariable(value="id")int id){
        courseService.delete(id);
        return "redirect:/adminPage/courseInfoByPage/1";
    }
    /******************************填空题信息管理****************************************************************/
    /**
     * 填空题分页显示
     * @param index
     * @param model
     * @return
     */
    @RequestMapping("blankInfoByPage/{index}")
    public String blankInfoByPage(@PathVariable(value="index")int index, ModelMap model){
        int pageSize = 2;
        int blankNum = blankService.recordNum();
        int pageNum = blankNum/pageSize;
        pageNum = blankNum % pageSize == 0 ? pageNum : pageNum+ 1;

        List<Blank> list = blankService.findAllByPage((index-1)*pageSize,pageSize);

        model.addAttribute("blanks", list);
        model.addAttribute("num", pageNum);
        return "AdminPage/blank_info";
    }
    /**
     * 填空题详细信息
     * @return
     */
    @RequestMapping("blankMoreInfo/{id}")
    public String blankMoreInfo(@PathVariable(value="id")int id, ModelMap model){
        Blank blank = blankService.getByUuid(id);
        model.addAttribute("blank",blank);
        return "AdminPage/blankMoreInfo";
    }
    /******************************选择题信息管理****************************************************************/
    /**
     * 选择题分页显示页面
     * @param index
     * @param model
     * @return
     */
    @RequestMapping("choiceInfoByPage/{index}")
    public String choiceInfoByPage(@PathVariable(value="index")int index, ModelMap model){
        int pageSize = 2;
        int choiceNum = choiceService.recordNum();
        int pageNum = choiceNum/pageSize;
        pageNum = choiceNum % pageSize == 0 ? pageNum : pageNum+ 1;

        List<Choice> list = choiceService.findAllByPage((index-1)*pageSize,pageSize);

        model.addAttribute("choices", list);
        model.addAttribute("num", pageNum);
        return "AdminPage/choice_info";
    }
    /**
     * 选择题详细信息
     * @return
     */
    @RequestMapping("choiceMoreInfo/{id}")
    public String choiceMoreInfo(@PathVariable(value="id")int id, ModelMap model){
        Choice choice = choiceService.getByUuid(id);
        model.addAttribute("choice",choice);
        return "AdminPage/choiceMoreInfo";
    }
}
