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

    <script>

        $.validator.setDefaults({
            submitHandler: function() {
                var param = {
                    id : $("#id").val(),
                    pw : $("#tch_pwd").val(),
                    name : $("#tch_name").val(),
                    sex : $("input[name='sex']:checked").val(),
                    phone : $("#phoneNum").val(),
                    card : $("#tch_card").val(),
                    title : $("#tch_title").val()
                };
                add(param);
                function add(param){
                    $.ajax({
                        type : "post",
                        dataType: 'json',
                        url : "/adminPage/teacherAdd",
                        contentType : "application/json",
                        data : JSON.stringify(param),
                        async:false,
                        success:function(data){
                            if(data){
                                $("#id").val("");
                                $("#tch_pwd").val("");
                                $("#tch_name").val("");
                                $("input:radio[name='sex']").attr("checked",false);
                                $("#phoneNum").val("");
                                $("#tch_card").val("");
                                $("#tch_title").val("");
                                alert("添加成功");
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
                    id: {
                        required: true,
                        minlength: 4,
                        maxlength: 6,
                        remote: {
                            url: "/adminPage/teacherIdCheck",
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
                    tchtitle:{
                        required:true,
                        maxlength: 10
                    }
                },
                messages: {
                    id:{
                        required: "请输入用户名",
                        minlength: "用户名最少由4字符组成",
                        maxlength: "用户名最多由6字符组成",
                        remote: "用户名已存在"
                    },
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
                    tchtitle:{
                        required:"请填写职称",
                        maxlength: "职称在10个字符以内"
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
        <label for="tch_name" class="col-sm-2 control-label">姓名</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="tch_name" name="username" placeholder="请输入姓名">
        </div>
    </div>

    <div class="form-group">
        <label for="tch_pwd" class="col-sm-2 control-label">密码</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="tch_pwd" name="password" placeholder="请输入密码">
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
            <input type="text" class="form-control" id="phoneNum" name="phoneNum" placeholder="请输入电话">
        </div>
    </div>

    <div class="form-group">
        <label for="tch_card" class="col-sm-2 control-label">身份证</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="tch_card" name="IDcard" placeholder="请输入身份证">
        </div>
    </div>

    <div class="form-group">
        <label for="tch_title" class="col-sm-2 control-label">教工职称</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="tch_title" placeholder="请输入职称" name="tchtitle">
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
