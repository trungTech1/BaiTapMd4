<%--
  Created by IntelliJ IDEA.
  User: H2O
  Date: 28/05/2024
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<h1>Add Book</h1>
<form action="/Category_servlet?action=ADDCATEGORY" method="post">
<div class="mb-3">
    <label for="categoryname" class="form-label">Category name</label>
    <input type="text" class="form-control" id="categoryname" name="categoryname" placeholder="Category name">
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
<input type="submit" class="btn btn-primary" name="action" value="ADDCATEGORY">
</form>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
