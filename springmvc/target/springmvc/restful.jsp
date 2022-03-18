<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <link rel="shortcut icon" href="#">
    <title>模拟restful请求</title>
    <%--引入JQuery--%>
    <script src="js/jquery-3.5.1.min.js"></script>
    <script>
        $(function (){
            $("#delete_doctor").click(function(){
                let href = $(this).attr("href");
                $("#del_form").attr("action",href).submit();
                return false;
            });
        })
    </script>
</head>
<body>
<a href="/restful/doctor/3">查询用户</a>
<form action="/restful/doctor/11" method="post">
    <label>
        姓名：<input type="text" name="username"><br>
        年龄：<input type="text" name="age"> <br>
        生日：<input type="text" name="date"> <br>
        <input type="submit" value="添加用户">
    </label>
</form><br/>
<%--转换请求方式的前提是表单必须是post方式提交--%>
<form action="/restful/doctor/11" method="post">
    <input type="hidden" name="_method" value="put">
    <label>
        姓名：<input type="text" name="username"><br>
        年龄：<input type="text" name="age"> <br>
        生日：<input type="text" name="date"> <br>
        <input type="submit" value="修改用户">
    </label>
</form>
<br/>
<a id="delete_doctor" href="/restful/doctor/1">删除用户</a>
<form id="del_form" action="" method="post">
    <input type="hidden" name="_method" value="delete">
</form>
<br/>
</body>
</html>
