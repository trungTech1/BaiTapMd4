<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Book Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-9ndCyUaIbpfBQ2OgQod3F+QQkhS0xtpdmdvmMBFjQXSwPHWSHMwRgFi4Zl+9L7E=" crossorigin="anonymous">
    <style>
        body {
            background-color: #f8f9fa;
        }

        .container {
            margin-top: 50px;
        }

        .nav-link {
            margin-right: 20px;
            font-weight: bold;
            color: #333;
        }

        .nav-link:hover {
            color: #007bff;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header bg-primary text-white">
                    <h1 class="card-title mb-0"><%= "Hello Book Admin" %>
                    </h1>
                </div>
                <div class="card-body">
                    <nav>
                        <a href="/Book_sevlet?action=LISTBOOK" class="nav-link">Book</a>
                        <a href="/Category_servlet?action=LISTCATEGORY" class="nav-link">Category</a>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
        crossorigin="anonymous"></script>
</body>
</html>