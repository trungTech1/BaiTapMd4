<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<c:forEach items="${urls}" var="url">
  <img src="/uploads/${url}" alt="" width="150" height="150" style="object-fit: cover">
</c:forEach>
</body>
</html>
