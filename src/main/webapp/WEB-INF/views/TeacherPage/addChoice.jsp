<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加选择题</title>
    <link rel="stylesheet" href="../../../css/bootstrap.min.css" />

    <link rel="stylesheet" href="../../../css/addChangeCss.css"/>

    <style>
        .form-group{
            height: 40px;
        }
    </style>

    <script type="application/javascript" src="../../../js/jquery-1.11.1.min.js"></script>

    <script language="JavaScript" src="../../../js/bootstrap.min.js"></script>

    <script language="JavaScript" src="../../../js/jquery.validate.min.js"></script>

    <script language="JavaScript" src="../../../js/additional-methods.js"></script>

    <script language="JavaScript" src="../../../js/messages_zh.js"></script>

    <script>
        $(doc)
        $.validator.setDefaults({
            submitHandler: function() {
                var param = {
                    question : $("#question").val(),
                    choiceA : $("#ch_a").val().trim(),
                    choiceB : $("#ch_b").val().trim(),
                    choiceC : $("#ch_c").val().trim(),
                    choiceD : $("#ch_d").val().trim(),
                    answer : $("input[name='answer']:checked").val(),
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
                        url : "/teacherPage/addChoice",
                        contentType : "application/json",
                        data : JSON.stringify(param),
                        async:false,
                        success:function(data){
                            if(data){
                                $("#question").val("");
                                $("#ch_a").val("");
                                $("#ch_b").val("");
                                $("#ch_c").val("");
                                $("#ch_d").val("");
                                $("input:radio[name='answer']").attr("checked",false);
                                $("#analyse").val("");
                                $("#courseId").val("");
                                alert("添加成功");
                            }
                        }
                    })
                }
            }
        });
        $().ready(function() {
            // 在键盘按下并释放及提交后验证提交表单
            $("#addChioceForm").validate({
                rules: {
                    question: {
                        required: true,
                        maxlength: 255
                    },
                    ch_a:{
                        required: true,
                        maxlength: 255
                    },
                    ch_c:{
                        required: true,
                        maxlength: 255
                    },
                    ch_b:{
                        required: true,
                        maxlength: 255
                    },
                    ch_d:{
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
                    ch_a:{
                        required: "请输入",
                        maxlength: "请输入少于255个字符"
                    },
                    ch_b:{
                        required: "请输入",
                        maxlength: "请输入少于255个字符"
                    },
                    ch_c:{
                        required: "请输入",
                        maxlength: "请输入少于255个字符"
                    },
                    ch_d:{
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
                errorPlacement: function (error, element) { //指定错误信息位置
                    if (element.is(':radio') || element.is(':checkbox')) { //如果是radio或checkbox
                        var eid = element.attr('name'); //获取元素的name属性
                        error.appendTo(element.parent()); //将错误信息添加当前元素的父结点后面
                    } else {
                        error.insertAfter(element);
                    }
                }
            });
        });
    </script>
</head>
<body style="padding: 0px;">
<form class="form" role="form" id="addChioceForm">
    <div class="form-group" style="padding-bottom: 50px;">
        <label for="question" class="col-sm-2 control-label">题干</label>
        <div class="col-sm-10">
            <textarea class="form-control" rows="3" name="question" id="question"></textarea>
        </div>
    </div>
    <div class="form-group">
        <label for="ch_a" class="col-sm-2 control-label">选项A</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="ch_a" name="ch_a">
        </div>
    </div>

    <div class="form-group">
        <label for="ch_b" class="col-sm-2 control-label">选项B</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="ch_b" name="ch_b">
        </div>
    </div>


    <div class="form-group">
        <label for="ch_c" class="col-sm-2 control-label">选项C</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="ch_c" name="ch_c">
        </div>
    </div>


    <div class="form-group">
        <label for="ch_d" class="col-sm-2 control-label">选项D</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="ch_d" name="ch_d">
        </div>
    </div>


    <div class="form-group">
        <label class="col-sm-2 control-label">答案</label>
        <div class="col-sm-10">
            <!--<input type="text" class="form-control" id="phoneNum" >-->
            <input type="radio" name="answer" id="optionsRadiosa" value="A">A&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="radio" name="answer" id="optionsRadiosb" value="B">B&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="radio" name="answer" id="optionsRadiosc" value="C">C&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="radio" name="answer" id="optionsRadiosd" value="D">D&nbsp;&nbsp;&nbsp;&nbsp;
        </div>
    </div>

    <div class="form-group" style="padding-bottom: 50px;">
        <label class="col-sm-2 control-label">解析</label>
        <div class="col-sm-10">
            <textarea class="form-control" rows="3" name="analyse" id="analyse"></textarea>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-2 control-label">课程</label>
        <div class="col-sm-10">
            <select id="courseId" name="courseId">
                <option value="" selected="selected">请选择</option>
                <c:forEach items="${course}" var="u">
                    <option value="${u.uuid}">${u.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-2 control-label"></label>
        <div class="col-sm-10">
            <button type="submit" class="btn btn-success btn-block">添加</button>
        </div>
    </div>
</form>
</body>
</html>
