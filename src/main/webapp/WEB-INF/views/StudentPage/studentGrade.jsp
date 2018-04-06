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
    <link rel="stylesheet" href="../../../css/dcalendar.picker.css"/>

    <style type="text/css">
        input{
            width: 105px;
        }
    </style>
    <script type="application/javascript" src="../../../js/jquery-1.11.1.min.js"></script>
    <script language="JavaScript" src="../../../js/bootstrap.min.js"></script>
</head>
<body>
<div class="frameContent">
    <table class="table table-hover">
        <thead>
        <tr>
            <th>学生学号</th>
            <th>学生姓名</th>
            <th>班级</th>
            <th>课程</th>
            <th>成绩</th>
            <th>考试日期</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${studentTPs}" var="u">
                <tr>
                    <td>${u.student.id}</td>
                    <td>${u.student.name}</td>
                    <td>${u.student.className}</td>
                    <td>${u.testPaper.course.name}</td>
                    <td>${u.grade.grade}</td>
                    <td>${u.stpTime}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="page_index">
        <ul class="pagination " >
            <li><a href="/studentPage/studentTPSearch/1?after=${after}&before=${before}&min=${min}&max=${max}"><<</a></li>

            <c:forEach var="i" begin="1" end="${num}" step="1">
                <li><a href="/studentPage/studentTPSearch/${i}?after=${after}&before=${before}&min=${min}&max=${max}">${i}</a></li>
            </c:forEach>

            <li><a href="/studentPage/studentTPSearch/${num}?after=${after}&before=${before}&min=${min}&max=${max}">>></a></li>
        </ul>
    </div>
</div>
</body>
</html>
