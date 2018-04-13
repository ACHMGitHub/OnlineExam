package com.onlineExam;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelOperating {

    private final static String excel2003L =".xls";    //2003- 版本的excel
    private final static String excel2007U =".xlsx";   //2007+ 版本的excel

    /**
     * 描述：获取IO流中的数据，组装成List<List<Object>>对象
     * @param in,fileName
     * @return
     * @throws IOException
     */
    public  List<List<Object>> getListByExcel(InputStream in, String fileName) throws Exception{
        List<List<Object>> list = null;

        //创建Excel工作薄
        Workbook work = this.getWorkbook(in,fileName);
        if(null == work){
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;

        list = new ArrayList<List<Object>>();
        //遍历Excel中所有的sheet
        for (int i = 0; i < work.getNumberOfSheets(); i++) {

            sheet = work.getSheetAt(i);
            if(sheet==null){continue;}

            //遍历当前sheet中的所有行
            for (int j = sheet.getFirstRowNum() + 1; j <= sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                if(row==null)
                    continue;

                //遍历所有的列
                List<Object> li = new ArrayList<Object>();
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    li.add(this.getCellValue(cell));
                }
                list.add(li);
            }
        }
        work.close();
        return list;
    }

    /**
     * 描述：根据文件后缀，自适应上传文件的版本
     * @param inStr,fileName
     * @return
     * @throws Exception
     */
    public Workbook getWorkbook(InputStream inStr,String fileName) throws Exception{

        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));

        if(excel2003L.equals(fileType)){
            wb = new HSSFWorkbook(inStr);  //2003-
        }
        else if(excel2007U.equals(fileType)){
            wb = new XSSFWorkbook(inStr);  //2007+
        }
        else{
            throw new Exception("解析的文件格式有误！");
        }
        return wb;
    }

    /**
     * 描述：对表格中数值进行格式化
     * @param cell
     * @return
     */
    public  Object getCellValue(Cell cell){
        Object value = null;
        DecimalFormat df = new DecimalFormat("0");  //格式化number String字符
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");  //日期格式化
        DecimalFormat df2 = new DecimalFormat("0.00");  //格式化数字

        System.out.println(cell);
        if(cell == null)
            return null;

        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if("General".equals(cell.getCellStyle().getDataFormatString())){
                    value = df.format(cell.getNumericCellValue());
                }
                else if("m/d/yy".equals(cell.getCellStyle().getDataFormatString())){
                    value = sdf.format(cell.getDateCellValue());
                }
                else{
                    value = df2.format(cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case Cell.CELL_TYPE_BLANK:
                value = "";
                break;
            default:
                break;
        }
        return value;
    }


    /**
     * 描述：根据文件路径获取项目中的文件
     * @param fileDir 文件路径
     * @return 文件
     * @throws Exception 异常
     */
    public File getExcelDemoFile(String fileDir) throws Exception{
        String classDir = null;
        String fileBaseDir = null;
        File file = null;
        classDir = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
        fileBaseDir = classDir.substring(0, classDir.lastIndexOf("classes"));

        file = new File(fileBaseDir+fileDir);
        if(!file.exists()){
            throw new Exception("模板文件不存在！");
        }
        return file;
    }

    public  Workbook writeNewExcel(File file,String sheetName, List< List<String> > lis) throws Exception{
        Workbook wb = null;
        Row row = null;
        Cell cell = null;

        FileInputStream fis = new FileInputStream(file);
        wb = this.getWorkbook(fis, file.getName());    //获取工作薄
        Sheet sheet = wb.getSheet(sheetName);

        //循环插入数据
        int lastRow = sheet.getLastRowNum();    //插入数据的数据ROW
        CellStyle cs = setSimpleCellStyle(wb);    //Excel单元格样式
        int j;
        for (List<String> strs : lis) {
            row = sheet.createRow(lastRow++); //创建新的ROW，用于数据插入
            //Cell赋值开始
            j = 0;
            for (String str : strs) {
                cell = row.createCell(j++);      //创建新的Cell，用于数据插入
                cell.setCellValue(str);
                cell.setCellStyle(cs);
            }
        }
        return wb;
    }

    /**
     * 描述：设置简单的Cell样式
     * @return
     */
    public CellStyle setSimpleCellStyle(Workbook wb){

        CellStyle cs = wb.createCellStyle();
        cs.setBorderBottom(BorderStyle.THIN); //下边框
        cs.setBorderLeft(BorderStyle.THIN);//左边框
        cs.setBorderTop(BorderStyle.THIN);//上边框
        cs.setBorderRight(BorderStyle.THIN);//右边框
        cs.setAlignment(HorizontalAlignment.CENTER); // 居中

        return cs;
    }


}