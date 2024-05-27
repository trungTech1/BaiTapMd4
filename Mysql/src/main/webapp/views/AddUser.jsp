<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AddUser</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

</head>
<body>

<h1>Add User</h1>
<form action="/users?action=ADD" method="post" enctype="multipart/form-data">
    <div class="mb-3">
        <label for="name" class="form-label">UserName</label>
        <input type="text" class="form-control" id="name" name="name" placeholder="User Name.....">
    </div>
    <div class="mb-3">
        <label for="password" class="form-label">Password</label>
        <input type="password" class="form-control" id="password" name="password" placeholder="Password...">
    </div>
    <div class="mb-3">
        <label for="image" class="form-label">image</label>
        <img src="" alt="" id="previews" width="150" height="150" style="object-fit: cover">
        <input type="file" class="form-control" id="image" name="file" placeholder="image....">
    </div>
    <div class="mb-3">
        <label for="country" class="form-label">Country</label>
        <input type="text" class="form-control" id="country" name="country" placeholder="country....">
    </div>
    <input type="submit" class="btn btn-primary" value="ADD" name="action">
</form>
<script>
    const image = document.getElementById('image');
    const previews = document.getElementById('previews');
    image.addEventListener('change', function () {
        const file = this.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function () {
                previews.src = reader.result;
            }
            reader.readAsDataURL(file);
        }
    });
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
