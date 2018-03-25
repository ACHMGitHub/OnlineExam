<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>网上考试系统登录</title>

    <link rel="stylesheet" href="../../css/bootstrap.css" />
    <link href="../../css/sign.css" rel="stylesheet">
    <script type="application/javascript" src="../../js/jquery-1.11.1.min.js"></script>
    <script language="JavaScript" src="../../js/bootstrap.min.js"></script>

</head>
<body>
<div class="container" >

    <form class="form-signin" action="">
        <h2 class="form-signin-heading">请登录</h2>
        <input type="text" id="userId" class="form-control" placeholder="用户ID" required autofocus>
        <input type="password" id="inputPassword" class="form-control" placeholder="用户口令" required>
        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> 记住我
            </label>
        </div>
        <div class="dropdown">
            <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                --用户类型--
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                <li><a href="#">学生</a></li>
                <li><a href="#">老师</a></li>
                <li><a href="#">管理员</a></li>
            </ul>
        </div>
        <button class="btn btn-lg btn-success btn-block" type="submit">登录</button>
    </form>

</div>
</body>
</html>
