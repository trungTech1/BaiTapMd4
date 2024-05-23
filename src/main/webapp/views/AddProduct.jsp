<%--
  Created by IntelliJ IDEA.
  User: H2O
  Date: 22/05/2024
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Product</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<style>
    a{
        text-decoration: none;
        color: aqua;
        background-color: azure;
        padding: 5px;
        border-radius: 5px;
    }
</style>
</head>
<body>
<h1>Add Product</h1>
<a href="/product?action=List" class="btn btn-primary">Quay lai</a>
<form action="/product" method="post">
    <div class="mb-3">
        <label for="name" class="form-label">Product Name</label>
        <input type="text" class="form-control" id="name" name="name"  placeholder="New Product name...">
    </div>
    <div class="mb-3">
        <label for="price" class="form-label">Product Price</label>
        <input type="number" class="form-control" id="price" name="price" placeholder="New Product price...">
    </div>
    <div class="mb-3">
        <label for="quantity" class="form-label">Product Quantity</label>
        <input type="number" class="form-control" id="quantity" name="quantity" placeholder="New Product quantity...">
    </div>
    <div class="mb-3">
        <label for="description" class="form-label">Product Description</label>
        <textarea class="form-control" id="description" name="description" rows="3" placeholder="New Product description..."></textarea>
    </div>
    <div class="mb-3">
        <label for="image" class="form-label">Product Image</label>
        <input type="text" class="form-control" id="image" name="image" placeholder="New Product image...">
    </div>
    <input type="submit" name="action" value="Save" class="btn btn-primary">
</form>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

</body>
</html>
