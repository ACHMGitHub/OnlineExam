<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <script type="text/javascript" src="../../js/jquery-1.11.1.js"></script>
    <script type="text/javascript" src="../../js/jquery.form.js"></script>
    <title>My JSP 'index.jsp' starting page</title>

    <script type="text/javascript">
        //ajax 方式上传文件操作  要引入jquery.form.js
        //要在jquery之后引入
        $(document).ready(function(){
            $('#btn').click(function(){
                if(checkData()){
                    $('#form1').ajaxSubmit({
                        url:'/ajaxUpload',
                        dataType: 'text',
                        success: resutlMsg,
                        error: errorMsg
                    });
                    function resutlMsg(msg){
                        alert("成功导入Excel");
                        $("#upfile").val("");
                    }
                    function errorMsg(){
                        alert("导入excel出错！");
                    }
                }
            });
        });

        //JS校验form表单信息
        function checkData(){
            var fileDir = $("#upfile").val();
            var suffix = fileDir.substr(fileDir.lastIndexOf("."));
            if("" == fileDir){
                alert("选择需要导入的Excel文件！");
                return false;
            }
            if(".xls" != suffix && ".xlsx" != suffix ){
                alert("选择Excel格式的文件导入！");
                return false;
            }
            return true;
        }
    </script>
</head>

<body>
    <form method="POST" enctype="multipart/form-data" id="form1" action="">
        <table>
            <tr>
                <td>上传文件: </td>
                <td> <input id="upfile" type="file" name="upfile"></td>
            </tr>
            <tr>
                <td><input type="submit" value="提交" onclick="return checkData()"></td>
                <td><input type="button" value="ajax方式提交" id="btn" name="btn" ></td>
            </tr>
        </table>
    </form>
<div>
    文件以模板的方式导出，模板存放在项目中(WEB-INF/ExcelDemoFile/)
</div><br>
    <form action="ajaxExport" method="post" id="form2">
        <input type="submit" id="exportExcel" name="exportExcel" value="Excel导出"/>
    </form>
</body>
</html>  