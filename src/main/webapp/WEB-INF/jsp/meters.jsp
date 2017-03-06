<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>

<head>
    <title>Meals</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>
<h2>Meter list</h2>

<table>
    <tr>
        <td>DateTime</td>
        <td>Level</td>
    </tr>

    <c:forEach items="${meters}" var="meter">
        <tr>
            <td><c:out value="${fn:replace(meter.dateTime, 'T', ' ')}"/></td>
            <td><c:out value="${meter.level}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>