<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="../../../css/bootstrap.min.css" />

    <link rel="stylesheet" href="../../../css/addChangeCss.css"/>

    <script type="application/javascript" src="../../../js/jquery-1.11.1.min.js"></script>

    <script language="JavaScript" src="../../../js/bootstrap.min.js"></script>

    <script language="JavaScript" src="../../../js/jquery.validate.min.js"></script>

    <script language="JavaScript" src="../../../js/additional-methods.js"></script>

    <script language="JavaScript" src="../../../js/messages_zh.js"></script>


    <script>

        $.validator.setDefaults({
            submitHandler: function() {
                var param = {
                    id : $("#id").val(),
                    pw : $("#pw").val(),
                    name : $("#name").val(),
                    sex : $("input[name='sex']:checked").val(),
                    phone : $("#phoneNum").val(),
                    card : $("#card").val(),
                    className : $("#className").val()
                };
                add(param);
                function add(param){
                    $.ajax({
                        type : "post",
                        dataType: 'json',
                        url : "/adminPage/studentAdd",
                        contentType : "application/json",
                        data : JSON.stringify(param),
                        async:false,
                        success:function(data){
                            if(data){
                                $("#id").val("");
                                $("#pw").val("");
                                $("#name").val("");
                                $("input:radio[name='sex']").attr("checked",false);
                                $("#phoneNum").val("");
                                $("#card").val("");
                                $("#title").val("");
                                $("#className").val("");
                                alert("添加成功");
                            }
                        }
                    })
                }
            }
        });
        $().ready(function() {
            $("#signupForm").validate({
                rules: {
                    id: {
                        required: true,
                        minlength: 8,
                        remote: {
                            url: "/adminPage/studentIdCheck",
                            type: "post",
                            dataType: "json",
                            data: {
                                id: function () {
                                    return $("#id").val();
                                }
                            }
                        }
                    },
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
                    },
                    IDcard:{
                        required:true,
                    },
                    sex:{
                        required:true,
                    },
                    className:{
                        required:true
                    }
                },
                messages: {
                    id:{
                        required: "请输入用户名",
                        minlength: "用户名必需由8字符组成",
                        remote: "用户名已存在"
                    },
                    username: {
                        required: "请输入用户名",
                        minlength: "姓名必需由两个字符组成"
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
                    sex:{
                        required:"请选择",
                    },
                    className:{
                        required:"请填写班级",
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
        <label class="col-sm-2 control-label">用户名</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="id" name="id" placeholder="请输入用户名">
        </div>
    </div>


    <div class="form-group">
        <label for="name" class="col-sm-2 control-label">学生姓名</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="name" name="username"
                   placeholder="请输入姓名" >
        </div>
    </div>

    <div class="form-group">
        <label for="pw" class="col-sm-2 control-label">密码</label>
        <div class="col-sm-10">
            <input type="password" class="form-control" id="pw" name="password">
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-2">性别</label>
        <div class="col-sm-10">
            <div>
                <label>
                    <input type="radio" name="sex" id="gridRadios1" value="1">男
                    <input type="radio" name="sex" id="gridRadios2" value="0">女
                </label>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label for="phoneNum" class="col-sm-2 control-label">联系电话</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="phoneNum" name="phoneNum">
        </div>
    </div>

    <div class="form-group">
        <label for="className"  class="col-sm-2 control-label">班级</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="className" name="className">
        </div>
    </div>

    <div class="form-group">
        <label for="card" class="col-sm-2 control-label">身份证</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="card" name="IDcard">
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
