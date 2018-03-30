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
                    <th>编号</th>
                    <th>题目</th>
                    <th>答案</th>
                    <th>分析</th>
                    <th>出题教师</th>
                    <th>详细</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${blanks}" var="u">
                    <tr>
                        <td>${u.uuid}</td>
                        <td>
                            <c:if test="${u.question.length() > 15}">${fn:substring(u.question, 0, 15)}... ...</c:if>
                            <c:if test="${u.question.length() <= 15}">${u.question}</c:if>
                        </td>
                        <td>
                            <c:if test="${u.answer.length() > 15}">${fn:substring(u.answer, 0, 15)}... ...</c:if>
                            <c:if test="${u.answer.length() <= 15}">${u.answer}</c:if>
                        </td>
                        <td>
                            <c:if test="${u.analyse.length() > 15}">${fn:substring(u.analyse, 0, 15)}... ...</c:if>
                            <c:if test="${u.analyse.length() <= 15}">${u.analyse}</c:if>
                        </td>
                        <td>${u.teacher.name}</td>
                        <td><a href="/adminPage/blankMoreInfo/${u.uuid}">详细</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="page_index">
                <ul class="pagination " >
                    <li>
                        <a href="/adminPage/blankInfoByPage/1"><<</a>
                    </li>
                    <c:forEach var="i" begin="1" end="${num}" step="1">
                        <li>
                            <a href="/adminPage/blankInfoByPage/${i}">${i}</a>
                        </li>
                    </c:forEach>
                    <li>
                        <a href="/adminPage/blankInfoByPage/${num}">>></a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>
