<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="../../../css/bootstrap.min.css" />

    <link rel="stylesheet" href="../../../css/examMain.css"/>

    <script type="application/javascript" src="../../../js/jquery-1.11.1.min.js"></script>

    <script language="JavaScript" src="../../../js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h3 style="text-align: center ; background: #EEEEEE;height: 45px;line-height: 45px;">
        你好 ${sessionScope.currentUser.name}, 请选择考试科目
    </h3>
    <div class="iconCon">
        <div class="row placeholders">
            <c:forEach items="${courses}" var="u" >
                <div class="col-xs-12 col-sm-3 placeholder">
                    <a href="/studentPage/exam/${u.uuid}" target="_blank">
                        <img <c:if test="${u.uuid % 4 == 0}">src="../../../img/icons_01.gif"</c:if>
                             <c:if test="${u.uuid % 4 == 1}">src="../../../img/icons_02.gif"</c:if>
                             <c:if test="${u.uuid % 4 == 2}">src="../../../img/icons_03.jpg"</c:if>
                             <c:if test="${u.uuid % 4 == 3}">src="../../../img/icons_04.jpg"</c:if>
                             width="200" height="200" class="img-responsive img-circle" alt="Generic placeholder thumbnail">
                        <h4 class="text-center">${u.name}</h4>
                    </a>
                </div>
            </c:forEach>
        </div>
    </div>

    <div class="col-md-12 column">
        <div class="jumbotron">
            <div class="place">
                <h3>欢迎使用网上考试系统</h3>
                <p style="font-size:16px;">
                    请考生严格准守考场纪律，有下列行为之一的，认定为考试违纪：<br>
                    （一）携带规定以外的物品进入考场或者未放在指定位置的；<br>
                    （二）未在规定的座位参加考试的；<br>
                    （三）考试开始信号发出前答题或者考试结束信号发出后继续答题的；<br>
                    （四）在考试过程中旁窥、交头接耳、互打暗号或者手势的；<br>
                    （五）在考场或者教育考试机构禁止的范围内，喧哗、吸烟或者实施其他影响考场秩序的行为的；<br>
                    （六）未经考试工作人员同意在考试过程中擅自离开考场的；<br>
                    （七）将试卷、答卷（含答题卡、答题纸等，下同）、草稿纸等考试用纸带出考场的；<br>
                    （八）用规定以外的笔或者纸答题或者在试卷规定以外的地方书写姓名、考号或者以其他方式在答卷上标记信息的；<br>
                    （九）其他违反考场规则但尚未构成作弊的行为。<br>
                </p>
                <%--<p><a class="btn btn-success btn-large" href="#">更多>></a></p>--%>
            </div>
        </div>
    </div>

    <div>
        <table  align="center" class="heise12x"  border="0" cellspacing="0" cellpadding="0">
            <tbody>
            <tr>
                <td height="85" align="center"><p><br>Copyright © 2018 by&nbsp;</p>&nbsp;</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</div>
</body>
</html>
