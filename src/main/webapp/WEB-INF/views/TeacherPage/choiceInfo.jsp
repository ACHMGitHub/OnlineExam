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
                    <th>题目</th>
                    <th>选项A</th>
                    <th>选项B</th>
                    <th>选项C</th>
                    <th>选项D</th>
                    <th>答案</th>
                    <th>分析</th>
                    <th>课程</th>
                    <th>出题教师</th>
                    <th>详细</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${choices}" var="u">
                    <tr>
                        <td>
                            <c:if test="${u.question.length() > 8}">${fn:substring(u.question, 0, 8)}... ...</c:if>
                            <c:if test="${u.question.length() <= 8}">${u.question}</c:if>
                        </td>
                        <td>
                            <c:if test="${u.choiceA.length() > 8}">${fn:substring(u.choiceA, 0, 8)}... ...</c:if>
                            <c:if test="${u.choiceA.length() <= 8}">${u.choiceA}</c:if>
                        </td>
                        <td>
                            <c:if test="${u.choiceB.length() > 8}">${fn:substring(u.choiceB, 0, 8)}... ...</c:if>
                            <c:if test="${u.choiceB.length() <= 8}">${u.choiceB}</c:if>
                        </td>
                        <td>
                            <c:if test="${u.choiceC.length() > 8}">${fn:substring(u.choiceC, 0, 8)}... ...</c:if>
                            <c:if test="${u.choiceC.length() <= 8}">${u.choiceC}</c:if>
                        </td>
                        <td>
                            <c:if test="${u.choiceD.length() > 8}">${fn:substring(u.choiceD, 0, 8)}... ...</c:if>
                            <c:if test="${u.choiceD.length() <= 8}">${u.choiceD}</c:if>
                        </td>
                        <td>
                            <c:if test="${u.answer.length() > 8}">${fn:substring(u.answer, 0, 8)}... ...</c:if>
                            <c:if test="${u.answer.length() <= 8}">${u.answer}</c:if>
                        </td>
                        <td>
                            <c:if test="${u.analyse.length() > 5}">${fn:substring(u.analyse, 0, 5)}... ...</c:if>
                            <c:if test="${u.analyse.length() <= 5}">${u.analyse}</c:if>
                        </td>
                        <td>${u.course.name}</td>
                        <td>${u.teacher.name}</td>
                        <td>
                            <c:choose>
                                <c:when test="${u.teacher.uuid == sessionScope.currentUser.uuid}">
                                    <a href="/teacherPage/choiceUpdatePage/${u.uuid}">修改</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="/teacherPage/choiceMoreInfo/${u.uuid}">详细</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="page_index">
                <ul class="pagination " >
                    <li>
                        <a href="/teacherPage/choiceInfoPage/1"><<</a>
                    </li>
                    <c:forEach var="i" begin="1" end="${num}" step="1">
                        <li>
                            <a href="/teacherPage/choiceInfoPage/${i}">${i}</a>
                        </li>
                    </c:forEach>
                    <li>
                        <a href="/teacherPage/choiceInfoPage/${num}">>></a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>
