package com.onlineExam.controller;

import com.onlineExam.GradeSearchHelp;
import com.onlineExam.entity.Grades;
import com.onlineExam.entity.Student;
import com.onlineExam.entity.StudentTP;
import com.onlineExam.service.Course.ICourseService;
import com.onlineExam.service.Grades.IGradesService;
import com.onlineExam.service.Student.IStudentService;
import com.onlineExam.service.StudentTP.IStudentTPService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/studentPage")
public class StudentController {

    @Autowired
    IStudentService studentService;
    @Autowired
    ICourseService courseService;
    @Autowired
    IGradesService gradesService;
    @Autowired
    IStudentTPService studentTPService;

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
     * 学生主页
     * @return 页面
     */
    @RequestMapping("studentHome")
    public String studentHome(){
        return "StudentPage/studentHome";
    }
    //注销
    @RequestMapping("studentLogOut")
    public String logOut(HttpSession session){
        session.setAttribute("currentUser",null);
        return "LoginPage/studentLogin";
    }
    //修改信息页面
    @RequestMapping("studentUpdatePage")
    public String studentUpdatePage(){
        return "StudentPage/studentUpdate";
    }
    //修改操作
    @RequestMapping("studentUpdate")
    @ResponseBody
    public Boolean studentUpdate(@RequestBody Student student, HttpSession session){
        Student stu = (Student)session.getAttribute("currentUser");
        if(studentService.getByUuid(student.getUuid()) == null)
            return false;
        if(!studentService.allowToSave(student))
            return false;
        studentService.update(student);
        session.setAttribute("currentUser", student);
        return true;
    }

    /********************************************成绩显示功能************************************************************/

    /**
     * 跳转到成绩查询页面
     * @return 页面
     */
    @RequestMapping("studentSearchBar")
    public String gradeSearchBar(){
        return "StudentPage/studentSearchBar";
    }

    /**
     * 成绩分页显示
     * @param index 页号
     * @param session 全局
     * @param model 模型
     * @return 页面
     */
    @RequestMapping("studentTPs/{index}")
    public String studentGradesByPage(@PathVariable(value = "index")int index, HttpSession session, ModelMap model){

        Student student = (Student)session.getAttribute("currentUser");
        int pageSize = 2;
        int studentTPNum = studentTPService.findRecordNumByPage(DetachedCriteria.forClass(StudentTP.class)
                            .add(Restrictions.eq("student", student)));
        int pageNum = studentTPNum/pageSize;
        pageNum = studentTPNum % pageSize == 0 ? pageNum : pageNum+ 1;


        List<StudentTP> list = studentTPService.findByStudent(student,(index-1)*pageSize, pageSize);

        model.addAttribute("studentTPs", list);
        model.addAttribute("num", pageNum);
        return "StudentPage/studentGrade";
    }

    /**
     * 按条件查询
     * @param after 在此时间后
     * @param before 在此时间前
     * @param min 最小分数
     * @param max 最大分数
     * @param index 页号
     * @param session 全局
     * @param model 模型
     * @return 页面
     */
    @RequestMapping("studentTPSearch/{index}")
    public String studentGradesByPage(String after, String before, Integer min, Integer max, @PathVariable(value = "index")int index,
                                      HttpSession session, ModelMap model){

        //保留查询条件
        model.addAttribute("after", after);
        model.addAttribute("before", before);
        model.addAttribute("min", min);
        model.addAttribute("max", max);

        //时间检验
        Timestamp aTime, bTime;
        if(after.equals("")) after = "0000-01-01 00:00:00";
        else after = after + " 00:00:00";
        if(before.equals("")) before = "3000-01-01 00:00:00";
        else before = before + " 00:00:00";
        aTime = Timestamp.valueOf(after);
        bTime = Timestamp.valueOf(before);
        //比较时间大小，使其正常化
        if(aTime.compareTo(bTime) > 0) {
            Timestamp mid = aTime;
            aTime = bTime;
            bTime = mid;
        }
        //使后一个时间增加23：59：59
        bTime.setTime(bTime.getTime() + 86399000);

        //分数检验
        if(min == null)
            min = 0;
        if(max == null)
            max = 100;

        //获取当前学生
        Student student = (Student)session.getAttribute("currentUser");
        //页面大小
        int pageSize = 2;
        //记录数
        int studentTPNum = studentTPService.recordOfTimeGradeStudent(student,aTime,bTime,min,max);
        //页面数
        int pageNum = studentTPNum/pageSize;
        pageNum = studentTPNum % pageSize == 0 ? pageNum : pageNum+ 1;

        List<StudentTP> list = studentTPService.findByTimeGradeStudent(student, aTime, bTime, min, max, (index-1)*pageSize, pageSize);

        model.addAttribute("studentTPs", list);
        model.addAttribute("num", pageNum);
        return "StudentPage/studentGrade";
    }

}
