<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Register in WaterLevelSPB</title>
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"/>
</head>
<body>
<div class="container" style="width: 300px;">
    <c:url value="/register" var="registerUrl"/>
    <form action="${registerUrl}" method="post">
        <h2 class="form-signin-heading">Create new account</h2>
        <input type="text" class="form-control" name="username" placeholder="Email address" required autofocus>
        <input type="password" class="form-control" name="password" placeholder="Password" required>
        <button class="btn btn-lg btn-success btn-block" type="submit">Register</button>
        <span style="color: red; " ><c:if test="${!empty error}"><c:out value="${error}"/></c:if></span>
        OR
        <input class="btn btn-lg btn-info btn-block" onclick="location.href ='${pageContext.request.contextPath}/login'" value="Login"/>
    </form>
</div>
</body>
</html>
