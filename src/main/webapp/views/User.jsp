<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        body {
            margin: 20px;
        }
    </style>
</head>
<body>
<h1>Users</h1>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Navbar</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">Home</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        Sort By
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="
                        /users?action=LIST_SORT&sortBy=name">Name</a></li>
                        <li><a class="dropdown-item" href="
                        /users?action=LIST_SORT&sortBy=country">Country</a></li>
                    </ul>
                </li>
            </ul>
            <form class="d-flex" role="search" action="/users">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="keyword"
                       value="${keyword}">
                <input class="btn btn-outline-success" type="submit" name="action" value="SEARCH">
            </form>
        </div>
    </div>
</nav>
<a href="<c:url value="/users?action=ADD"/>">Create User</a>
<table class="table table-bordered">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">UserName</th>
        <th scope="col">Image</th>
        <th scope="col">Country</th>
        <th scope="col" colspan="2">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach varStatus="status" items="${users}" var="user">
        <tr>
            <th scope="row">${status.index + 1}</th>
            <td>${user.name}</td>
            <td>
                <img src="${user.image}" alt="image" width="50px" height="50px">
            </td>
            <td>${user.country}</td>
            <td>
                <button type="button" class="btn btn-primary"
                        onclick="location.href='/users?action=UPDATE&id=${user.id}'">Edit
                </button>
            </td>
            <td>
                <button type="button" class="btn btn-danger"
                        onclick="location.href='/users?action=DELETE&id=${user.id}'">Delete
                </button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
