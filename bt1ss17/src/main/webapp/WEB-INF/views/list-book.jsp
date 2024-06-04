<%--
  Created by IntelliJ IDEA.
  User: dokie
  Date: 6/4/2024
  Time: 9:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Bạn đã chọn các gia vị sau:</h2>
<ul>
    <c:forEach items="${selectedCondiments}" var="condiment">
        <li>${condiment}</li>
    </c:forEach>
</ul>

</body>
</html>
