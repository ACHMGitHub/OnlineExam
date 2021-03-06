<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加填空题</title>
    <link rel="stylesheet" href="../../../css/bootstrap.min.css" />
    <link rel="stylesheet" href="../../../css/test.css" />
    <link rel="stylesheet" href="../../../css/addChangeCss.css"/>
    <link rel="stylesheet" href="../../../css/examMain.css"/>
    <script type="application/javascript" src="../../../js/jquery-1.11.1.min.js"></script>
    <script language="JavaScript" src="../../../js/bootstrap.min.js"></script>
    <script language="JavaScript" src="../../../js/jquery.validate.min.js"></script>

    <script language="JavaScript" src="../../../js/additional-methods.js"></script>

    <script language="JavaScript" src="../../../js/messages_zh.js"></script>

</head>
<body>

<form class="form" role="form" id="completForm">
    <div class="form-group" style="padding-bottom: 50px;">
        <label  class="col-sm-2 control-label">题干</label>
        <div class="col-sm-10">
            <textarea class="form-control" rows="5" name="title" disabled>${blank.question}</textarea>
        </div>
    </div>

    <div class="form-group">
        <label for="phoneNum" class="col-sm-2 control-label">答案</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="phoneNum" name="answer" value="${blank.answer}" disabled>
        </div>
    </div>

    <div class="form-group" style="padding-bottom: 50px;">
        <label for="phoneNum" class="col-sm-2 control-label">解析</label>
        <div class="col-sm-10">
            <textarea class="form-control" rows="5" name="analyse" disabled>${blank.analyse}</textarea>
        </div>
    </div>

</form>
</body>
</html>
