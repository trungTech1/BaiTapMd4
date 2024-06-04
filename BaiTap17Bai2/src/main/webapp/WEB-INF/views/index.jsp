<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Simple Calculator</title>
</head>
<body>
<h1>Simple Calculator</h1>
<form action="/calculate" method="post">
    Number 1: <input type="number" name="number1"><br>
    Number 2: <input type="number" name="number2"><br>
    <input type="submit" value="Calculate">
</form>
<br>
Result: ${result}
</body>
</html>