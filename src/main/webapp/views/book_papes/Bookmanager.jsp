<%--
  Created by IntelliJ IDEA.
  User: H2O
  Date: 28/05/2024
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<h1>Book Manager</h1>
<a href="/Book_sevlet?action=ADDBOOK" class="btn btn-primary">Add Book</a>
<table class="table">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">name</th>
        <th scope="col">category</th>
        <th scope="col">Price</th>
        <th scope="col">Stock</th>
        <th scope="col">Papes</th>
        <th scope="col">Year</th>
        <th scope="col">Author</th>
        <th scope="col">Status</th>
        <th scope="col" colspan="2">Action</th>
    </tr>
    </thead>
    <tbody>
  <c:forEach items="${books}" var="book" varStatus = "loop">
    <tr>
        <th scope="row">${book.id}</th>
        <td>${book.name}</td>
        <td>${book.category_id}</td>
        <td>${book.price}</td>
        <td>${book.stock}</td>
        <td>${book.totalPages}</td>
        <td>${book.yearCreated}</td>
        <td>${book.author}</td>
        <td>${book.status}</td>
        <td>
            <a href="/Book_sevlet?action=EDITBOOK&id=${book.id}" class="btn btn-primary">Edit</a>
            <a href="/Book_sevlet?action=DELETEBOOK&id=${book.id}" class="btn btn-danger">Delete</a>
        </td>
    </tr>
    </c:forEach>
    </tbody>
</table>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
