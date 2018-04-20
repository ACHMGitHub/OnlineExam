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
        请选择课程。
    </h3>
    <div class="iconCon">
        <div class="row placeholders">
            <c:forEach items="${courses}" var="u" >
                <div class="col-xs-12 col-sm-3 placeholder">
                    <a href="/teacherPage/uploadExcel/${u.uuid}">
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

</div>
</body>
</html>
