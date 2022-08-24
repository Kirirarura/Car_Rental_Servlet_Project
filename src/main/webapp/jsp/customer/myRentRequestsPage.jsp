<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf" %>

<!DOCTYPE>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registered Users</title>
    <%@ include file="../partial/head.jspf" %>
    <link rel="stylesheet" href="<c:url value="/static/css/listPage.css"/>">
    <link rel="stylesheet" href="<c:url value="/static/css/pagination.css"/>">

</head>

<body>
<header>
    <tf:chooseHeader role="${sessionScope.role}"/>
</header>

<main>
    <input type="hidden" id="status" value="<%= request.getAttribute("status")%>">
    <h1>Requests that you have made</h1>
    <div class="card-content">
        <c:forEach var="booking" items="${requestScope.bookingList}">
        <div class="card-item">
            <div class="container">
                <div class="details">
                    <h2 class="primaryInfo">Car: <c:out value="${booking.car.brand} ${booking.car.modelName}"/></h2>
                    <h3 class="secondaryInfo"><c:out value="${booking.startDate} - ${booking.endDate}"/></h3>
                </div>
                <div class="details">
                    <h2 class="primaryInfo">Passport ID: <c:out value="${booking.userDetails} "/></h2>
                    <h3 class="secondaryInfo">Price: <c:out value="${booking.price}"/></h3>
                </div>
                <div class="details">
                    <h2 class="primaryInfo">Status: <c:out value="${booking.bookingStatus}"/></h2>
                </div>
            </div>

            <form action="${pageContext.request.contextPath}/Customer/terminate" method="post" name="terminate">
                <input type="text" name="bookingId" value="${booking.id}" hidden="hidden">
                <input type="text" name="carId" value="${booking.car.carId}" hidden="hidden">
                <c:if test="${booking.bookingStatus == 'PENDING_REVIEW' || booking.bookingStatus == 'ON_REVIEW'}">
                <div class="block">
                    <input type="submit" class="btn" value="Cancel">
                </div>
                </c:if>
        </div>
        </form>
    </div>
    </c:forEach>
    </div>

    <div class="pagination">
    </div>
</main>

<footer>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/pagination.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.19/dist/sweetalert2.all.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/js/messages/infoLoadingMessages.js"></script>
</footer>
</body>
</html>
