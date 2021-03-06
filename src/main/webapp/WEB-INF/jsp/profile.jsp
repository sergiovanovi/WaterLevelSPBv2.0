<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
    <title>
        <c:if test="${user.util == 0}">Within the specified limits</c:if>
        <c:if test="${user.util > 0}">Above the specified limits</c:if>
        <c:if test="${user.util < 0}">Below the specified limits</c:if>
    </title>
    <jsp:include page="headTag.jsp"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">

    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load("current", {packages: ["corechart"]});
        google.charts.setOnLoadCallback(drawChart);
        function drawChart() {
            var arr = [];
            arr.push(['dayOfMonth', 'level', 'min', 'max']);

            <c:forEach items="${meters}" var="meter">
            arr.push([String(${meter.dateTime.dayOfMonth}
                +'.' + ${meter.dateTime.monthValue} +'.' + ${meter.dateTime.year} +' ' + ${meter.dateTime.hour} +'h'), Number(${meter.level}), Number(${user.min}), Number(${user.max})]);
            </c:forEach>

            var data = google.visualization.arrayToDataTable(arr);

            var options = {
                title: 'Water level in the Neva River St. Petersburg for the last 7 days with an interval of 2 hours, in cm',
                curveType: 'function',
                legend: {position: 'bottom'}
            };

            var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
            chart.draw(data, options);
        }
    </script>

</head>

<body>

<div id="chart_div" style="width: 1500px; height: 500px;"></div>

<form method="post">
    <table>
        <tr>
            <td>Current login</td>
            <td>
                <sec:authentication property="principal.username"/>
                <a class="btn btn-xs btn-danger" href="<c:url value="/logout"/>" role="button">Logout</a>
            </td>
        </tr>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <tr>
                <td>Users</td>
                <td><input class="btn btn-xs btn-primary" onclick="location.href ='${pageContext.request.contextPath}/users'" value="Users"></td>
            </tr>
        </sec:authorize>
        <tr>
            <td>Current level</td>
            <td><c:out value="${lastMeter.level}"/></td>
        </tr>
        <tr>
            <td>Max level</td>
            <td><input name="max" type="number" step="0.1" min="${user.min}" value="${user.max}" required/></td>
        </tr>
        <tr>
            <td>Min level</td>
            <td><input name="min" type="number" step="0.1" max="${user.max}" value="${user.min}" required/></td>
        </tr>
        <tr>
            <td>Save Input Limits</td>
            <td><input class="btn btn-xs btn-success" type="submit" value="Save"/></td>
        </tr>
    </table>
</form>

</body>
</html>