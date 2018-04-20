package com.onlineExam.controller;

import com.onlineExam.ExcelOperating;
import com.onlineExam.SearchCondition;
import com.onlineExam.entity.*;
import com.onlineExam.service.Blank.IBlankService;
import com.onlineExam.service.Choice.IChoiceService;
import com.onlineExam.service.Course.ICourseService;
import com.onlineExam.service.Grades.IGradesService;
import com.onlineExam.service.Student.IStudentService;
import com.onlineExam.service.StudentTP.IStudentTPService;
import com.onlineExam.service.Teacher.ITeacherService;
import com.onlineExam.service.TestPaper.ITestPaperService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
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
    @Autowired
    ITestPaperService testPaperService;
    @Autowired
    IStudentTPService studentTPService;

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
     * 教师主页
     * @return 页面
     */
    @RequestMapping("teacherHome")
    public String teacherHome(){
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
        int gradesNum = gradesService.recordNum();
        int pageNum = gradesNum/pageSize;
        pageNum = gradesNum % pageSize == 0 ? pageNum : pageNum+ 1;

        List<Grades> list = gradesService.findAllByPage((index-1)*pageSize,pageSize);

        model.addAttribute("grades", list);
        model.addAttribute("num", pageNum);
        return "TeacherPage/grades_info";
    }


    @RequestMapping("gradesInfoSearch/{index}")
    public String gradesInfoSearch(SearchCondition condition, @PathVariable(value = "index")int index, ModelMap model){
        //保留查询条件
        model.addAttribute("condition", condition);

        String after = condition.getAfter();
        String before = condition.getBefore();
        Integer min = condition.getMin();
        Integer max = condition.getMax();
        String stuId = condition.getStuId();
        String className = condition.getClassName();

        //时间检验
        Timestamp aTime, bTime;

        if(after == null || after.equals("")) after = "0000-01-01 00:00:00";
        else after = after + " 00:00:00";
        if(before == null || before.equals("")) before = "3000-01-01 00:00:00";
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

        //学生条件
        if(stuId == null)
            stuId = "";
        if(className == null)
            className = "";

        //记录数
        int studentTPNum = studentTPService.recordOfTimeGradeStudent(stuId,className,aTime,bTime,min,max);
        //页面数
        int pageNum = studentTPNum/pageSize;
        pageNum = studentTPNum % pageSize == 0 ? pageNum : pageNum+ 1;

        List<StudentTP> list = studentTPService.findByTimeGradeStudent(stuId,className, aTime, bTime, min, max, (index-1)*pageSize, pageSize);

        model.addAttribute("studentTPs", list);
        model.addAttribute("num", pageNum);
        return "TeacherPage/gradesInfo";
    }

    /**
     * 描述：导出excel文件
     * @param response
     * @throws Exception
     */
    @RequestMapping(value="gradeExport",method={RequestMethod.GET,RequestMethod.POST})
    public String gradeExportExcel(SearchCondition condition, HttpServletResponse response) throws Exception {
        System.out.println("通过 jquery.form.js 提供的ajax方式导出文件！");
        OutputStream os = null;
        Workbook wb = null;    //工作薄

        try {
            String after = condition.getAfter();
            String before = condition.getBefore();
            Integer min = condition.getMin();
            Integer max = condition.getMax();
            String stuId = condition.getStuId();
            String className = condition.getClassName();

            //时间检验
            Timestamp aTime, bTime;

            if(after == null || after.equals("")) after = "0000-01-01 00:00:00";
            else after = after + " 00:00:00";
            if(before == null || before.equals("")) before = "3000-01-01 00:00:00";
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

            //学生条件
            if(stuId == null)
                stuId = "";
            if(className == null)
                className = "";

            //记录数
            int studentTPNum = studentTPService.recordOfTimeGradeStudent(stuId,className,aTime,bTime,min,max);
            List<StudentTP> list = studentTPService.findByTimeGradeStudent(stuId,className, aTime, bTime, min, max,0, studentTPNum);
            //模拟数据库取值
            List<List<String>> objs = new ArrayList<List<String>>();
            List<String> header = new ArrayList<String>();
            //标题
            header.add("学生学号");
            header.add("学生姓名");
            header.add("班级");
            header.add("课程");
            header.add("成绩");
            header.add("考试日期");
            objs.add(header);
            //数据
            for(StudentTP tp : list){
                List<String> obj = new ArrayList<String>();
                Student student = tp.getStudent();
                obj.add(student.getId());
                obj.add(student.getName());
                obj.add(student.getClassName());
                obj.add(tp.getTestPaper().getCourse().getName());
                obj.add(tp.getGrade().getGrade().toString());
                obj.add(tp.getStpTime().toString());
                objs.add(obj);
            }

            System.out.println(objs.size());
            //导出Excel文件数据
            ExcelOperating util = new ExcelOperating();
            File file =util.getExcelDemoFile("/ExcelDemoFile/测试模板.xlsx");
            String sheetName="sheet1";
            wb = util.writeNewExcel(file, sheetName, objs);

            String fileName="学生成绩表.xlsx";
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
            os = response.getOutputStream();
            wb.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            os.flush();
            os.close();
            wb.close();
        }
        return null;
    }
