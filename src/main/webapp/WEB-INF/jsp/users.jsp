<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
    <jsp:include page="headTag.jsp"/>
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
                <td><input  name="email" type="email" value="${user.email}" required/></td>
                <td>
                    <select name="enabled">
                        <option <c:if test="${user.enabled == true}">selected</c:if>>true</option>
                        <option <c:if test="${user.enabled == false}">selected</c:if>>false</option>
                    </select>
                </td>
                <td><input name="min" type="number" step="0.1" value="${user.min}" required/></td>
                <td><input name="max" type="number" step="0.1" value="${user.max}" required/></td>
                <td><input name="util" type="number" min="-1" max="1" value="${user.util}" required/></td>
                <td><input class="btn btn-xs btn-success" type="submit" value="Save"/></td>
                <td><input class="btn btn-xs btn-danger" onclick="location.href ='${pageContext.request.contextPath}/users/${user.id}'" value="Delete"/></td>
            </tr>
        </form>
    </c:forEach>
</table>
</body>
</html>
