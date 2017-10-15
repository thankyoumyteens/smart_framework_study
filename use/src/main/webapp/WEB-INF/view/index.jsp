<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2017/10/15
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
hello<br>
<%=(String)request.getAttribute("service")%><br>
<%=(long)request.getAttribute("param")%><br>
</body>
</html>