/****************************填空题目信息管理***********************************************************************/
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
     * 填空题信息分页页面
     * @param index 页号
     * @param model 模型
     * @return 页面
     */
    @RequestMapping("blankInfoPage/{index}")
    public String blankInfoPage(@PathVariable(value = "index")int index, ModelMap model){
        int blankNum = blankService.recordNum();
        int pageNum = blankNum/pageSize;
        pageNum = blankNum % pageSize == 0 ? pageNum : pageNum+ 1;

        List<Blank> list = blankService.allByTeacherAsc((index-1)*pageSize,pageSize);

        model.addAttribute("blanks", list);
        model.addAttribute("num", pageNum);

        return "TeacherPage/blankInfo";
    }

    /**
     * 填空题更新页面
     * @param uuid 填空题主码
     * @param model 模型
     * @return 页面
     */
    @RequestMapping("blankUpdatePage/{uuid}")
    public String blankUpdate(@PathVariable(value = "uuid")int uuid, ModelMap model){
        Blank blank = blankService.findById(uuid);
        if(blank == null)
            return "redirect:/teacherPage/blankInfoPage/1";
        model.addAttribute("course", courseService.findAll());
        model.addAttribute("blank", blank);
        return "TeacherPage/blankUpdate";
    }

    /**
     * 更新
     * @param blank 填空题试题
     * @param session 全局
     * @return 是否成功更新
     */
    @RequestMapping("blankUpdate")
    @ResponseBody
    public Boolean blankUpdate(@RequestBody Blank blank, HttpSession session){
        Course course = courseService.findById(blank.getCourse().getUuid());
        Teacher teacher = (Teacher) session.getAttribute("currentUser");
        if(course == null || teacher == null)
            return false;
        else{
            blank.setCourse(course);
            blank.setTeacher(teacher);
        }
        if(blankService.allowToSave(blank)){
            blankService.update(blank);
            return true;
        }
        else
            return false;
    }

    /**
     * 填空题详细信息
     * @return 页面
     */
    @RequestMapping("blankMoreInfo/{id}")
    public String blankMoreInfo(@PathVariable(value="id")int id, ModelMap model){
        Blank blank = blankService.getByUuid(id);
        model.addAttribute("blank",blank);
        return "AdminPage/blankMoreInfo";
    }

    /**************************选择题目信息管理***********************************************************************/
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

    /**
     * 选择题分页显示
     * @param index 页号
     * @param model 模型
     * @return 页面
     */
    @RequestMapping("choiceInfoPage/{index}")
    public String choiceInfoPage(@PathVariable(value = "index")int index, ModelMap model){
        int choiceNum = choiceService.recordNum();
        int pageNum = choiceNum/pageSize;
        pageNum = choiceNum % pageSize == 0 ? pageNum : pageNum+ 1;

        List<Choice> list = choiceService.allByTeacherAsc((index-1)*pageSize,pageSize);

        model.addAttribute("choices", list);
        model.addAttribute("num", pageNum);

        return "TeacherPage/choiceInfo";
    }

    /**
     * 选择题更新页面
     * @param uuid 主码
     * @param model 模型
     * @return 页面
     */
    @RequestMapping("choiceUpdatePage/{uuid}")
    public String choiceUpdate(@PathVariable(value = "uuid")int uuid, ModelMap model){
        Choice choice = choiceService.findById(uuid);
        if(choice == null)
            return "redirect:/teacherPage/choiceInfoPage/1";
        model.addAttribute("course", courseService.findAll());
        model.addAttribute("choice", choice);
        return "TeacherPage/choiceUpdate";
    }
    /**
     * 更新选择题
     * @param choice 选择题实例
     * @param session 全局
     * @return 是否成功更新
     */
    @RequestMapping("choiceUpdate")
    @ResponseBody
    public Boolean choiceUpdate(@RequestBody Choice choice, HttpSession session){
        Course course = courseService.findById(choice.getCourse().getUuid());
        Teacher teacher = (Teacher) session.getAttribute("currentUser");
        if(course == null || teacher == null)
            return false;
        else{
            choice.setCourse(course);
            choice.setTeacher(teacher);
        }
        if(choiceService.allowToSave(choice)){
            choiceService.update(choice);
            return true;
        }
        else
            return false;
    }

    /**
     * 选择题详细信息
     * @return 页面
     */
    @RequestMapping("choiceMoreInfo/{id}")
    public String choiceMoreInfo(@PathVariable(value="id")int id, ModelMap model){
        Choice choice = choiceService.getByUuid(id);
        model.addAttribute("choice",choice);
        return "AdminPage/choiceMoreInfo";
    }
    /**************************试卷信息管理***********************************************************************/

    @RequestMapping("testPaperPage")
    public String testPaperPage(ModelMap model){
        List<TestPaper> list = testPaperService.findAll();
        model.addAttribute("testPapers", list);
        return "TeacherPage/testPaperSetting";
    }

    @RequestMapping("testPaperUpdate")
    @ResponseBody
    public Boolean testPaperUpdate(@RequestBody TestPaper testPaper){
        TestPaper tp = testPaperService.getByUuid(testPaper.getUuid());
        tp.setTotalGrade(testPaper.getTotalGrade());
        tp.setTimeLimit(testPaper.getTimeLimit());
        tp.setBlankNum(testPaper.getBlankNum());
        tp.setChoiceNum(testPaper.getChoiceNum());
        testPaperService.update(tp);
        return true;
    }

    /**********************************试题EXCEL上传**************************************************************************/

    @RequestMapping("uploadChooseCourse")
    public String uploadChooseCourse(ModelMap model){
        model.addAttribute("courses", courseService.findAll());
        return "TeacherPage/chooseCourse";
    }

    /**
     * 上传试题页面
     * @return 页面
     */
    @RequestMapping("/uploadExcel/{courseId}")
    public String uploadTest(@PathVariable(value = "courseId") int courseId, ModelMap model){
        model.addAttribute("courseId", courseId);
        return "TeacherPage/upload";
    }

    /**
     * 上传填空题
     * @param request
     * @throws Exception
     */
    @RequestMapping(value="blankUpload",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public  void  blankUploadExcel(HttpServletRequest request, HttpSession session, Integer courseId) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        InputStream in = null;
        List<List<Object>> listob = null;
        MultipartFile file = multipartRequest.getFile("blankFile");
        if(file.isEmpty())
            throw new Exception("文件不存在！");

        in = file.getInputStream();
        listob = new ExcelOperating().getListByExcel(in,file.getOriginalFilename());

        Teacher teacher = (Teacher)session.getAttribute("currentUser");
        Course course = courseService.findById(courseId);
        if(teacher == null || course == null)
            return;

        for (List<Object> lo : listob) {
            Blank blank = new Blank();
            blank.setQuestion(String.valueOf(lo.get(0)));
            blank.setAnswer(String.valueOf(lo.get(1)));
            blank.setAnalyse(String.valueOf(lo.get(2)));
            blank.setCourse(course);
            blank.setTeacher(teacher);
            blankService.saveViaCheck(blank);
        }
    }

    /**
     * 上传选择题
     * @param request
     * @throws Exception
     */
    @RequestMapping(value="choiceUpload",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public  void  choiceUploadExcel(HttpServletRequest request, HttpSession session, Integer courseId) throws Exception {

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        InputStream in = null;
        List<List<Object>> listob = null;
        MultipartFile file = multipartRequest.getFile("choiceFile");
        if(file.isEmpty())
            throw new Exception("文件不存在！");

        in = file.getInputStream();
        listob = new ExcelOperating().getListByExcel(in,file.getOriginalFilename());

        Teacher teacher = (Teacher)session.getAttribute("currentUser");
        Course course = courseService.findById(courseId);
        if(teacher == null || course == null)
            return;
        for (List<Object> lo : listob) {
            Choice choice = new Choice();
            choice.setQuestion(String.valueOf(lo.get(0)));
            choice.setChoiceA(String.valueOf(lo.get(1)));
            choice.setChoiceB(String.valueOf(lo.get(2)));
            choice.setChoiceC(String.valueOf(lo.get(3)));
            choice.setChoiceD(String.valueOf(lo.get(4)));
            choice.setAnswer(String.valueOf(lo.get(5)));
            choice.setAnalyse(String.valueOf(lo.get(6)));
            choice.setCourse(course);
            choice.setTeacher(teacher);
            System.out.println(choiceService.saveViaCheck(choice));
        }
    }
}
