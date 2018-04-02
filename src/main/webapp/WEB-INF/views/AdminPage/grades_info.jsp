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
    <script type="application/javascript" src="../../../js/jquery-1.11.1.min.js"></script>

    <script language="JavaScript" src="../../../js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
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
                    <c:forEach items="${grades}" var="u">
                        <tr>
                            <td>${u.stuTestPaper.student.id}</td>
                            <td>${u.stuTestPaper.student.name}</td>
                            <td>${u.stuTestPaper.student.className}</td>
                            <td>${u.stuTestPaper.testPaper.course.name}</td>
                            <td>${u.grade}</td>
                            <td>${u.stuTestPaper.stpTime}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div class="page_index">
                <ul class="pagination " >
                    <li>
                        <a href="/adminPage/gradesInfoByPage/1"><<</a>
                    </li>
                    <c:forEach var="i" begin="1" end="${num}" step="1">
                        <li>
                            <a href="/adminPage/gradesInfoByPage/${i}">${i}</a>
                        </li>
                    </c:forEach>
                    <li>
                        <a href="/adminPage/gradesInfoByPage/${num}">>></a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>
