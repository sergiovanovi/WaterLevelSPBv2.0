<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login in WaterLevelSPB</title>
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"/>
</head>
<body>
<div class="container" style="width: 300px;">
    <c:url value="/spring_security_check" var="loginUrl"/>
    <form action="${loginUrl}" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <input type="email" class="form-control" name="username" placeholder="Email address" required autofocus>
        <input type="password" class="form-control" name="password" placeholder="Password" required>
        <button class="btn btn-lg btn-success btn-block" type="submit">Login</button>
        <span style="color: red; " ><c:if test="${!empty error}"><c:out value="${error}"/></c:if></span>
        <input class="btn btn-lg btn-info btn-block" onclick="location.href ='${pageContext.request.contextPath}/register'" value="Register"/>
    </form>
</div>
</body>
</html>
