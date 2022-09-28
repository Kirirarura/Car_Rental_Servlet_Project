<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE>
<html lang="en">
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

    <h1><fmt:message key="customer.myRequests.title"/></h1>
    <div class="card-content">
        <c:forEach var="booking" items="${requestScope.bookingList}">
            <div class="card-item">
                <div class="container">
                    <div class="details">
                        <h2 class="primaryInfo"><fmt:message key="customer.myRequests.car"/> <c:out
                                value="${booking.car.brand} ${booking.car.modelName}"/></h2>
                        <h3 class="secondaryInfo"><c:out value="${booking.startDate} - ${booking.endDate}"/></h3>
                    </div>
                    <div class="details">
                        <h2 class="primaryInfo"><fmt:message key="customer.myRequests.passport"/> <c:out
                                value="${booking.userDetails} "/></h2>
                        <h3 class="secondaryInfo"><fmt:message key="customer.myRequests.price"/> <c:out
                                value="${booking.price}"/></h3>
                    </div>
                    <div class="details">
                        <h2 class="primaryInfo"><fmt:message key="customer.myRequests.status"/>
                            <c:choose>
                                <c:when test="${booking.bookingStatus == 'PENDING_REVIEW'}">
                                    <fmt:message key="status.pendingReview"/>
                                </c:when>
                                <c:when test="${booking.bookingStatus == 'ON_REVIEW'}">
                                    <fmt:message key="status.onReview"/>
                                </c:when>
                                <c:when test="${booking.bookingStatus == 'ACTIVE'}">
                                    <fmt:message key="status.active"/>
                                </c:when>
                                <c:when test="${booking.bookingStatus == 'TERMINATED'}">
                                    <fmt:message key="status.terminated"/>
                                </c:when>
                                <c:when test="${booking.bookingStatus == 'DECLINED'}">
                                    <fmt:message key="status.declined"/>
                                </c:when>
                                <c:when test="${booking.bookingStatus == 'FINISHED'}">
                                    <fmt:message key="status.finished"/>
                                </c:when>
                            </c:choose>
                        </h2>
                    </div>
                    <c:if test="${booking.bookingStatus == 'DECLINED'}">
                        <div class="details">
                            <h2 class="primaryInfo"><fmt:message key="customer.myRequests.declineInfo"/> <c:out
                                    value="${booking.declineInfo}"/></h2>
                        </div>
                    </c:if>
                    <c:if test="${booking.bookingStatus == 'FINISHED'}">
                        <c:if test="${booking.additionalFee.unscaledValue() != 0}">
                            <form action="${pageContext.request.contextPath}/Customer/additionalPayment"
                                  method="post"
                                  name="additionalPayment">
                                <input type="text" name="bookingId" value="${booking.id}" hidden="hidden">
                                <h2 class="primaryInfo"><fmt:message key="customer.myRequests.additionalFee"/>
                                    <c:out value="${booking.additionalFee}"/>$</h2>
                                <div class="block">
                                    <input type="submit" class="btn"
                                           value="<fmt:message key="customer.myRequests.payButton"/>">
                                </div>
                            </form>
                        </c:if>
                        <c:if test="${booking.additionalFee.unscaledValue() == 0}">
                            <h2 class="primaryInfo"><fmt:message key="customer.myRequests.everythingPaid"/></h2>
                        </c:if>
                    </c:if>

                </div>
                <c:if test="${booking.bookingStatus == 'PENDING_REVIEW' || booking.bookingStatus == 'ON_REVIEW'}">
                    <form action="${pageContext.request.contextPath}/Customer/terminate" method="post" name="terminate">
                        <input type="text" name="bookingId" value="${booking.id}" hidden="hidden">
                        <input type="text" name="carId" value="${booking.car.carId}" hidden="hidden">
                        <div class="block">
                            <input type="submit" class="btn"
                                   value="<fmt:message key="cancelButton"/>">
                        </div>
                    </form>
                </c:if>
            </div>
        </c:forEach>
    </div>

    <c:choose>
        <c:when test="${empty requestScope.bookingList}">
            <div class="no-results">
                <h2><fmt:message key="noResults"/></h2>
            </div>
        </c:when>
        <c:otherwise>
            <div class="pagination"></div>
        </c:otherwise>
    </c:choose>
</main>

<footer class="footer">
    <%@ include file="../partial/footer.jspf" %>

    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/paginationClientSide.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.19/dist/sweetalert2.all.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/js/messages/customerMessages.js"></script>
</footer>
</body>
</html>
