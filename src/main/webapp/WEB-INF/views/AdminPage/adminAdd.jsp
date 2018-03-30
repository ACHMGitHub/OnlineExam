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
                    pw : $("#ad_pwd").val(),
                    name : $("#ad_name").val(),
                    sex : $("input[name='sex']:checked").val(),
                    phone : $("#ad_phone").val(),
                    card : $("#ad_card").val()
                };
                add(param);
                function add(param){
                    $.ajax({
                        type : "post",
                        dataType: 'json',
                        url : "/adminPage/adminAdd",
                        contentType : "application/json",
                        data : JSON.stringify(param),
                        async:false,
                        success:function(data){
                            if(data){
                                $("#id").val("");
                                $("#ad_pwd").val("");
                                $("#ad_name").val("");
                                $("input:radio[name='sex']").attr("checked",false);
                                $("#ad_phone").val("");
                                $("#ad_card").val("");
                                alert("添加成功");
                            }
                        }
                    })
                }
            }
        });
        $().ready(function() {
            // 在键盘按下并释放及提交后验证提交表单
            $("#adminForm").validate({

                rules: {
                    id: {
                        required: true,
                        minlength: 6,
                        remote: {
                            url: "/adminPage/adminIdCheck",
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
                    sex:{
                        required:true,
                    },
                    phoneNum:{
                        required: true,

                    },
                    IDcard:{
                        required:true,
                    },
                },
                messages: {
                    id:{
                        required: "请输入用户名",
                        minlength: "用户名必需由6字母组成",
                        remote: "用户名已存在"
                    },
                    username: {
                        required: "请输入用户名",
                        minlength: "用户名必需由两个字母组成"
                    },
                    password: {
                        required: "请输入密码",
                        minlength: "密码长度不能小于 5 个字母"
                    },
                    sex:{
                        required:"请选择性别",
                    },
                    phoneNum:{
                        required:"请输入电话号码",
                    },
                    IDcard:{
                        required:"请输入身份证号",
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

    <script type="text/javascript">

    </script>

</head>
<body>
<form class="form" role="form" id="adminForm">

    <div class="form-group">
        <label class="col-sm-2 control-label">用户名</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="id" name="id" placeholder="请输入用户名">
        </div>
    </div>

    <div class="form-group">
        <label for="ad_name" class="col-sm-2 control-label">姓名</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="ad_name" name="username" placeholder="请输入姓名">
        </div>
    </div>

    <div class="form-group">
        <label for="ad_pwd" class="col-sm-2 control-label">密码</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="ad_pwd" name="password" placeholder="请输入密码">
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-2">性别</label>
        <div class="col-sm-10">
            <div >
                <label>
                    <input type="radio" name="sex" id="gridRadios1" value="1">男
                    <input type="radio" name="sex" id="gridRadios2" value="0">女
                </label>
            </div>

        </div>
    </div>

    <div class="form-group">
        <label for="ad_phone" class="col-sm-2 control-label">联系电话</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" name="phoneNum" id="ad_phone" placeholder="请输入电话">
        </div>
    </div>

    <div class="form-group">
        <label for="ad_card" class="col-sm-2 control-label">身份证</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="ad_card" name="IDcard" placeholder="请输入证号">
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-success btn-block" id="addButton">添加</button>
        </div>
    </div>
</form>

</body>
</html>
