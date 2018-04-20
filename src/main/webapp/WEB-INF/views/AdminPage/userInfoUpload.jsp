<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="../../../css/bootstrap.min.css" />
    <script type="text/javascript" src="../../../js/jquery-1.11.1.js"></script>
    <script type="text/javascript" src="../../../js/jquery.form.js"></script>
    <style>
        .parentElement{
            display: flex;
            justify-content:center;
            height: 200px;
        }
        .childElement{
            position: relative;
            top: 50%;
        }
    </style>

    <script type="text/javascript">
        //ajax 方式上传文件操作  要引入jquery.form.js
        //要在jquery之后引入
        $(document).ready(function(){
            function resultMsg(fileInput){
                fileInput.val("");
            }
            function errorMsg(){
                alert("导入excel出错！");
            }
            $('#studentButton').click(function(){
                if(checkData($('#stuFile'))){
                    $('#formStudent').ajaxSubmit({
                        url:'/adminPage/stuUpload',
                        dataType: 'text',
                        success: function(){
                            alert("成功导入Excel");
                            $('#stuFile').val("");
                        },
                        error: errorMsg
                    });
                }
            });
            $('#teacherButton').click(function(){
                if(checkData($('#tchFile'))){
                    $('#formTeacher').ajaxSubmit({
                        url:'/adminPage/tchUpload',
                        dataType: 'text',
                        success: function(){
                            alert("成功导入Excel");
                            $('#tchFile').val("");
                        },
                        error: errorMsg
                    });
                }
            });
            $('#adminButton').click(function(){
                if(checkData($('#adminFile'))){
                    $('#formAdmin').ajaxSubmit({
                        url:'/adminPage/adminUpload',
                        dataType: 'text',
                        success: function(){
                            alert("成功导入Excel");
                            $('#adminFile').val("");
                        },
                        error: errorMsg
                    });
                }
            });
        });

        //JS校验form表单信息
        function checkData(fileInput){
            var fileDir = fileInput.val();
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
<div class="parentElement">

    <form method="POST" enctype="multipart/form-data" id="formStudent" action="">
        <div class="form-group childElement">
            <label for="stuFile">选择文件</label>
            <input type="file" id="stuFile" name="stuFile">
            <p class="help-block">Example block-level help text here.</p>
            <button type="button" class="btn btn-success" id="studentButton">学生信息导入</button>
        </div>
    </form>
    <form method="POST" enctype="multipart/form-data" id="formTeacher" action="">
        <div class="form-group childElement">
            <label for="tchFile">选择文件</label>
            <input type="file" id="tchFile" name="tchFile">
            <p class="help-block">Example block-level help text here.</p>
            <button type="button" class="btn btn-success" id="teacherButton">教师信息导入</button>
        </div>
    </form>
    <form method="POST" enctype="multipart/form-data" id="formAdmin" action="">
        <div class="form-group childElement">
            <label for="adminFile">选择文件</label>
            <input type="file" id="adminFile" name="adminFile">
            <p class="help-block">Example block-level help text here.</p>
            <button type="button" class="btn btn-success" id="adminButton">管理员信息导入</button>
        </div>
    </form>
</div>
</body>
</html>
