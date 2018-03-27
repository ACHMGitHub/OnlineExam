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
        }
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
                <li><a href="#"><span class="glyphicon glyphicon-user"></span>&nbsp;修改密码</a></li>
                <li><a href="#"><span class="glyphicon glyphicon-off"></span>&nbsp;注销</a></li>
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
                <!--一个功能 -->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a href="#"  data-toggle="collapse" data-target="#channel_demo1"  data-parent="#panelcontainer">学生管理</a>
                        </h4>
                    </div>

                    <div class=" collapse panel-collapse"  id="channel_demo1">
                        <div class="panel-body">
                            <ul>
                                <li><a href="/adminLogin" target="iframe_a">学生信息</a></li>
                                <li><a href="../03studentJSP/stu_add.html" target="iframe_a">添加学生</a></li>
                            </ul>
                        </div>
                    </div>
                </div>


                <!--一个功能 -->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a href="#"  data-toggle="collapse" data-target="#channel_demo2">教师管理</a>
                        </h4>
                    </div>

                    <div class=" collapse panel-collapse"  id="channel_demo2">
                        <div class="panel-body">
                            <ul >
                                <li><a href="../04teacherJSP/tch_info.html" target="iframe_a">教师信息</a></li>
                                <li><a href="../04teacherJSP/tch_add.html" target="iframe_a">添加教师</a></li>

                            </ul>
                        </div>
                    </div>
                </div>



                <!--一个功能 -->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a href="#"  data-toggle="collapse" data-target="#channel_demo3">管理员管理</a>
                        </h4>
                    </div>

                    <div class="collapse panel-collapse"  id="channel_demo3">
                        <div class="panel-body">
                            <ul>
                                <li><a href="/user/adminInfo" target="iframe_a">管理员信息</a></li>
                                <li><a href="../05administratorJSP/admin_add.html " target="iframe_a">添加管理员</a></li>

                            </ul>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a href="#"  data-toggle="collapse" data-target="#channel_demo4">成绩管理</a>
                        </h4>
                    </div>
                    <div class="collapse panel-collapse"  id="channel_demo4">
                        <div class="panel-body">
                            <ul>
                                <li><a href="../01adminJSP/grade_info.html" target="iframe_a">成绩信息</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a href="#"  data-toggle="collapse" data-target="#channel_demo5">题目管理</a>
                        </h4>
                    </div>
                    <div class="collapse panel-collapse"  id="channel_demo5">
                        <div class="panel-body">
                            <ul>
                                <li><a href="../01adminJSP/examQue.html" target="iframe_a">题目答案</a></li>
                                <li><a href="../06testPaper/choiceQue_add.html" target="iframe_a">添加选择题</a></li>
                                <li><a href="../06testPaper/Complet_add.html" target="iframe_a">添加填空题</a></li>
                                <li><a href="../06testPaper/testPaper_info.html" target="iframe_a">试卷信息</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</div>
<div class="main" style="background: #000000;">
    <iframe src="../home.html" id="engine" name="iframe_a" frameborder="0" marginwidth="0" marginheight="0" allowtransparency="true" style="background-color:#fff"></iframe>
</div>
<div class="navbar-fixed-bottom">
    <iframe src="../07background/footer.html" id="engine1" name="engine" frameborder="0" marginwidth="0" marginheight="0" allowtransparency="true" style="background-color:#000"></iframe>
</div>
</body>
</html>

