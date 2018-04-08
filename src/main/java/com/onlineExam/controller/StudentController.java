package com.onlineExam.controller;

import com.onlineExam.GradeAsis;
import com.onlineExam.entity.*;
import com.onlineExam.service.Blank.IBlankService;
import com.onlineExam.service.Choice.IChoiceService;
import com.onlineExam.service.Course.ICourseService;
import com.onlineExam.service.Grades.IGradesService;
import com.onlineExam.service.Student.IStudentService;
import com.onlineExam.service.StudentTP.IStudentTPService;
import com.onlineExam.service.TestPaper.ITestPaperService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    @Autowired
    ITestPaperService testPaperService;
    @Autowired
    IChoiceService choiceService;
    @Autowired
    IBlankService blankService;

    private int pageSize = 5;

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

    /********************************************考试功能************************************************************/
    /**
     * 选择课程页面
     * @param model 模型
     * @return 页面
     */
    @RequestMapping("studentExamPage")
    public String studentExamPage(ModelMap model){
        model.addAttribute("courses", courseService.findAll());
        return "StudentPage/exam_chooseCourse";
    }

    @RequestMapping("exam/{cozId}")
    public String showTestPaper(@PathVariable("cozId")Integer courseId, ModelMap model){

        Course course = courseService.findById(courseId);
        TestPaper tp = testPaperService.findByCourse(courseId).get(0);

        //试卷需要的选择题数
        int choiceNum = tp.getChoiceNum();
        //试卷需要的填空题数
        int blankNum = tp.getBlankNum();

        //现有选择题数
        List<Choice> choices = choiceService.findByCourse(course);
        int chNum = choices.size();
        //现有填空题数
        List<Blank> blanks = blankService.findByCourse(course);
        int blNum = blanks.size();

        Random random = new Random();

        List<Choice> c = new ArrayList<Choice>();
        List<Blank> b = new ArrayList<Blank>();

        //随机生成试题
        if(chNum > 0)
            for(int i=0; i<choiceNum; i++)
                c.add(choices.get(random.nextInt(chNum)));
        if(blNum > 0)
            for(int i=0; i<blankNum; i++)
                b.add(blanks.get(random.nextInt(blNum)));

        model.addAttribute("testPaper", tp);
        model.addAttribute("blanks", b);
        model.addAttribute("choices", c);
        return "StudentPage/exam";
    }

    @RequestMapping("addGrade")
    @ResponseBody
    public Boolean addGrade(@RequestBody GradeAsis asis, HttpSession session){
        Student student = (Student) session.getAttribute("currentUser");
        TestPaper testPaper = testPaperService.getByUuid(asis.getId());
        if(testPaper == null || student == null)
            return false;

        StudentTP studentTP = new StudentTP();
        Grades grade = new Grades();
        grade.setGrade(asis.getGrade());
        grade.setStuTestPaper(studentTP);

        studentTP.setGrade(grade);
        studentTP.setStudent(student);
        studentTP.setTestPaper(testPaper);
        studentTP.setStuAnswer("");
        studentTP.setStpTime(new Timestamp(System.currentTimeMillis()));

        studentTPService.saveViaCheck(studentTP);
        gradesService.saveViaCheck(grade);
        return true;
    }
}
