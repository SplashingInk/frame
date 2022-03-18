<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <link rel="shortcut icon" href="#">
    <title>医生列表</title>
</head>
<body>
<table>
    <thead>
        <tr>编号</tr>
        <tr>姓名</tr>
        <tr>年龄</tr>
        <tr>出生日期</tr>
    </thead>
    <tbody>
        <tr>
            <td>${doctor.id}</td>
            <td>${doctor.username}</td>
            <td>${doctor.age}</td>
            <td>${doctor.date}</td>
        </tr>
    </tbody>
</table>
</body>
</html>
