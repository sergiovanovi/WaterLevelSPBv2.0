<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<html>

<head>
    <title>Profile</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">

    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load("current", {packages: ["corechart"]});
        google.charts.setOnLoadCallback(drawChart);
        function drawChart() {
            var arr = [];
            arr.push(['dayOfMonth', 'level']);

            <c:forEach items="${meters}" var="meter">
                arr.push([String(${meter.dateTime.dayOfMonth}
                    + '.' + ${meter.dateTime.monthValue}
                    + '.' + ${meter.dateTime.year}
                    + ' ' + ${meter.dateTime.hour}
                    + 'h'), Number(${meter.level})]);
            </c:forEach>

            var data = google.visualization.arrayToDataTable(arr);

            var options = {
                title: 'Water level in the Neva River St. Petersburg for the last 10 days with an interval of 6 hours, in cm',
                curveType: 'function',
                legend: { position: 'bottom' }
            };

            var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
            chart.draw(data, options);
        }
    </script>

</head>

<body>

<div id="chart_div" style="width: 1500px; height: 500px;"></div>

<table>
    <tr>
        <td>Current level</td>
        <td><c:out value="${lastMeter.level}"/></td>
    </tr>
</table>

</body>
</html>