<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="../../../css/bootstrap.min.css" />

    <link rel="stylesheet" href="../../../css/adminFrame.css" />

    <link rel="stylesheet" href="../../../css/test.css" />

    <style>
        .testFrame{
            width:80%;
            margin:0 auto;
        }
        .question{
            margin:40px 0 40px 0;
        }
        .layer{
            padding:15px 30px ;
        }
    </style>

    <script type="application/javascript" src="../../../js/jquery-1.11.1.min.js"></script>

    <script language="JavaScript" src="../../../js/bootstrap.min.js"></script>

    <script type="text/javascript">
        window.onload=function(){
            document.body.parentNode.style.overflow="hidden";//隐藏且禁用
        }

        function changeFrameHeight(){
            var ifm= document.getElementById("mainiframe");
            ifm.height=document.documentElement.clientHeight-56;
        }
        $(function(){
            window.onresize=function(){
            changeFrameHeight();
            }
        });
        window.onload=function(){
            daojishi();
            document.onkeydown=function(){
                var e=window.event||arguments[0];
                if(e.keyCode==123){
                    return false;
                }else if((e.ctrlKey)&&(e.shiftKey)&&(e.keyCode==73)){
                    return false;
                }
                else if((e.ctrlKey)&&(e.keyCode==85)){
                    return false;
                }
            };
            document.oncontextmenu=function(){
                return false;
            }
        };

        var counttime = ${testPaper.timeLimit}*60;//总秒钟
        var t;

        function daojishi(){

            if(counttime>=0){
                var ms = counttime%60;//余数 89%60==29秒
                var mis = Math.floor(counttime/60);//分钟

                if(mis>=60){
                    var hour=Math.floor(mis/60);
                    mis=Math.floor((counttime-hour*60*60)/60);
                    document.getElementById("mss").innerHTML=hour+"小时"+mis+"分"+ms+"秒";
                }
                else if(mis>=1){
                    document.getElementById("mss").innerHTML=mis+"分"+ms+"秒";
                }
                else{
                    document.getElementById("mss").innerHTML=ms+"秒";
                }
                counttime--;
                t =  window.setTimeout("daojishi()",1000);
            }else{
                window.clearTimeout(t);
                tijiao();
            }
        }

        function tijiao(){
            var gradePerQ = ${testPaper.totalGrade} / (${choices.size()}+${blanks.size()});
            var grade = 0;
            alert("您的得分为：" + grade);
            for(var i=1; i<=${choices.size()}; i++){
                var id = "input[name='c" + i + "']:checked";
                var a = $(id).val();
                var idspan = "#ca" + i;
                var an = $(idspan).text().trim();
                if(an == a){
                    grade += gradePerQ;
                }
            }
            for(var j=1; j<=${blanks.size()}; j++){
                id = "#b" + j;
                var ba = $(id).val().trim();
                idspan = "#ba" + j;
                var ban = $(idspan).text().trim();
                if(ban == ba){
                    grade += gradePerQ;
                }
            }
            submitGrade(grade);
            alert("您的得分为：" + grade);
        }

        function submitGrade(grade){
            var param = {id : ${testPaper.uuid}, grade : grade };
            $.ajax({
                type : "post",
                dataType: 'json',
                url : "/studentPage/addGrade",
                contentType : "application/json",
                data : JSON.stringify(param),
                async:false,
                success:function(data){
                    if(!data){
                        alert("出错了");
                    }
                }
            })
        }

        $(document).ready(function(){
            $("#submit").click(function(){
                tijiao();
                $(".answer").css("display","block");
                $("#submit").css("display","none");
                window.clearTimeout(t);
            });
        })

    </script>
</head>
<body>
    <nav class="navbar navbar-default navbar-fixed-top">
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
                <a href="#" class="btn btn-success" style="float: right" id="submit">提交</a>
                <ul class="nav navbar-nav navbar-right">
                    <div align="center" class="layer">剩余时间：<label id="mss"></label> </div>
                </ul>
            </div>
        </div>
    </nav>
    <div class="testFrame">
        <div>
            <h4>一、选择题</h4>
            <c:forEach items="${choices}" var="u" varStatus="i">
            <div class="question">
                <div class="panels">
                    <h4>${u.question}</h4>
                </div>
                <div>
                    <div class="radio">
                        <div>
                            <label>
                                <input type="radio" name="c${i.count}" id="optionsRadios1" value="A">
                                A、<span>${u.choiceA}</span>
                            </label>
                        </div>
                        <div>
                            <label>
                                <input type="radio" name="c${i.count}" id="optionsRadios2" value="B">
                                B、<span>${u.choiceB}</span>
                            </label>
                        </div>
                        <div>
                            <label>
                                <input type="radio" name="c${i.count}" id="optionsRadios3" value="C">
                                C、<span>${u.choiceC}</span>
                            </label>
                        </div>
                        <div>
                            <label>
                                <input type="radio" name="c${i.count}" id="optionsRadios4" value="D">
                                D、<span>${u.choiceD}</span>
                            </label>
                        </div>
                    </div>
                </div>
                <div>
                    <label class="answer" style="display: none;">
                        答案：<span id="ca${i.count}">${u.answer}</span><br>
                        分析：<span>${u.analyse}</span>
                    </label>
                </div>
            </div>
            </c:forEach>

            <h4>二、填空题</h4>
            <c:forEach items="${blanks}" var="u" varStatus="i">
            <div class="question">
                <div class="panels">
                    <h4>${u.question}</h4>
                </div>
                <div class="answer">
                    <span class="input input--hoshi">
                        <input class="input__field input__field--hoshi" type="text" id="b${i.count}" />
                        <label class="input__label input__label--hoshi input__label--hoshi-color-1">
                            <span class="input__label-content input__label-content--hoshi"></span>
                        </label>
                    </span>
                </div>
                <div>
                    <label class="answer" style="display: none;">
                        答案：<span id="ba${i.count}">${u.answer}</span><br>
                        分析：<span>${u.analyse}</span>
                    </label>
                </div>
            </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>
