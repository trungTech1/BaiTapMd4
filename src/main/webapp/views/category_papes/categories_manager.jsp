<%--
  Created by IntelliJ IDEA.
  User: H2O
  Date: 28/05/2024
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<h1>Category Manager</h1>
<a href="/Category_servlet?action=ADDCATEGORY" class="btn btn-primary">Add Category</a>
<table class="table">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Name</th>
        <th scope="col">Status</th>
        <th scope="col" colspan="2">Action</th>
    </tr>
    </thead>
    <tbody>
 <c:forEach items="${categories}" var="category" varStatus = "loop">
    <tr>
        <th scope="row">${category.id}</th>
        <td>${category.name}</td>
        <td>${category.status}</td>
        <td>
            <a href="/Category_servlet?action=EDITCATEGORY&id=${category.id}" class="btn btn-primary">Edit</a>
            <a href="/Category_servlet?action=DELETECATEGORY&id=${category.id}" class="btn btn-danger">Delete</a>
        </td>
    </tr>
    </c:forEach>
    </tbody>
</table>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
