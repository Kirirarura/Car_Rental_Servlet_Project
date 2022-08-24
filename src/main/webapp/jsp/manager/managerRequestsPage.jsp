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
    <h1>Requests that you are working on</h1>
    <div class="card-content">
        <c:forEach var="booking" items="${requestScope.bookingList}">
        <div class="card-item">
            <div class="container">
                <div class="details">
                    <h2 class="primaryInfo">Request Id:<c:out value="${booking.id} "/></h2>
                    <h3 class="secondaryInfo">Car: <c:out value="${booking.car.brand} ${booking.car.modelName}"/></h3>
                </div>
                <div class="details">
                    <h2 class="primaryInfo">Period:</h2>
                    <h3 class="secondaryInfo"> Period:<c:out value="${booking.startDate} - ${booking.endDate}"/></h3>
                </div>
                <div class="details">
                    <h2 class="primaryInfo">User: <c:out
                            value="${booking.user.firstName} ${booking.user.lastName}"/></h2>
                    <h3 class="secondaryInfo">Passport number: <c:out value="${booking.userDetails} "/></h3>

                </div>
                <div class="details">
                    <h2 class="primaryInfo">Price: <c:out value="${booking.price}"/></h2>
                    <c:choose>
                        <c:when test="${booking.withDriver}">
                            <h3 class="secondaryInfo">With driver option selected</h3>
                        </c:when>
                        <c:when test="${!booking.withDriver}">
                            <h3 class="secondaryInfo">Without driver option selected</h3>
                        </c:when>
                    </c:choose>
                </div>
            </div>
                <c:choose>
                    <c:when test="${booking.bookingStatus == 'ON_REVIEW' || booking.bookingStatus == 'ACTIVE'}">
                        <div class="responses">
                            <div class="details">
                                <h2 class="primaryInfo">Status: </h2>
                                <h3 class="secondaryInfo"><c:out value="${booking.bookingStatus}"/></h3>
                            </div>
                            <c:choose>
                                <c:when test="${booking.bookingStatus.toString() == 'ON_REVIEW'}">
                                    <div class="acceptRequest">
                                        <form action="${pageContext.request.contextPath}/Manager/acceptRequest"
                                              method="post" name="acceptRequest">
                                            <input type="text" name="id" value="${booking.id}" hidden="hidden">
                                            <div class="block">
                                                <input type="submit" class="btn" value="Accept request">
                                            </div>
                                        </form>
                                    </div>
                                </c:when>
                                <c:when test="${booking.bookingStatus.toString() == 'ACTIVE'}">
                                    <div class="registerReturn">
                                        <form action="${pageContext.request.contextPath}/Manager/registerReturn"
                                              method="get" name="registerReturn">
                                            <input type="text" name="bookingId" value="${booking.id}" hidden="hidden">
                                            <input type="text" name="carId" value="${booking.car.carId}" hidden="hidden">
                                            <div class="block">
                                                <input type="submit" class="btn" value="Register return">
                                            </div>
                                        </form>
                                    </div>
                                </c:when>
                            </c:choose>


                            <div class="declineRequest">
                                <form action="${pageContext.request.contextPath}/Manager/declineRequest"
                                  method="post" name="declineRequest">
                                    <input type="text" name="bookingId" value="${booking.id}" hidden="hidden">
                                    <input type="text" name="carId" value="${booking.car.carId}" hidden="hidden">
                                    <div class="block">
                                        <input type="submit" class="btn" value="Decline request">
                                    </div>
                                    <input type="text" name="declineDescription" class="additionalInfo"
                                       placeholder="Reason of declining..." required>
                                </form>
                            </div>
                        </div>
                    </c:when>
                    <c:when test="${booking.bookingStatus == 'FINISHED'}">
                        <div class="details">
                            <h2 class="primaryInfo">This request is finished </h2>
                        </div>
                    </c:when>
                    <c:when test="${booking.bookingStatus == 'DECLINED'}">
                        <div class="details">
                            <h2 class="primaryInfo">This request is declined </h2>
                        </div>
                    </c:when>
                    <c:when test="${booking.bookingStatus == 'TERMINATED'}">
                        <div class="details">
                            <h2 class="primaryInfo">This request is terminated by user </h2>
                        </div>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </c:forEach>

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
