<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 5/21/2024
  Time: 2:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Danh sach khach hang</title>
</head>
<body>
<h1>Danh sách khách hàng</h1>
<table border="1">
    <thead>
    <tr>
        <th>Tên</th>
        <th>Ngày Sinh</th>
        <th>Địa Chỉ</th>
        <th>Ảnh</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="khachHangList" items="${khachHangList}">
        <tr>
            <td>${khachHangList.ten}</td>
            <td>${khachHangList.ngaySinh}</td>
            <td>${khachHangList.diaChi}</td>
            <td><img src="${khachHangList.anh}" alt="Ảnh của khách hàng"></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
