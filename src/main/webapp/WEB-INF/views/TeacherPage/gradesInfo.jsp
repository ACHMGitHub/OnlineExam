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
    <link rel="stylesheet" href="../../../css/dcalendar.picker.css"/>
    <script type="application/javascript" src="../../../js/jquery-1.11.1.min.js"></script>
    <script language="JavaScript" src="../../../js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../../../js/dcalendar.picker.js"></script>
    <style type="text/css">
        input{
            width: 150px;
        }
        iframe{
            height: 500px;
        }
        #formDiv{
            padding:0 80px 0 80px;
        }

    </style>
    <script type="text/javascript">
        $(function(){
            $('#mydatepicker2').dcalendarpicker({
                format:'yyyy-mm-dd'
            });
            $('#mydatepicker1').dcalendarpicker({
                format:'yyyy-mm-dd'
            });
        });
        function check(){
            var grade=parseFloat( $('#grade1').val());
            grade>=0&&grade<=100?$('#grade1').css({"border-color":"","border-style":"","border-size":""}):$('#grade1').css({"border-color":"red","border-style":"solid","border-size":"1px"});
        }
        function check2(){
            var grade=parseFloat( $('#grade2').val());
            grade>=0&&grade<=100?$('#grade2').css({"border-color":"","border-style":"","border-size":""}):$('#grade2').css({"border-color":"red","border-style":"solid","border-size":"1px"});
        }
    </script>
</head>
<body>
<div class="container">

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div id="formDiv">
                <form action="/teacherPage/gradesInfoSearch/1">
                    <label>起始时间：</label><input id='mydatepicker1' type='text' name="after" value="${after}"/>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <label>结束时间：</label><input id='mydatepicker2' type='text' name="before" value="${before}"/>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <label>成绩区间：</label>
                        <input id='grade1' class="grade" type='text' onchange="check();" name="min" value="${min}"/>~
                        <input id='grade2' class="grade" type='text' onchange="check2();" name="max" value="${max}"/>&nbsp;&nbsp;&nbsp;&nbsp;
                    <br>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <label>学号：</label><input id='stuId' class="grade" type='text' name="stuId" value="${stuId}"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <label>班级：</label><input id='className' class="grade" type='text' name="className" value="${className}"/>&nbsp;&nbsp;&nbsp;&nbsp;
                    <button type="submit" class="btn btn-sm btn-success">查询</button>
                </form>
            </div>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>学生学号</th>
                    <th>学生姓名</th>
                    <th>班级</th>
                    <th>课程</th>
                    <th>成绩</th>
                    <th>考试日期</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${studentTPs}" var="u">
                    <tr>
                        <td>${u.student.id}</td>
                        <td>${u.student.name}</td>
                        <td>${u.student.className}</td>
                        <td>${u.testPaper.course.name}</td>
                        <td>${u.grade.grade}</td>
                        <td>${u.stpTime}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="page_index">
                <ul class="pagination " >
                    <li>
                        <a href="/teacherPage/gradesInfoSearch/1?after=${after}&before=${before}&min=${min}&max=${max}&stuId=${stuId}&className=${className}"><<</a>
                    </li>
                    <c:forEach var="i" begin="1" end="${num}" step="1">
                        <li>
                            <a href="/teacherPage/gradesInfoSearch/${i}?after=${after}&before=${before}&min=${min}&max=${max}&stuId=${stuId}&className=${className}">${i}</a>
                        </li>
                    </c:forEach>
                    <li>
                        <a href="/teacherPage/gradesInfoSearch/${num}?after=${after}&before=${before}&min=${min}&max=${max}&stuId=${stuId}&className=${className}">>></a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>
