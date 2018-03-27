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
                    <th colspan="2" style="text-align:center;">操作</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${admins}" var="u">
                    <tr>
                        <td>${u.id}</td>
                        <td>${u.name}</td>
                        <td>${u.pw}</td>
                        <td>${u.sex}</td>
                        <td>${u.phone}</td>
                        <td>${u.card}</td>
                        <td><a href="#">修改</a></td>
                        <td><a href="/user/managerDelete/${u.id}">删除</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="page_index">
                <ul class="pagination " >
                    <li>
                        <a href="#"><<</a>
                    </li>
                    <li>
                        <a href="#">1</a>
                    </li>
                    <li>
                        <a href="#">2</a>
                    </li>
                    <li>
                        <a href="#">3</a>
                    </li>
                    <li>
                        <a href="#">4</a>
                    </li>
                    <li>
                        <a href="#">5</a>
                    </li>
                    <li>
                        <a href="#">>></a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>

