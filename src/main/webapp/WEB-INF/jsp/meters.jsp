<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>

<head>
    <title>Meals</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">

    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load("current", {packages: ["corechart"]});
        google.charts.setOnLoadCallback(drawChart);
        function drawChart() {
            var arr = [];
            arr.push(['id', 'level']);

            <c:forEach items="${meters}" var="meter">
                arr.push([String(${meter.id}), Number(${meter.level})]);
            </c:forEach>

            var data = google.visualization.arrayToDataTable(arr);

            var options = {
                title: 'the water level in the river Neva St. Petersburg , in cm',
                isStacked: true,
                colors: ['blue']
            };

            var chart = new google.visualization.SteppedAreaChart(document.getElementById('chart_div'));
            chart.draw(data, options);
        }
    </script>

</head>

<body>
<h2>Meter list over the last 30 days</h2>

<div id="chart_div" style="width: 1000px; height: 500px;"></div>

<table id="tableMeters">
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