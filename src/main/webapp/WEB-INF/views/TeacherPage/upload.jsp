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
            $('#blankButton').click(function(){
                if(checkData($('#blankFile'))){
                    $('#formBlank').ajaxSubmit({
                        url:'/teacherPage/blankUpload',
                        dataType: 'text',
                        success: function(){
                            alert("成功导入Excel");
                            $('#blankFile').val("");
                        },
                        error: errorMsg
                    });
                }
            });
            $('#choiceButton').click(function(){
                if(checkData($('#choiceFile'))){
                    $('#formChoice').ajaxSubmit({
                        url:'/teacherPage/choiceUpload',
                        dataType: 'text',
                        success: function(){
                            alert("成功导入Excel");
                            $('#choiceFile').val("");
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

        <form method="POST" enctype="multipart/form-data" id="formBlank" action="">
            <input type="text" hidden value="${courseId}" name="courseId">
            <div class="form-group childElement">
                <label for="blankFile">选择文件</label>
                <input type="file" id="blankFile" name="blankFile">
                <p class="help-block">Example block-level help text here.</p>
                <button type="button" class="btn btn-success" id="blankButton">填空题上传</button>
            </div>
        </form>
        <form method="POST" enctype="multipart/form-data" id="formChoice" action="">
            <input type="text" hidden value="${courseId}" name="courseId">
            <div class="form-group childElement">
                <label for="choiceFile">选择文件</label>
                <input type="file" id="choiceFile" name="choiceFile">
                <p class="help-block">Example block-level help text here.</p>
                <button type="button" class="btn btn-success" id="choiceButton">选择题上传</button>
            </div>
        </form>
    </div>
</body>
</html>
