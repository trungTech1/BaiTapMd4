<%--
  Created by IntelliJ IDEA.
  User: dokie
  Date: 6/4/2024
  Time: 9:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Sandwich Condiments</h2>
<form action="/sandwich" method="post">
    <input type="checkbox" name="condiments" value="Lettuce"> Lettuce<br>
    <input type="checkbox" name="condiments" value="Tomato"> Tomato<br>
    <input type="checkbox" name="condiments" value="Mustard"> Mustard<br>
    <input type="checkbox" name="condiments" value="Sprouts"> Sprouts<br>
    <input type="submit" value="Save">
</form>
</body>
</html>
