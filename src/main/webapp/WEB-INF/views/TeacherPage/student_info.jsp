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
                    <th>考生登录id</th>
                    <th>考生姓名</th>
                    <th>考生性别</th>
                    <th>身份证号</th>
                    <th>班别</th>
                    <th>考生电话</th>

                </tr>
                </thead>

                <tbody>
                <c:forEach items="${students}" var="u">
                    <tr>
                        <td>${u.id}</td>
                        <td>${u.name}</td>
                        <td>
                            <c:if test="${u.sex == 0}">女</c:if>
                            <c:if test="${u.sex == 1}">男</c:if>
                        </td>
                        <td>${u.card}</td>
                        <td>${u.className}</td>
                        <td>${u.phone}</td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="page_index">
                <ul class="pagination " >
                    <li>
                        <a href="/teaccherPage/studentInfoByPage/1"><<</a>
                    </li>
                    <c:forEach var="i" begin="1" end="${num}" step="1">
                        <li>
                            <a href="/teacherPage/studentInfoByPage/${i}">${i}</a>
                        </li>
                    </c:forEach>
                    <li>
                        <a href="/teacherPage/studentInfoByPage/${num}">>></a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>
