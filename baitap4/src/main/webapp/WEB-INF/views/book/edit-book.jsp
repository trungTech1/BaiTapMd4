<%--
  Created by IntelliJ IDEA.
  User: dokie
  Date: 5/30/2024
  Time: 3:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<form action="/edit-book" method="post">
    <h1>Edit BOOK</h1>
    <div class="mb-3">
        <label for="name" class="form-label">ID</label>
        <input type="text" class="form-control" id="id" name="id" value="${books.id}" readonly>
    </div>
    <div class="mb-3">
        <label for="name" class="form-label">Name</label>
        <input type="text" class="form-control" id="name" name="name" value="${books.name}">
    </div>
    <div class="mb-3">
        <label for="catalog" class="form-label"> Catalog</label>
        <input type="number" class="form-control" name="catalog" id="catalog" value="${books.catalog}">
    </div>
    <div class="mb-3">
        <label for="authors" class="form-label">Author</label>
        <input type="number" class="form-control" name="author" id="authors" value="${books.author}">
    </div>
    <div class="mb-3">
        <label for="status" class="form-label">Status</label>
        <select class="form-control" name="status" id="status" value="${books.status}">

            <option value="true">Active</option>
            <option value="false">Inactive</option>
        </select>
    </div>
    <input type="submit" name="action"  class="btn btn-primary" onclick="return alert('da chinh sua thanh cong')"/>
</form>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>

