
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

</head>
<body>
<h1>Edit User</h1>
<form action="/users?action=UPDATE" method="post">
    <input type="hidden" name="id" value="${user.id}">
    <div class="mb-3">
        <label for="userName" class="form-label">UserName</label>
        <input type="text" class="form-control" id="userName" name="name" value="${user.name}">
    </div>
    <div class="mb-3">
        <label for="image" class="form-label">Email</label>
        <input type="file" class="form-control" id="image" name="image" value="${user.image}">
    </div>
    <div class="mb-3">
        <label for="password" class="form-label">Password</label>
        <input type="password" class="form-control" id="password" name="password" value="${user.password}">
    </div>
    <div class="mb-3">
        <label for="country" class="form-label">Country</label>
        <input type="text" class="form-control" id="country" name="country" value="${user.country}">
    </div>
    <input type="submit" class="btn btn-primary" value="UPDATE" name="action">
</form>
<% String message = (String) session.getAttribute("message"); %>
<% if (message != null) { %>
<div class="alert alert-info">
    <%= message %>
</div>
<% session.removeAttribute("message"); %>
<% } %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
