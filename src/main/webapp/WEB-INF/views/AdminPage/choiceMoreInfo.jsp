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
