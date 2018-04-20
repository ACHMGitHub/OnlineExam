<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="../../../css/bootstrap.min.css" />

    <link rel="stylesheet" href="../../../css/addChangeCss.css"/>

    <script type="application/javascript" src="../../../js/jquery-1.11.1.min.js"></script>

    <script language="JavaScript" src="../../../js/bootstrap.min.js"></script>

    <script language="JavaScript" src="../../../js/jquery.validate.min.js"></script>

    <script language="JavaScript" src="../../../js/additional-methods.js"></script>

    <script>
        $(document).ready(function () {
            $("#addButton").click(function () {
                var param = {
                    name : $("#name").val(),
                    timeLimit : $("#timeLimit").val(),
                    blankNum : $("#blankNum").val(),
                    choiceNum : $("#choiceNum").val(),
                    totalGrade : $("#totalGrade").val()
                };
                $.ajax({
                    type : "post",
                    dataType: 'json',
                    url : "courseAdd",
                    contentType : "application/json",
                    data : JSON.stringify(param),
                    async:false,
                    success:function(data){
                        if(data){
                            $("#name").val("");
                            $("#timeLimit").val();
                            $("#blankNum").val();
                            $("#choiceNum").val();
                            $("#totalGrade").val();
                            alert("添加成功");
                        }
                    }
                })
            })
        })
    </script>

    <script type="text/javascript">

    </script>

</head>
<body>
<form class="form" role="form" id="adminForm">

    <div class="form-group">
        <label class="col-sm-2 control-label">课程名</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="name" name="name" placeholder="请输入课程名">
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-2 control-label">考试时长</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="timeLimit" name="timeLimit" placeholder="请输入考试时长（分钟）">
        </div>
    </div>

    <div class="form-group">
        <label  class="col-sm-2 control-label">填空题数</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="blankNum" name="blankNum" placeholder="请输入数目">
        </div>
    </div>

    <div class="form-group">
        <label  class="col-sm-2 control-label">选择题数</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" name="choiceNum" id="choiceNum" placeholder="请输入数目">
        </div>
    </div>

    <div class="form-group">
        <label  class="col-sm-2 control-label">总分</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="totalGrade" name="totalGrade" placeholder="请输入试卷总分">
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-success btn-block" id="addButton">添加</button>
        </div>
    </div>
</form>

</body>
</html>
