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
<h1>Edit Book</h1>
<form action="/Book_sevlet?action=EDITBOOK" method="post">
    <div class="mb-3">
        <label for="bookname" class="form-label">Book name</label>
        <input type="text" class="form-control" id="bookname" name="bookname" placeholder="Book name">
    </div>
    <div class="mb-3">
        <label for="category" class="form-label">Category</label>
        <select class="form-select" id="category" name="category">
            <c:forEach items="${categorys}" var="category">
                <option value="${category.id}">${category.name}</option>
            </c:forEach>
        </select>
    </div>
    <div class="mb-3">
        <label for="price" class="form-label
    ">Price</label>
        <input type="number" class="form-control" name="Price" id="price" value="${book.price}" placeholder="Price">
    </div>
    <div class="mb-3">
        <label for="stock" class="form-label
    ">Stock</label>
        <input type="number" class="form-control" id="stock" name="stock" value="${book.stock}" placeholder="Stock">
    </div>
    <div class="mb-3">
        <label for="totalpages" class="form-label
    ">Total pages</label>
        <input type="number" class="form-control" id="totalpages" name="totalpages" value="${book.totalPages}" placeholder="Total pages">
    </div>
    <div class="mb-3">
        <label for="yearcreate" class="form-label
    ">Year create</label>
        <input type="number" class="form-control" id="yearcreate" name="yearcreate"  value="${book.yearCreated}" placeholder="Year create">
    </div>
    <div class="mb-3">
        <label for="author" class="form-label
    ">Author</label>
        <input type="text" class="form-control" id="author" name="author" value="${book.author}" placeholder="Author">
    </div>
    <div class="mb-3">
        <label for="status" class="form-label
    ">Status</label>
        <select class="form-select" id="status" name="status">
            <option selected>Choose...</option>
            <option value="true">Active</option>
            <option value="false">Inactive</option>
        </select>
    </div>
    <input type="submit" class="btn btn-primary" name="action" value="EDITBOOK">
</form>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
