<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>系统管理界面</title>
    <link rel="stylesheet" href="../../../css/bootstrap.min.css" />

    <link rel="stylesheet" href="../../../css/adminFrame.css" />

    <link rel="stylesheet" href="../../../css/background.css" />

    <script type="application/javascript" src="../../../js/jquery-1.11.1.min.js"></script>

    <script language="JavaScript" src="../../../js/bootstrap.min.js"></script>

    <script type="text/javascript">
        window.onload=function(){
            document.body.parentNode.style.overflow="hidden";//隐藏且禁用
        };
    </script>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">导航条</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">网上考试系统</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/studentPage/studentUpdatePage" target="iframe_a"><span class="glyphicon glyphicon-user"></span>&nbsp;修改信息</a></li>
                <li><a href="/studentPage/studentLogOut"><span class="glyphicon glyphicon-off"></span>&nbsp;注销</a></li>
            </ul>
            <form class="navbar-form navbar-right">
                <input type="text" class="form-control" placeholder="搜索">
            </form>
        </div>
    </div>
</nav>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">

            <div class="panel-group" id="panelcontainer">

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a href="#"  data-toggle="collapse" data-target="#channel_demo4">成绩管理</a>
                        </h4>
                    </div>
                    <div class="collapse panel-collapse"  id="channel_demo4">
                        <div class="panel-body">
                            <ul>
                                <li><a href="/studentPage/studentSearchBar" target="iframe_a">成绩信息</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a href="#"  data-toggle="collapse" data-target="#channel_demo5">考试</a>
                        </h4>
                    </div>
                    <div class="collapse panel-collapse"  id="channel_demo5">
                        <div class="panel-body">
                            <ul>
                                <li><a href="/studentPage/studentExamPage">进入考试</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
<div class="main" style="background: #000000;">
    <iframe src="/studentPage/homePage" id="engine" name="iframe_a" frameborder="0" marginwidth="0" marginheight="0" allowtransparency="true" style="background-color:#fff"></iframe>
    <%--<a href="/studentPage/homePage" target="iframe_a" style="display: none" id="home" />--%>
</div>
</body>
</html>
