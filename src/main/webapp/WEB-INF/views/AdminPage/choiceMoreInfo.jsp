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
            height: 60px;
        }
    </style>

    <script type="application/javascript" src="../../../js/jquery-1.11.1.min.js"></script>

    <script language="JavaScript" src="../../../js/bootstrap.min.js"></script>

    <script language="JavaScript" src="../../../js/jquery.validate.min.js"></script>

    <script language="JavaScript" src="../../../js/additional-methods.js"></script>

    <script language="JavaScript" src="../../../js/messages_zh.js"></script>

    <%--<script>--%>

        <%--$.validator.setDefaults({--%>
            <%--submitHandler: function() {--%>
                <%--alert("提交事件!");--%>
            <%--}--%>
        <%--});--%>
        <%--$().ready(function() {--%>
<%--// 在键盘按下并释放及提交后验证提交表单--%>
            <%--$("#addChioceForm").validate({--%>
                <%--rules: {--%>
                    <%--ch_question:"required",--%>
                    <%--ch_a:"required",--%>
                    <%--ch_c:"required",--%>
                    <%--ch_b:"required",--%>
                    <%--ch_d:"required",--%>
                    <%--ch_answer:"required",--%>
                    <%--ch_analyse:"required",--%>
                    <%--username: {--%>
                        <%--required: true,--%>
                        <%--minlength: 2--%>
                    <%--},--%>
                    <%--gridRadios:{--%>
                        <%--required:true,--%>
                    <%--},--%>

                <%--},--%>
                <%--messages: {--%>
                    <%--ch_question:"请输入",--%>
                    <%--ch_a:"请输入",--%>
                    <%--ch_b:"请输入",--%>
                    <%--ch_c:"请输入",--%>
                    <%--ch_d:"请输入",--%>
                    <%--ch_answer:"请输入",--%>
                    <%--ch_analyse:"请输入",--%>
                    <%--username: {--%>
                        <%--required: "请输入用户名",--%>
                        <%--minlength: "用户名必需由两个字母组成"--%>
                    <%--},--%>

                    <%--gridRadios:{--%>
                        <%--required:"请选择",--%>
                    <%--},--%>

                <%--},--%>

                <%--errorPlacement: function (error, element) { //指定错误信息位置--%>
                    <%--if (element.is(':radio') || element.is(':checkbox')) { //如果是radio或checkbox--%>
                        <%--var eid = element.attr('name'); //获取元素的name属性--%>
                        <%--error.appendTo(element.parent()); //将错误信息添加当前元素的父结点后面--%>
                    <%--} else {--%>
                        <%--error.insertAfter(element);--%>
                    <%--}--%>
                <%--}--%>

            <%--});--%>
        <%--});--%>

    <%--</script>--%>




</head>
<body style="padding: 0px;">
<form class="form" role="form" id="addChioceForm">
    <div class="form-group" style="padding-bottom: 50px;">
        <label for="ch_question" class="col-sm-2 control-label">题干</label>
        <div class="col-sm-10">
            <textarea class="form-control" rows="3" name="ch_question" id="ch_question" disabled>${choice.question}</textarea>
        </div>
    </div>

    <div class="form-group">
        <label for="ch_a" class="col-sm-2 control-label">选项A</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="ch_a" name="ch_a" value="${choice.choiceA}" disabled>
        </div>
    </div>

    <div class="form-group">
        <label for="ch_b" class="col-sm-2 control-label">选项B</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="ch_b" name="ch_b" value="${choice.choiceB}" disabled>
        </div>
    </div>


    <div class="form-group">
        <label for="ch_c" class="col-sm-2 control-label">选项C</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="ch_c" name="ch_c" value="${choice.choiceC}" disabled>
        </div>
    </div>


    <div class="form-group">
        <label for="ch_d" class="col-sm-2 control-label">选项D</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="ch_d" name="ch_d" value="${choice.choiceD}" disabled>
        </div>
    </div>


    <div class="form-group">
        <label class="col-sm-2 control-label">答案</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="ch_answer" name="ch_d" value="${choice.answer}" disabled>
        </div>
    </div>


    <div class="form-group" style="padding-bottom: 50px;">
        <label for="ch_analyse" class="col-sm-2 control-label">解析</label>
        <div class="col-sm-10">
            <textarea class="form-control" rows="5" name="ch_analyse" id="ch_analyse" disabled>${choice.analyse}</textarea>
        </div>
    </div>
</form>
</body>
</html>
