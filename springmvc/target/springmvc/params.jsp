<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <link rel="shortcut icon" href="#">
    <title>测试参数界面</title>
</head>
<body>
<a href="/param/testParam?username=徐凤年&password=123">测试普通参数</a>
<br/>
<a href="/param/testParam">测试普通参数（有默认值）</a>
<br/>
<h2>测试保存对象</h2>
<form action="/param/saveDoctor" method="post">
    <label>
        姓名：<input type="text" name="username"><br>
        年龄：<input type="text" name="age"> <br>
        生日：<input type="text" name="date"> <br>
        <input type="submit" value="提交">
    </label>
</form>
<br/>
<a href="/param/testAnt/rolea/boy/hello/key/blue/has">测试Ant形式的请求方式</a>
<br/>
<a href="/param/sessionId">测试CookieValue</a>
<br/>
<a href="/param/header">测试header内容</a>
<br/>
</body>
</html>
