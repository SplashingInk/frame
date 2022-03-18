<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
  <title>文件上传与下载</title>
  <script>
    function check() {
      let file = document.getElementById("file");
      if (file.value === "") {
        alert("上传的文件为空")
        return false;
      }
      return true;
    }
  </script>
</head>
<body>
<form action="${pageContext.request.contextPath}/upload" enctype="multipart/form-data" method="post" onsubmit="return check()">
  <input type="file" name="file" multiple="multiple" id="file"/>
  <input type="submit" value="上传">
</form>
<br/>
<h2>Springmvc文件下载</h2>
个人照片<a href="/download/1.jpg">个人照片.png</a><br>
个人简历<a href="/download/2.jpg">个人简历.pdf</a>
</body>
</html>
