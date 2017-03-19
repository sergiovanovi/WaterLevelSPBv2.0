<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<table>
    <tr>
        <td>Email</td>
        <td>Enabled</td>
        <td>Min</td>
        <td>Max</td>
        <td>Util</td>
        <td></td>
        <td></td>
    </tr>

    <c:forEach items="${users}" var="user">
        <form method="post">
            <tr>
                <td><input  name="email" type="email" value="${user.email}"/></td>
                <td>
                    <select name="enabled">
                        <option <c:if test="${user.enabled == true}">selected</c:if>>true</option>
                        <option <c:if test="${user.enabled == false}">selected</c:if>>false</option>
                    </select>
                </td>
                <td><input name="min" type="number" step="0.1" value="${user.min}"/></td>
                <td><input name="max" type="number" step="0.1" value="${user.max}"/></td>
                <td><input name="util" type="number" min="-1" max="1" value="${user.util}"/></td>
                <td><input class="btn btn-xs btn-danger" type="submit" value="Save"/></td>
                <td><input class="btn btn-xs btn-danger" onclick="location.href ='${pageContext.request.contextPath}/users/${user.id}'" value="Delete"/></td>
            </tr>
        </form>
    </c:forEach>
</table>
</body>
</html>
