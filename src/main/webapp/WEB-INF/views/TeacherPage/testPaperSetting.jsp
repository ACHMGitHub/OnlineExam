<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="../../../css/bootstrap.min.css" />
    <link rel="stylesheet" href="../../../css/forPanel.css" />
    <script type="application/javascript" src="../../../js/jquery-1.11.1.min.js"></script>
    <script language="JavaScript" src="../../../js/bootstrap.min.js"></script>
    <style>
        input{border-width: 0;}
    </style>
    <script type="text/javascript">
        function change(inputclass) {
            var input = "." + inputclass + "input";
            var change = "#" + inputclass;
            var buttons = "." + inputclass;
            $(input).removeAttr("readonly");
            $(input).css("border-width","1");
            $(change).hide();
            $(input + " :first").onFocus;
            $(buttons).toggle();
        }
        function cancle(uuid){
            var input = "." + uuid + "input";
            var change = "#" + uuid;
            var buttons = "." + uuid;
            $(input).attr("readonly","readonly");
            $(input).css("border-width","0");
            $(change).toggle();
            $(buttons).toggle();
        }
        function confirm(uuid) {
            var input = "." + uuid + "input";
            var change = "#" + uuid;
            var buttons = "." + uuid;
            $(input).attr("readonly","readonly");
            $(input).css("border-width","0");
            $(change).toggle();
            $(buttons).toggle();

            var param = {
                uuid : uuid,
                timeLimit : $("#"+uuid+"1").val(),
                totalGrade : $("#"+uuid+"2").val(),
                choiceNum : $("#"+uuid+"3").val(),
                blankNum : $("#"+uuid+"4").val(),
            };
            $.ajax({
                type : "post",
                dataType: 'json',
                url : "testPaperUpdate",
                contentType : "application/json",
                data : JSON.stringify(param),
                async:false,
                success:function(data){
                    if(data){
                        alert("修改成功");
                    }
                }
            })
        }
    </script>

</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>课程</th>
                    <th>考试时长(分钟)</th>
                    <th>总分</th>
                    <th>选择题数</th>
                    <th>填空题数</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${testPapers}" var="u">
                    <tr>
                        <td><input type="text" value="${u.course.name}" readonly="readonly" /></td>
                        <td><input id="${u.uuid}1" type="text" value="${u.timeLimit}" readonly="readonly" class="${u.uuid}input"/></td>
                        <td><input id="${u.uuid}2" type="text" value="${u.totalGrade}" readonly="readonly" class="${u.uuid}input"/></td>
                        <td><input id="${u.uuid}3" type="text" value="${u.choiceNum}" readonly="readonly" class="${u.uuid}input"/></td>
                        <td><input id="${u.uuid}4" type="text" value="${u.blankNum}" readonly="readonly" class="${u.uuid}input"/></td>
                        <td>
                            <button id="${u.uuid}" type="button" class="btn btn-success change" onclick="change(${u.uuid})">更改</button></td>
                        <td>
                            <button type="button" class="btn btn-success ${u.uuid}" style="display: none;" onclick="confirm(${u.uuid})">确定</button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>
