<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>网上考试系统登录</title>
    <link rel="stylesheet" href="../../../css/bootstrap.min.css" />

    <link href="../../../css/sign.css" rel="stylesheet">
    <style>

        body{
            background:url(../../../img/background.png);
            -moz-background-size:100% 100%; /* 老版本的 Firefox */
            background-size:100% 100%;
            background-repeat:no-repeat;

        }
        .form-signin{
            margin:100px auto;
        }
    </style>

    <script type="application/javascript" src="../../../js/jquery-1.11.1.min.js"></script>

    <script language="JavaScript" src="../../../js/bootstrap.min.js"></script>

    <script type="text/javascript">
        window.onload=function(){
            document.body.parentNode.style.overflow="hidden";//隐藏且禁用
        }
    </script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#login").click(function () {
                var param = { id : $("#userName").val(), pw : $("#passWord").val()};
                login(param);
            });
            function login(param){
                $.ajax({
                    type : "post",
                    dataType: 'json',
                    url : "/user/adminLogin",
                    contentType : "application/json",
                    data : JSON.stringify(param),
                    async:false,
                    error:function(data){
                        alert("Error");
                    },
                    success:function(data){
                        if(data){
                            location.href = "/adminPage/adminHome";
                        }
                        else{
                            alert("账号或密码错误");
                            $("#passWord").val("");
                        }
                    }
                })
            }
        })
    </script>
</head>
<body>
<div class="container">

    <form class="form-signin">
        <h2 class="form-signin-heading">管理员登录</h2>

        <input type="text" id="userName" name="userName" class="form-control" placeholder="用户ID" required autofocus>
        <input type="password" id="passWord" name="passWord" class="form-control" placeholder="用户口令" required>

        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> 记住我
            </label>
        </div>

        <button class="btn btn-lg btn-success btn-block" type="button" id="login">登录</button>

        <%--<div class="pos">--%>
            <%--<a href="#">教师</a>--%>
            <%--&nbsp;&nbsp;&nbsp;&nbsp;--%>
            <%--<a href="#" >管理员</a>--%>
        <%--</div>--%>

    </form>
</div>
</body>
</html>
