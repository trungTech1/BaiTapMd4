<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Upload Song</title>
</head>
<body>
<h1>Upload Song</h1>
<form:form modelAttribute="song" method="post" enctype="multipart/form-data">
    <div>
        <label for="title">Title:</label>
        <form:input path="title" id="title" />
        <form:errors path="title" cssClass="error" />
    </div>
    <div>
        <label for="artist">Artist:</label>
        <form:input path="artist" id="artist" />
        <form:errors path="artist" cssClass="error" />
    </div>
    <div>
        <label for="genre">Genre:</label>
        <form:select path="genre" id="genre">
            <form:options items="${genres}" />
        </form:select>
        <form:errors path="genre" cssClass="error" />
    </div>
    <div>
        <label for="file">File:</label>
        <form:input type="file" path="file" id="file" />
        <form:errors path="file" cssClass="error" />
    </div>
    <button type="submit">Upload</button>
</form:form>
</body>
</html>