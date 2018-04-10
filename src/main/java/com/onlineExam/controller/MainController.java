package com.onlineExam.controller;

import com.onlineExam.ExcelOperating;
import com.onlineExam.entity.Blank;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
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
        listob = new ExcelOperating().getListByExcel(in,file.getOriginalFilename());

        for (int i = 0; i < listob.size(); i++) {
            List<Object> lo = listob.get(i);
            Blank blank = new Blank();
            blank.setQuestion(String.valueOf(lo.get(0)));
            blank.setAnswer(String.valueOf(lo.get(1)));
            blank.setAnalyse(String.valueOf(lo.get(2)));
        }
    }
    /**
     * 描述：导出excel文件
     * @param response
     * @throws Exception
     */
    @RequestMapping(value="ajaxExport",method={RequestMethod.GET,RequestMethod.POST})
    public  String  ajaxExportExcel(HttpServletResponse response) throws Exception {
        System.out.println("通过 jquery.form.js 提供的ajax方式导出文件！");
        OutputStream os = null;
        Workbook wb = null;    //工作薄

        try {
            //模拟数据库取值
            List<List<String>> objs = new ArrayList<List<String>>();
            List<String> header = new ArrayList<String>();
            //标题
            header.add("标题1");
            header.add("标题2");
            header.add("标题3");
            header.add("标题4");
            header.add("标题5");
            objs.add(header);
            //数据
            for (int i = 0; i < 8; i++) {
                List<String> obj = new ArrayList<String>();
                obj.add("v1");
                obj.add("v2");
                obj.add("v3");
                obj.add("v4");
                obj.add("v5");
                objs.add(obj);
            }
            System.out.println(objs.size());
            //导出Excel文件数据
            ExcelOperating util = new ExcelOperating();
            File file =util.getExcelDemoFile("/ExcelDemoFile/测试模板.xlsx");
            String sheetName="sheet1";
            wb = util.writeNewExcel(file, sheetName, objs);

            String fileName="机构码.xlsx";
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
}
