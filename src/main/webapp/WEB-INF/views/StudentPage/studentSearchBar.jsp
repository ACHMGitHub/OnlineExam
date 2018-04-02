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

    <script type="application/javascript" src="../../../js/jquery-1.11.1.min.js"></script>

    <script language="JavaScript" src="../../../js/bootstrap.min.js"></script>

    <script type="text/javascript" src="../../../js/dcalendar.picker.js"></script>

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
                <form action="/studentPage/studentTPSearch/1" target="info">
                    <label>起始时间：</label><input id='mydatepicker1' type='text' name="after"/>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <label>结束时间：</label><input id='mydatepicker2' type='text' name="before"/>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <label>成绩区间：</label><input id='grade1' class="grade" type='text' onchange="check();" name="min"/>~<input id='grade2' class="grade" type='text' onchange="check2();" name="max"/>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <button type="submit" class="btn btn-sm btn-success">查询</button>
                </form>
            </div>
            <iframe src="/studentPage/studentTPs/1" frameborder="0" class="col-md-12 column" name="info"></iframe>
        </div>
    </div>
</div>
</body>
</html>
