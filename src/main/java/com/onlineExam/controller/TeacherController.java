package com.onlineExam.controller;

import com.onlineExam.entity.Grades;
import com.onlineExam.entity.Student;
import com.onlineExam.entity.Teacher;
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


    @RequestMapping("teacherUpdatePage")
    public String teacherUpdatePage(){
        return "TeacherPage/teacherUpdate";
    }

    @RequestMapping("teacherUpdate")
    @ResponseBody
    public Boolean teacherUpdate(@RequestBody Teacher teacher, HttpSession session){
        System.out.println("这里输出字符串"+teacher);
        Teacher tch = (Teacher) session.getAttribute("currentUser");
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


}
