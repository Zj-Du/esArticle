<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery-3.3.1.js"></script>
</head>
<body style="text-align: center">
<form action="${pageContext.request.contextPath}/poem/add" method="post">
    姓名:<input name="name" type="text"><br/>
    作者:<input name="author" type="text"><br/>
    内容:<input name="content" type="text"><br/>
    <input type="submit" value="添加">
</form>

</body>
</html>