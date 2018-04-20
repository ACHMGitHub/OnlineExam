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
                    <th>课程号</th>
                    <th>课程名称</th>
                    <th colspan="2" style="text-align:center;">操作</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${courses}" var="u">
                    <tr>
                        <td>${u.uuid}</td>
                        <td>${u.name}</td>
                        <td><a href="/adminPage/courseDelete/${u.uuid}">删除</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="page_index">
                <ul class="pagination " >
                    <li>
                        <a href="/adminPage/courseInfoByPage/1"><<</a>
                    </li>
                    <c:forEach var="i" begin="1" end="${num}" step="1">
                        <li>
                            <a href="/adminPage/courseInfoByPage/${i}">${i}</a>
                        </li>
                    </c:forEach>
                    <li>
                        <a href="/adminPage/courseInfoByPage/${num}">>></a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>
