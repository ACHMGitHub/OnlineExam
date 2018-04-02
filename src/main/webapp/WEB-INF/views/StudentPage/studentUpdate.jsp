<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${sessionScope.currentUser.id}</title>
    <link rel="stylesheet" href="../../../css/bootstrap.min.css" />

    <link rel="stylesheet" href="../../../css/addChangeCss.css"/>

    <script type="application/javascript" src="../../../js/jquery-1.11.1.min.js"></script>

    <script language="JavaScript" src="../../../js/bootstrap.min.js"></script>

    <script language="JavaScript" src="../../../js/jquery.validate.min.js"></script>

    <script language="JavaScript" src="../../../js/additional-methods.js"></script>

    <script>

        $.validator.setDefaults({
            submitHandler: function() {
                var param = {
                    uuid : ${sessionScope.currentUser.uuid},
                    id : '${sessionScope.currentUser.id}',
                    pw : $("#pw").val(),
                    name : $("#name").val(),
                    sex : $("input[name='sex']:checked").val(),
                    phone : $("#phoneNum").val(),
                    card : $("#card").val(),
                    className : $("#className").val()
                };
                update(param);
                function update(param){
                    $.ajax({
                        type : "post",
                        dataType: 'json',
                        url : "/studentPage/studentUpdate",
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
            $("#signupForm").validate({
                rules: {
                    username: {
                        required: true,
                        minlength: 2,
                        maxlength: 10
                    },
                    password: {
                        required: true,
                        minlength: 5,
                        maxlength: 15
                    },
                    phoneNum:{
                        required: true,
                        digits:true,
                        minlength: 8,
                        maxlength: 11,
                    },
                    IDcard:{
                        required:true,
                        minlength: 18,
                        maxlength: 18
                    },
                    sex:{
                        required:true
                    },
                    className:{
                        required:true,
                        maxlength: 20
                    }
                },
                messages: {
                    username: {
                        required: "请输入姓名",
                        minlength: "姓名最少由两个字组成",
                        maxlength: "姓名最多由10个字组成"
                    },
                    password: {
                        required: "请输入密码",
                        minlength: "密码长度不能小于 5 个字符",
                        maxlength: "密码长度不能多于 10 个字符组成"
                    },
                    phoneNum:{
                        required:"请输入电话号码",
                        minlength: "电话号码长度不能小于 8 个字符",
                        maxlength: "电话号码长度不能多于 11 个字符组成"
                    },
                    IDcard:{
                        required:"请输入身份证号",
                        minlength: "请输入正确的身份证号",
                        maxlength: "请输入正确的身份证号"
                    },
                    sex:{
                        required:"请选择"
                    },
                    className:{
                        required:"请填写班级",
                        maxlength: "班级名最多为20个字符"
                    }
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
        <label for="name" class="col-sm-2 control-label">学生姓名</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="name" name="username" placeholder="请输入姓名" value="${sessionScope.currentUser.name}">
        </div>
    </div>

    <div class="form-group">
        <label for="pw" class="col-sm-2 control-label">密码</label>
        <div class="col-sm-10">
            <input type="password" class="form-control" id="pw" name="password" value="${sessionScope.currentUser.pw}">
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
        <label for="phoneNum" class="col-sm-2 control-label">联系电话</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="phoneNum" name="phoneNum" value="${sessionScope.currentUser.phone}">
        </div>
    </div>

    <div class="form-group">
        <label for="className"  class="col-sm-2 control-label">班级</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="className" name="className" value="${sessionScope.currentUser.className}">
        </div>
    </div>

    <div class="form-group">
        <label for="card" class="col-sm-2 control-label">身份证</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="card" name="IDcard" value="${sessionScope.currentUser.card}">
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-success btn-block" id="submit">确认</button>
        </div>
    </div>
</form>

</body>
</html>
