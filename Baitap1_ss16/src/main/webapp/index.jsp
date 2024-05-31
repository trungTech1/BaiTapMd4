<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 5/31/2024
  Time: 11:35 AM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Currency Converter</title>
</head>
<body>
<form action="#" th:action="@{/convert}" method="post">
    <label for="rate">Rate:</label><br>
    <input type="number" id="rate" name="rate" value="23000"><br>
    <label for="usd">USD:</label><br>
    <input type="number" id="usd" name="usd" value="1"><br>
    <input type="submit" value="Convert">
</form>
<p th:text="${result}"></p>
</body>
</html>
