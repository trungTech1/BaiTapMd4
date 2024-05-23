<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List Product</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Home</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">product</a>
                </li>
            </ul>
            <form class="d-flex" role="search" action="/product">
                <input class="form-control me-2" type="search" value="${keyword}" placeholder="Search" name="keyword" aria-label="Search">
                <input class="btn btn-outline-success"  type="submit" name="action" value="SEARCH">
            </form>
        </div>
    </div>
</nav>
<h1>List Product</h1>
<a href="/product?action=Add" class="btn btn-primary">Add Product</a>
<table class="table">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Name</th>
        <th scope="col">Image</th>
        <th scope="col">Price</th>
        <th scope="col">Quantity</th>
        <th scope="col">Description</th>
        <th scope="col" colspan="2">Action</th>
    </tr>
    </thead>
    <tbody>
   <c:forEach items="${products}" var="product" varStatus="loop" >
        <tr>
            <th scope="row">${loop.index + 1}</th>
            <td>${product.name}</td>
            <td><img src="${product.image}" alt="image" style="width: 100px; height: 100px; object-fit:cover"></td>
            <td>${product.price}</td>
            <td>${product.quantity}</td>
            <td>${product.description}</td>
            <td><a href="/product?action=Edit&id=${product.id}">Edit</a></td>
            <td><a href="/product?action=Delete&id=${product.id}">Delete</a></td>
        </tr>
   </c:forEach>
    </tbody>
</table>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
