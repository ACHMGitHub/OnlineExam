package com.onlineExam.controller;

import com.onlineExam.CourseAdd;
import com.onlineExam.ExcelOperating;
import com.onlineExam.SearchCondition;
import com.onlineExam.entity.*;
import com.onlineExam.service.Admin.IAdminService;
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
    @Autowired
    IGradesService gradesService;
    @Autowired
    IStudentTPService studentTPService;
    @Autowired
    ITestPaperService testPaperService;

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

        return "AdminPage/student_info";
    }
    /**
     * 学生删除操作
     * @param id 学生主码
     * @return 页面
     */
    @RequestMapping("studentDelete/{id}")
    public String studentDelete(@PathVariable(value="id")String id){
        studentService.delete(id);
        return "redirect:/adminPage/studentInfoByPage/1";
    }
    /**
     * 添加学生页面
     * @return 页面
     */
    @RequestMapping("studentAddPage")
    public String studentAdd(){
        return "AdminPage/studentAdd";
    }
    /**
     * 验证学生用户名唯一性
     * @param id 用户名
     * @return  是否唯一
     */
    @RequestMapping("studentIdCheck")
    @ResponseBody
    public Boolean studentIdCheck(@RequestBody String id){
        id = id.substring(3);
        return studentService.idUnique(id);
    }
    /**
     * 添加学生操作
     * @param student 学生实体
     * @return 是否成功添加
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
     * @param id 页号
     * @param model 模型
     * @return 页面
     */
    @RequestMapping("studentUpdatePage/{id}")
    public String studentUpdatePage(@PathVariable(value = "id") int id, ModelMap model){
        Student student = studentService.getByUuid(id);
        model.addAttribute("student", student);
        return "AdminPage/studentUpdate";
    }

    /**
     * 学生修改操作
     * @param student 学生实体
     * @return 页面
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
     * @param model 模型
     * @return 页面
     */
    @RequestMapping("teacherInfoByPage/{index}")
    public String teacherInfo(@PathVariable(value="index")int index, ModelMap model){

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
     * @return 页面
     */
    @RequestMapping("teacherDelete/{id}")
    public String teacherDelete(@PathVariable(value="id")String id){
        teacherService.delete(id);
        return "redirect:/adminPage/teacherInfoByPage/1";
    }
    /**
     * 教师添加页面
     * @return 页面
     */
    @RequestMapping("teacherAddPage")
    public String teacherAdd(){
        return "AdminPage/teacherAdd";
    }
    /**
     * 验证教师用户名唯一性
     * @param id 用户名
     * @return 是否唯一
     */
    @RequestMapping("teacherIdCheck")
    @ResponseBody
    public Boolean teacherIdCheck(@RequestBody String id){
        id = id.substring(3);
        return teacherService.idUnique(id);
    }
    /**
     * 教师添加操作
     * @param teacher 教师实体
     * @return 是否成功添加
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
     * @param id 页号
     * @param model 模型
     * @return 页面
     */
    @RequestMapping("teacherUpdatePage/{id}")
    public String teacherUpdatePage(@PathVariable(value = "id") int id, ModelMap model){
        Teacher teacher = teacherService.getByUuid(id);
        model.addAttribute("teacher", teacher);
        return "AdminPage/teacherUpdate";
    }

    /**
     * 教师修改操作
     * @param teacher 教师实体
     * @return 是否成功更新
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
     * @param index 页号
     * @param model 模型
     * @return 页面
     */
    @RequestMapping("courseInfoByPage/{index}")
    public String courseInfoByPage(@PathVariable(value="index")int index, ModelMap model){
//        int courseNum = courseService.recordNum();
//        int pageNum = courseNum/pageSize;
//        pageNum = courseNum % pageSize == 0 ? pageNum : pageNum + 1;
//
//        List<Course> list = courseService.findAllByPage((index-1)*pageSize,pageSize);
//
//        model.addAttribute("courses", list);
//        model.addAttribute("num", pageNum);

        int testPaperNum = testPaperService.recordNum();
        int pageNum = testPaperNum/pageSize;
        pageNum = testPaperNum % pageSize == 0 ? pageNum : pageNum + 1;

        List<TestPaper> list = testPaperService.findAllByPage((index-1)*pageSize,pageSize);

        model.addAttribute("testPapers", list);
        model.addAttribute("num", pageNum);
        return "AdminPage/course_info";
    }

    @RequestMapping("courseAddPage")
    public String courseAddPage(){
        return "AdminPage/newCourse";
    }

    @RequestMapping("courseAdd")
    @ResponseBody
    public Boolean courseAdd(@RequestBody CourseAdd courseAdd){
        Course course = new Course();
        course.setName(courseAdd.getName());
        course.setUuid((Integer) courseService.save(course));
        TestPaper testPaper = new TestPaper();
        testPaper.setCourse(course);
        testPaper.setChoiceNum(courseAdd.getChoiceNum());
        testPaper.setBlankNum(courseAdd.getBlankNum());
        testPaper.setTotalGrade(courseAdd.getTotalGrade());
        testPaper.setTimeLimit(courseAdd.getTimeLimit());
        List<TestPaper> testPapers = new ArrayList<TestPaper>();
        testPapers.add(testPaper);
        testPaperService.save(testPaper);
        return true;
    }

    /**
     * 删除操作
     * @param id 课程主码
     * @return 页面
     */
    @RequestMapping("courseDelete/{id}")
    public String courseDelete(@PathVariable(value="id")int id){
        courseService.delete(id);
        return "redirect:/adminPage/courseInfoByPage/1";
    }
    /******************************填空题信息管理****************************************************************/
    /**
     * 填空题分页显示
     * @param index 页号
     * @param model 模型
     * @return 页面
     */
    @RequestMapping("blankInfoByPage/{index}")
    public String blankInfoByPage(@PathVariable(value="index")int index, ModelMap model){
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
     * @return 页面
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
     * @param index 页号
     * @param model 模型
     * @return 页面
     */
    @RequestMapping("choiceInfoByPage/{index}")
    public String choiceInfoByPage(@PathVariable(value="index")int index, ModelMap model){
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
     * @return 页面
     */
    @RequestMapping("choiceMoreInfo/{id}")
    public String choiceMoreInfo(@PathVariable(value="id")int id, ModelMap model){
        Choice choice = choiceService.getByUuid(id);
        model.addAttribute("choice",choice);
        return "AdminPage/choiceMoreInfo";
    }
    /******************************成绩信息管理****************************************************************/
    /**
     * 成绩分页显示页面
     * @param index 页号
     * @param model 模型
     * @return 页面
     */
    @RequestMapping("gradesInfoByPage/{index}")
    public String gradesInfoByPage(@PathVariable(value="index")int index, ModelMap model){
        int gradesNum = gradesService.recordNum();
        int pageNum = gradesNum/pageSize;
        pageNum = gradesNum % pageSize == 0 ? pageNum : pageNum+ 1;

        List<Grades> list = gradesService.findAllByPage((index-1)*pageSize,pageSize);

        model.addAttribute("grades", list);
        model.addAttribute("num", pageNum);
        return "AdminPage/grades_info";
    }

    @RequestMapping("gradesInfoSearch/{index}")
    public String gradesInfoSearch(SearchCondition condition, @PathVariable(value = "index")int index, ModelMap model){

        //保留查询条件
        model.addAttribute("condition", condition);

        //时间检验
        Timestamp aTime, bTime;
        String after = condition.getAfter();
        String before = condition.getBefore();
        Integer min = condition.getMin();
        Integer max = condition.getMax();
        String stuId = condition.getStuId();
        String className = condition.getClassName();

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
        return "AdminPage/gradesInfo";
    }

    /**
     * 描述：导出excel文件
     * @param response
     * @throws Exception
     */
    @RequestMapping(value="gradeExport",method={RequestMethod.GET,RequestMethod.POST})
    public String gradeExportExcel(SearchCondition condition, HttpServletResponse response) throws Exception {

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

    /******************************用户信息导入****************************************************************/

    /**
     * 上传信息页面
     * @return 页面
     */
    @RequestMapping("/upload")
    public String uploadTest(ModelMap model){
        return "AdminPage/userInfoUpload";
    }

    /**
     * 上传
     * @param request
     * @throws Exception
     */
    @RequestMapping(value="stuUpload",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public void stuUploadExcel(HttpServletRequest request, HttpSession session) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        InputStream in = null;
        List<List<Object>> listob = null;
        MultipartFile file = multipartRequest.getFile("stuFile");
        if(file.isEmpty())
            throw new Exception("文件不存在！");

        in = file.getInputStream();
        listob = new ExcelOperating().getListByExcel(in,file.getOriginalFilename());

        for (List<Object> lo : listob) {
            Student stu = new Student();
            stu.setId(String.valueOf(lo.get(0)));
            if(!studentService.idUnique(stu.getId()))
                continue;
            stu.setPw(String.valueOf(lo.get(1)));
            stu.setName(String.valueOf(lo.get(2)));
            int sex = String.valueOf(lo.get(3)).equals("男") ? 1 : 0;
            stu.setSex(sex);
            stu.setPhone(String.valueOf(lo.get(4)));
            stu.setCard(String.valueOf(lo.get(5)));
            stu.setClassName(String.valueOf(lo.get(6)));
            studentService.saveViaCheck(stu);
        }
    }

    /**
     * 上传
     * @param request
     * @throws Exception
     */
    @RequestMapping(value="adminUpload",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public void adminUploadExcel(HttpServletRequest request, HttpSession session) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        InputStream in = null;
        List<List<Object>> listob = null;
        MultipartFile file = multipartRequest.getFile("adminFile");
        if(file.isEmpty())
            throw new Exception("文件不存在！");

        in = file.getInputStream();
        listob = new ExcelOperating().getListByExcel(in,file.getOriginalFilename());

        for (List<Object> lo : listob) {
            Admin user = new Admin();
            user.setId(String.valueOf(lo.get(0)));
            if(!adminService.idUnique(user.getId()))
                continue;
            user.setPw(String.valueOf(lo.get(1)));
            user.setName(String.valueOf(lo.get(2)));
            int sex = String.valueOf(lo.get(3)).equals("男") ? 1 : 0;
            user.setSex(sex);
            user.setPhone(String.valueOf(lo.get(4)));
            user.setCard(String.valueOf(lo.get(5)));
            adminService.saveViaCheck(user);
        }
    }

    /**
     * 上传
     * @param request
     * @throws Exception
     */
    @RequestMapping(value="tchUpload",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public void tchUploadExcel(HttpServletRequest request, HttpSession session) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        InputStream in = null;
        List<List<Object>> listob = null;
        MultipartFile file = multipartRequest.getFile("tchFile");
        if(file.isEmpty())
            throw new Exception("文件不存在！");

        in = file.getInputStream();
        listob = new ExcelOperating().getListByExcel(in,file.getOriginalFilename());

        for (List<Object> lo : listob) {
            Teacher user = new Teacher();
            user.setId(String.valueOf(lo.get(0)));
            if(!teacherService.idUnique(user.getId()))
                continue;
            user.setPw(String.valueOf(lo.get(1)));
            user.setName(String.valueOf(lo.get(2)));
            int sex = String.valueOf(lo.get(3)).equals("男") ? 1 : 0;
            user.setSex(sex);
            user.setPhone(String.valueOf(lo.get(4)));
            user.setCard(String.valueOf(lo.get(5)));
            user.setTitle(String.valueOf(lo.get(6)));
            teacherService.saveViaCheck(user);
        }
    }
}
