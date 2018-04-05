<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="../../../css/bootstrap.min.css" />
    <link rel="stylesheet" href="../../../css/forPanel.css" />
    <script type="application/javascript" src="../../../js/jquery-1.11.1.js"></script>

    <script language="JavaScript" src="../../../js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>试卷编号</th>
                    <th>课程</th>
                    <th>考试时长(分钟)</th>
                    <th>总分</th>
                    <th>选择题数</th>
                    <th>填空题数</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${testPapers}" var="u">
                    <tr>
                        <td>${u.uuid}</td>
                        <td>${u.course.name}</td>
                        <td>${u.timeLimit}</td>
                        <td>${u.choiceNum}</td>
                        <td>${u.blankNum}</td>
                        <td>${u.blankNum}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
