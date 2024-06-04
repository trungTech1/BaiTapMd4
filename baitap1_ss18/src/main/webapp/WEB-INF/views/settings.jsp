<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: dokie
  Date: 6/4/2024
  Time: 10:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Email Settings</title>
    <style>
        body { font-family: Arial, sans-serif; }
        .form-group { margin-bottom: 15px; }
        label { display: inline-block; width: 100px; }
        select, input[type="checkbox"] { margin-right: 10px; }
        textarea { width: 300px; height: 100px; }
        .btn { padding: 8px 15px; cursor: pointer; }
        .btn-primary { background-color: #007bff; color: white; border: none; }
    </style>
</head>
<body>
<h2>Settings</h2>
<form:form modelAttribute="settings" action="settings" method="post">
    <div class="form-group">
        <form:label path="language">Languages</form:label>
        <form:select path="language">
            <form:option value="English" label="English"/>
            <form:option value="Vietnamese" label="Vietnamese"/>
            <form:option value="Japanese" label="Japanese"/>
            <form:option value="Chinese" label="Chinese"/>
        </form:select>
    </div>

    <div class="form-group">
        <form:label path="pageSize">Page Size:</form:label>
        Show
        <form:select path="pageSize">
            <form:option value="5" label="5"/>
            <form:option value="10" label="10"/>
            <form:option value="15" label="15"/>
            <form:option value="25" label="25"/>
            <form:option value="50" label="50"/>
            <form:option value="100" label="100"/>
        </form:select>
        emails per page
    </div>

    <div class="form-group">
        <form:label path="spamFilter">Spams filter:</form:label>
        <form:checkbox path="spamFilter" value="true"/>
        Enable spams filter
    </div>

    <div class="form-group">
        <form:label path="signature">Signature:</form:label><br>
        <form:textarea path="signature" rows="4" cols="50"/>
    </div>

    <div class="form-group">
        <input type="submit" value="Update"/>
        <a href="/">Cancel</a>
    </div>
</form:form>

</body>
</html>
