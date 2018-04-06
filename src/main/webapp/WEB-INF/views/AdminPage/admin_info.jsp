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
                    <th>管理员账号</th>
                    <th>姓名</th>
                    <th>密码</th>
                    <th>性别</th>
                    <th>电话</th>
                    <th>身份证号</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${admins}" var="u">
                    <tr>
                        <td>${u.id}</td>
                        <td>${u.name}</td>
                        <td>${u.pw}</td>
                        <td>
                            <c:if test="${u.sex == 0}">女</c:if>
                            <c:if test="${u.sex == 1}">男</c:if>
                        </td>
                        <td>${u.phone}</td>
                        <td>${u.card}</td>
                        <%--<td><a href="#">修改</a></td>--%>
                        <%--<td><a href="/adminPage/adminDelete/${u.id}">删除</a></td>--%>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="page_index">
                <ul class="pagination" >
                    <li>
                        <a href="/adminPage/adminInfoByPage/1"><<</a>
                    </li>
                    <c:forEach var="i" begin="1" end="${num}" step="1">
                    <li>
                        <a href="/adminPage/adminInfoByPage/${i}">${i}</a>
                    </li>
                    </c:forEach>
                    <li>
                        <a href="/adminPage/adminInfoByPage/${num}">>></a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>

