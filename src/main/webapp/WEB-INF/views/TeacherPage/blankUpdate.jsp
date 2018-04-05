<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>修改填空题</title>
    <link rel="stylesheet" href="../../../css/bootstrap.min.css" />
    <link rel="stylesheet" href="../../../css/test.css" />
    <link rel="stylesheet" href="../../../css/addChangeCss.css"/>
    <link rel="stylesheet" href="../../../css/examMain.css"/>
    <script type="application/javascript" src="../../../js/jquery-1.11.1.min.js"></script>
    <script language="JavaScript" src="../../../js/bootstrap.min.js"></script>
    <script language="JavaScript" src="../../../js/jquery.validate.min.js"></script>

    <script language="JavaScript" src="../../../js/additional-methods.js"></script>

    <script language="JavaScript" src="../../../js/messages_zh.js"></script>
    <script>
        $.validator.setDefaults({
            submitHandler: function() {
                var param = {
                    uuid : ${blank.uuid},
                    question : $("#question").val(),
                    answer : $("#answer").val().trim(),
                    analyse : $("#analyse").val(),
                    course : {
                        uuid :  $("#courseId").val()
                    }
                };
                add(param);
                function add(param){
                    $.ajax({
                        type : "post",
                        dataType: 'json',
                        url : "/teacherPage/blankUpdate",
                        contentType : "application/json",
                        data : JSON.stringify(param),
                        async:false,
                        success:function(data){
                            if(data){
                                alert("修改成功");
                            }
                        }
                    })
                }
            }
        });
        $().ready(function() {
            // 在键盘按下并释放及提交后验证提交表单
            $("#completForm").validate({
                rules: {
                    question: {
                        required: true,
                        maxlength: 255
                    },
                    answer:{
                        required: true,
                        maxlength: 255
                    },
                    analyse:{
                        required: true,
                        maxlength: 255
                    },
                    courseId:"required"
                },
                messages: {
                    question:{
                        required: "请输入",
                        maxlength: "请输入少于255个字符"
                    },
                    answer:{
                        required: "请输入",
                        maxlength: "请输入少于255个字符"
                    },
                    analyse:{
                        required: "请输入",
                        maxlength: "请输入少于255个字符"
                    },
                    courseId:"请选择"
                },
            });
        });

    </script>

</head>
<body>

<form class="form" role="form" id="completForm">


    <div class="form-group" style="padding-bottom: 50px;">
        <label  class="col-sm-2 control-label">题干</label>
        <div class="col-sm-10">
            <textarea class="form-control" rows="3" name="question" id="question">${blank.question}</textarea>
        </div>
    </div>
    <div class="form-group">
        <label for="answer" class="col-sm-2 control-label">答案</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="answer" name="answer" value="${blank.answer}">
        </div>
    </div>

    <div class="form-group" style="padding-bottom: 50px;">
        <label for="analyse" class="col-sm-2 control-label">解析</label>
        <div class="col-sm-10">
            <textarea class="form-control" rows="3" name="analyse" id="analyse">${blank.analyse}</textarea>
        </div>
    </div>
    <div class="form-group" style="padding-bottom: 50px;">
        <label for="analyse" class="col-sm-2 control-label">课程</label>
        <div class="col-sm-10">
            <select id="courseId" name="courseId">
                <c:forEach items="${course}" var="u">
                    <option value="${u.uuid}" <c:if test="${blank.course.uuid == u.uuid}">selected="selected"</c:if> >${u.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label"></label>
        <div class="col-sm-10">
            <button type="submit" class="btn btn-success btn-block">修改</button>
        </div>
    </div>
</form>
</body>
</html>
