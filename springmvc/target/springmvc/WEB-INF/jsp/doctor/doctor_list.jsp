<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <link rel="shortcut icon" href="#">
    <title>医生列表</title>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme()+ "://" +request.getServerName()+ ":" +request.getServerPort()+path+ "/" ;
    %>
    <script src="<%=basePath%>/js/jquery-3.5.1.min.js"></script>
    <script>
        $(function (){
            $(document).on("click",".update_doctor",function (){
                $.ajax({
                    url:"/restful/doctor/"+$(this).attr("doctor_id"),
                    type:"post",
                    data:{_method:"PUT"},
                    success:function (){
                        console.log("修改信息成功");
                    }
                })
            })

            $(document).on("click",".delete_doctor",function (){
                $.ajax({
                    url:"/restful/doctor/"+$(this).attr("doctor_id"),
                    type:"post",
                    data:{_method:"DELETE"},
                    success:function (){
                        console.log("删除医生信息成功");
                    }
                })
            })
        })
    </script>
</head>
<body>
<table>
    <thead>
        <tr>编号</tr>
        <tr>姓名</tr>
        <tr>年龄</tr>
        <tr>出生日期</tr>
        <tr>操作</tr>
    </thead>
    <tbody>
    <c:forEach items="${doctorList}" var="doctor">
        <tr>
            <td>${doctor.id}</td>
            <td>${doctor.username}</td>
            <td>${doctor.age}</td>
            <td>${doctor.date}</td>
            <td>
                <button class="update_doctor" doctor_id="${doctor.id}">修改</button>|
                <button class="delete_doctor" doctor_id="${doctor.id}">删除</button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
