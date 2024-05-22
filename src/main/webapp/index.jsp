<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 5/21/2024
  Time: 5:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Simple Calculator</title>
</head>
<body>
<form action="calculator" method="post">
    <input type="number" name="operand1" required>
    <select name="operator">
        <option value="add">+</option>
        <option value="subtract">-</option>
        <option value="multiply">*</option>
        <option value="divide">/</option>
    </select>
    <input type="number" name="operand2" required>
    <input type="submit" value="Calculate">
</form>
</body>
</html>
