package com.onlineExam.controller;

import com.onlineExam.entity.*;
import com.onlineExam.service.Admin.IAdminService;
import com.onlineExam.service.Blank.IBlankService;
import com.onlineExam.service.Choice.IChoiceService;
import com.onlineExam.service.Course.ICourseService;
import com.onlineExam.service.Grades.IGradesService;
import com.onlineExam.service.Student.IStudentService;
import com.onlineExam.service.StudentTP.IStudentTPService;
import com.onlineExam.service.Teacher.ITeacherService;
import com.onlineExam.service.Teacher.TeacherService;
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
@RequestMapping("/teacherPage")
public class TeacherController {


    @Autowired
    IStudentService studentService;
    @Autowired
    ITeacherService teacherService;
    @Autowired
    IBlankService blankService;
    @Autowired
    IChoiceService choiceService;
    @Autowired
    IGradesService gradesService;
    @Autowired
    ICourseService courseService;

    //页面显示辅助
    @RequestMapping("homePage")
    public String homePage(){
        return "home";
    }
    @RequestMapping("footPage")
    public String footPage(){
        return "home";
    }
    /************************************************************************************************************/
    /**
     * 教师主页
     * @return 页面
     */
    @RequestMapping("teacherHome")
    public String studentHome(){
        return "TeacherPage/teacherHome";
    }
    //注销
    @RequestMapping("teacherLogOut")
    public String logOut(HttpSession session){
        session.setAttribute("currentUser",null);
        return "LoginPage/teacherLogin";
    }

    //修改信息页面
    @RequestMapping("teacherUpdatePage")
    public String teacherUpdatePage(){
        return "TeacherPage/teacherUpdate";
    }
    //修改操作
    @RequestMapping("studentUpdate")
    @ResponseBody
    public Boolean teacherUpdate(@RequestBody Teacher teacher, HttpSession session){
        Teacher tch = (Teacher)session.getAttribute("currentUser");
        if(teacherService.getByUuid(teacher.getUuid()) == null)
            return false;
        if(!teacherService.allowToSave(teacher))
            return false;
        teacherService.update(teacher);
        session.setAttribute("currentUser", teacher);
        return true;
    }

    /****************************学生信息管理***********************************************************************/
    /**
     * 学生信息分页显示
     * @param index 页号
     * @param model 模型
     * @return 页面
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

        return "TeacherPage/student_info";
    }



    /****************************成绩信息管理***********************************************************************/
    @RequestMapping("gradesInfoByPage/{index}")
    public String gradesInfoByPage(@PathVariable(value="index")int index, ModelMap model){
        int pageSize = 2;
        int gradesNum = gradesService.recordNum();
        int pageNum = gradesNum/pageSize;
        pageNum = gradesNum % pageSize == 0 ? pageNum : pageNum+ 1;

        List<Grades> list = gradesService.findAllByPage((index-1)*pageSize,pageSize);

        model.addAttribute("grades", list);
        model.addAttribute("num", pageNum);
        return "TeacherPage/grades_info";
    }

/****************************题目信息管理***********************************************************************/
    /**
     * 添加填空题页面
     * @return 页面
     */
    @RequestMapping("addBlankPage")
    public String addBlankPage(ModelMap model){
        model.addAttribute("course", courseService.findAll());
        return "TeacherPage/addBlank";
    }

    /**
     * 添加填空题
     * @param blank 填空题实例
     * @param session 全局
     * @return 是否成功添加
     */
    @RequestMapping("addBlank")
    @ResponseBody
    public Boolean addBlank(@RequestBody Blank blank, HttpSession session){
        Course course = courseService.findById(blank.getCourse().getUuid());
        Teacher teacher = (Teacher) session.getAttribute("currentUser");
        if(course == null || teacher == null)
            return false;
        else{
            blank.setCourse(course);
            blank.setTeacher(teacher);
        }
        if(blankService.allowToSave(blank)){
            blankService.save(blank);
            return true;
        }
        else
            return false;
    }

    /**
     * 添加选择题页面
     * @return 页面
     */
    @RequestMapping("addChoicePage")
    public String addChoicePage(ModelMap model){
        model.addAttribute("course", courseService.findAll());
        return "TeacherPage/addChoice";
    }

    /**
     * 添加选择题
     * @param choice 选择题实例
     * @param session 全局
     * @return 是否成功添加
     */
    @RequestMapping("addChoice")
    @ResponseBody
    public Boolean addChoice(@RequestBody Choice choice, HttpSession session){
        Course course = courseService.findById(choice.getCourse().getUuid());
        Teacher teacher = (Teacher) session.getAttribute("currentUser");
        if(course == null || teacher == null)
            return false;
        else{
            choice.setCourse(course);
            choice.setTeacher(teacher);
        }
        if(choiceService.allowToSave(choice)){
            choiceService.save(choice);
            return true;
        }
        else
            return false;
    }

    @RequestMapping("blankInfoPage/{index}")
    public String blankInfoPage(@PathVariable(value = "index")int index, ModelMap model){
        int pageSize = 2;
        int blankNum = blankService.recordNum();
        int pageNum = blankNum/pageSize;
        pageNum = blankNum % pageSize == 0 ? pageNum : pageNum+ 1;

        List<Blank> list = blankService.findAllByPage((index-1)*pageSize,pageSize);

        model.addAttribute("blanks", list);
        model.addAttribute("num", pageNum);

        return "TeacherPage/blankInfo";
    }
}
