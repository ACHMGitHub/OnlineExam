package com.onlineExam.controller;

import com.onlineExam.ImportExcelUtil;
import com.onlineExam.entity.Blank;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class MainController {


    @RequestMapping(value = "/studentLogin", method = RequestMethod.GET)
    public String stuLoginPage(){
        return "LoginPage/studentLogin";
    }

    @RequestMapping(value = "/teacherLogin", method = RequestMethod.GET)
    public String tchLoginPage(){
        return "LoginPage/teacherLogin";
    }

    @RequestMapping(value = "/adminLogin", method = RequestMethod.GET)
    public String adminLoginPage(){
        return "LoginPage/adminLogin";
    }

    @RequestMapping("/uploadTest")
    public String uploadTest(){
        return "uploadExcel";
    }


    /**
     * 描述：通过传统方式form表单提交方式导入excel文件
     * @param request
     * @throws Exception
     */
    @RequestMapping(value="uploadExcel",method={RequestMethod.GET,RequestMethod.POST})
    public  String  uploadExcel(HttpServletRequest request) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        System.out.println("通过传统方式form表单提交方式导入excel文件！");

        InputStream in =null;
        List<List<Object>> listob = null;
        MultipartFile file = multipartRequest.getFile("upfile");
        if(file.isEmpty()){
            throw new Exception("文件不存在！");
        }
        in = file.getInputStream();
        listob = new ImportExcelUtil().getBankListByExcel(in,file.getOriginalFilename());
        in.close();

        //该处可调用service相应方法进行数据保存到数据库中，现只对数据输出
        for (int i = 0; i < listob.size(); i++) {
            List<Object> lo = listob.get(i);
            Blank blank = new Blank();
            blank.setQuestion(String.valueOf(lo.get(0)));
            blank.setAnalyse(String.valueOf(lo.get(1)));
            blank.setAnalyse(String.valueOf(lo.get(2)));

            System.out.println(blank.getQuestion());
            System.out.println(blank.getAnswer());
            System.out.println(blank.getAnalyse());
        }
        return "uploadExcel";
    }

    /**
     * 描述：通过 jquery.form.js 插件提供的ajax方式上传文件
     * @param request
     * @param response
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value="ajaxUpload",method={RequestMethod.GET,RequestMethod.POST})
    public  void  ajaxUploadExcel(HttpServletRequest request,HttpServletResponse response) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        System.out.println("通过 jquery.form.js 提供的ajax方式上传文件！");

        InputStream in = null;
        List<List<Object>> listob = null;
        MultipartFile file = multipartRequest.getFile("upfile");
        if(file.isEmpty())
            throw new Exception("文件不存在！");

        in = file.getInputStream();
        listob = new ImportExcelUtil().getBankListByExcel(in,file.getOriginalFilename());

        for (int i = 0; i < listob.size(); i++) {
            List<Object> lo = listob.get(i);
            Blank blank = new Blank();
            blank.setQuestion(String.valueOf(lo.get(0)));
            blank.setAnswer(String.valueOf(lo.get(1)));
            blank.setAnalyse(String.valueOf(lo.get(2)));
        }
    }

}
