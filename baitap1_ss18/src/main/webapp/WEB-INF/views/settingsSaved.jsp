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
    <title>Title</title>
</head>
<body>
<h2>Settings Saved</h2>
<p>Your email settings have been successfully updated.</p>
<ul>
    <li>Language: ${settings.language}</li>
    <li>Page Size: ${settings.pageSize} emails per page</li>
    <li>Spam Filter: ${settings.spamFilter ? 'Enabled' : 'Disabled'}</li>
    <li>Signature: ${settings.signature}</li>
</ul>
<a href="settings">Back to Settings</a>

</body>
</html>
