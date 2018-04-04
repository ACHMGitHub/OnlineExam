<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="../css/bootstrap.min.css" />

    <link rel="stylesheet" href="../css/addChangeCss.css"/>

    <script type="application/javascript" src="../js/jquery-1.11.1.min.js"></script>

    <script language="JavaScript" src="../js/bootstrap.min.js"></script>

    <script language="JavaScript" src="../js/jquery.validate.min.js"></script>

    <script language="JavaScript" src="../js/additional-methods.js"></script>

    <script language="JavaScript" src="../js/messages_zh.js"></script>

    <script>

        $.validator.setDefaults({
            submitHandler: function(){

                var param = {
                    uuid : ${sessionScope.currentUser.uuid},
                    id : '${sessionScope.currentUser.id}',
                    pw : $("#pw").val(),
                    name : $("#name").val(),
                    sex : $("input[name='sex']:checked").val(),
                    phone : $("#phoneNum").val(),
                    card : $("#card").val(),
                    /*className : $("#className").val()*/
                    title:$("#title").val()
                };
                update(param);
                function update(param){
                    alert("提交");
                    $.ajax({
                        type : "post",
                        dataType: 'json',
                        url : "/teacherPage/teacherUpdate",
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
            }
        });
        $().ready(function() {
// 在键盘按下并释放及提交后验证提交表单
            $("#signupForm").validate({
                rules: {
                    firstname: "required",
                    lastname: "required",
                    tchtitle:"required",
                    username: {
                        required: true,
                        minlength: 2
                    },
                    password: {
                        required: true,
                        minlength: 5
                    },
                    phoneNum:{
                        required: true,
                        isPhone:true,
                    },
                    IDcard:{
                        required:true,
                    },
                    gridRadios:{
                        required:true,
                    },
                    confirm_password: {
                        required: true,
                        minlength: 5,
                        equalTo: "#password"
                    },
                    email: {
                        required: true,
                        email: true
                    },
                    "topic[]": {
                        required: "#newsletter:checked",
                        minlength: 2
                    },
                    agree: "required"
                },
                messages: {
                    firstname: "请输入您的名字",
                    lastname: "请输入您的姓氏",
                    tchtitle:"请输入教职",
                    username: {
                        required: "请输入用户名",
                        minlength: "用户名必需由两个字母组成"
                    },
                    password: {
                        required: "请输入密码",
                        minlength: "密码长度不能小于 5 个字母"
                    },
                    phoneNum:{
                        required:"请输入电话号码",
                    },
                    IDcard:{
                        required:"请输入身份证号",

                    },
                    gridRadios:{
                        required:"请选择",
                    },
                    confirm_password: {
                        required: "请输入密码",
                        minlength: "密码长度不能小于 5 个字母",
                        equalTo: "两次密码输入不一致"
                    },
                    email: "请输入一个正确的邮箱",
                    agree: "请接受我们的声明",
                    topic: "请选择两个主题"
                },

                errorPlacement: function (error, element) { //指定错误信息位置
                    if (element.is(':radio') || element.is(':checkbox')) { //如果是radio或checkbox
                        var eid = element.attr('name'); //获取元素的name属性
                        error.appendTo(element.parent()); //将错误信息添加当前元素的父结点后面
                    } else {
                        error.insertAfter(element);
                    }
                }

            });
        });

    </script>


</head>
<body>
<form class="form" role="form" id="signupForm">

    <div class="form-group">
        <label for="tch_name" class="col-sm-2 control-label">教工姓名</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="name" name="username" value="${sessionScope.currentUser.name}">
        </div>
    </div>

    <div class="form-group">
        <label for="tch_pwd" class="col-sm-2 control-label">密码</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="pw" name="password"  value="${sessionScope.currentUser.pw}">
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-2">性别</label>
        <div class="col-sm-10">
            <div>
                <label>
                    <input type="radio" name="sex" id="gridRadios1" value="1" <c:if test="${sessionScope.currentUser.sex == 1}">checked="checked"</c:if> >男
                    <input type="radio" name="sex" id="gridRadios2" value="0" <c:if test="${sessionScope.currentUser.sex == 0}">checked="checked"</c:if> >女
                </label>
            </div>

        </div>
    </div>

    <div class="form-group">
        <label  class="col-sm-2 control-label">联系电话</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="phoneNum" name="phoneNum" value="${sessionScope.currentUser.phone}">
        </div>
    </div>

    <div class="form-group">
        <label for="tch_card" class="col-sm-2 control-label">身份证</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="card" name="IDcard" value="${sessionScope.currentUser.card}">
        </div>
    </div>

    <div class="form-group">
        <label for="tch_title" class="col-sm-2 control-label">教工职称</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="title"
                   value="${sessionScope.currentUser.title}" name="title">
        </div>
    </div>


    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-success btn-block">添加</button>
        </div>
    </div>
</form>

</body>
</html>

