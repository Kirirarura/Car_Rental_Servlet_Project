<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

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
    <input type="hidden" id="lang" value="<%= session.getAttribute("lang")%>">

    <h1><fmt:message key="manager.myRequests.title"/></h1>
    <div class="card-content">
        <c:forEach var="booking" items="${sessionScope.bookingList}">
        <div class="card-item">
            <div class="container">
                <div class="details">
                    <h2 class="primaryInfo"><fmt:message key="manager.myRequests.requestId"/> <c:out
                            value="${booking.id} "/></h2>
                    <h3 class="secondaryInfo"><fmt:message key="manager.myRequests.car"/> <c:out
                            value="${booking.car.brand} ${booking.car.modelName}"/></h3>
                </div>
                <div class="details">
                    <h2 class="primaryInfo"><fmt:message key="manager.myRequests.period"/></h2>
                    <h3 class="secondaryInfo"><c:out value="${booking.startDate} - ${booking.endDate}"/></h3>
                </div>
                <div class="details">
                    <h2 class="primaryInfo"><fmt:message key="manager.myRequests.name"/> <c:out
                            value="${booking.user.firstName} ${booking.user.lastName}"/></h2>
                    <h3 class="secondaryInfo"><fmt:message key="manager.myRequests.passport"/> <c:out
                            value="${booking.userDetails} "/></h3>

                </div>
                <div class="details">
                    <h2 class="primaryInfo"><fmt:message key="manager.myRequests.price"/> <c:out
                            value="${booking.price}"/></h2>
                    <c:choose>
                        <c:when test="${booking.withDriver}">
                            <h3 class="secondaryInfo"><fmt:message key="manager.myRequests.withDriver"/></h3>
                        </c:when>
                        <c:when test="${!booking.withDriver}">
                            <h3 class="secondaryInfo"><fmt:message key="manager.myRequests.withoutDriver"/></h3>
                        </c:when>
                    </c:choose>
                </div>
            </div>
            <c:choose>
                <c:when test="${booking.bookingStatus == 'ON_REVIEW' || booking.bookingStatus == 'ACTIVE'}">
                    <div class="responses">
                        <div class="details">
                            <h2 class="primaryInfo"><fmt:message key="manager.myRequests.status"/></h2>
                            <h3 class="secondaryInfo"><c:out value="${booking.bookingStatus}"/></h3>
                        </div>
                        <c:choose>
                            <c:when test="${booking.bookingStatus.toString() == 'ON_REVIEW'}">
                                <div class="acceptRequest">
                                    <form action="${pageContext.request.contextPath}/Manager/acceptRequest"
                                          method="post" name="acceptRequest">
                                        <input type="text" name="id" value="${booking.id}" hidden="hidden">
                                        <div class="block">
                                            <input type="submit" class="btn"
                                                   value="<fmt:message key="manager.myRequests.accept"/>">
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
                                            <input type="submit" class="btn"
                                                   value="<fmt:message key="manager.myRequests.return"/>">
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
                                    <input type="submit" class="btn"
                                           value="<fmt:message key="manager.myRequests.decline"/>">
                                </div>
                                <input type="text" name="declineDescription" class="additionalInfo"
                                       placeholder="<fmt:message key="manager.myRequests.decline.placeHolder"/>"
                                       required>
                            </form>
                        </div>
                    </div>
                </c:when>
                <c:when test="${booking.bookingStatus == 'FINISHED'}">
                    <div class="details">
                        <h2 class="primaryInfo"><fmt:message key="manager.myRequests.finished"/></h2>
                    </div>
                </c:when>
                <c:when test="${booking.bookingStatus == 'DECLINED'}">
                    <div class="details">
                        <h2 class="primaryInfo"><fmt:message key="manager.myRequests.declined"/></h2>
                    </div>
                </c:when>
                <c:when test="${booking.bookingStatus == 'TERMINATED'}">
                    <div class="details">
                        <h2 class="primaryInfo"><fmt:message key="manager.myRequests.terminated"/></h2>
                    </div>
                </c:when>
            </c:choose>
            <c:if test="${booking.additionalFee.unscaledValue() != 0 && booking.bookingStatus == 'FINISHED'}">
                <div class="details">
                    <h2 class="primaryInfo"><fmt:message key="manager.myRequests.fee"/> ${booking.additionalFee}</h2>
                </div>
            </c:if>
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
            src="${pageContext.request.contextPath}/static/js/messages/managerMessages.js"></script>

</footer>
</body>
</html>
