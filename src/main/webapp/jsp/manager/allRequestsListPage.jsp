<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />

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

    <h1><fmt:message key="manager.allRequests.title"/> </h1>
    <div class="card-content">
        <c:forEach var="booking" items="${requestScope.bookingList}">
            <div class="card-item">
                <div class="container">
                    <div class="details">
                        <h2 class="primaryInfo">Id:<c:out value="${booking.id} "/></h2>
                        <h3 class="secondaryInfo"><c:out value="${booking.startDate} - ${booking.endDate}"/></h3>
                    </div>
                    <div class="details">
                        <h2 class="primaryInfo"><fmt:message key="manager.allRequests.name"/> <c:out value="${booking.user.firstName} "/></h2>
                        <h3 class="secondaryInfo"><fmt:message key="manager.allRequests.passport"/> <c:out value="${booking.userDetails} "/></h3>
                    </div>
                    <div class="details">
                        <h2 class="primaryInfo"><fmt:message key="manager.allRequests.car"/> <c:out value="${booking.car.brand} ${booking.car.modelName}"/></h2>
                    </div>
                    <div class="details">
                        <h2 class="primaryInfo"><fmt:message key="manager.allRequests.price"/> <c:out value="${booking.price}"/></h2>
                        <c:choose>
                            <c:when test="${booking.withDriver}">
                                <h3 class="secondaryInfo"><fmt:message key="manager.allRequests.withDriver"/></h3>
                            </c:when>
                            <c:when test="${!booking.withDriver}">
                                <h3 class="secondaryInfo"><fmt:message key="manager.allRequests.withoutDriver"/></h3>
                            </c:when>
                        </c:choose>
                    </div>
                </div>

                <div class="details">
                    <form action="${pageContext.request.contextPath}/Manager/onReview"
                          method="post" name="blockUnblock">
                        <input type="text" name="id" value="${booking.id}" hidden="hidden">
                            <div class="block">
                                <input type="submit" class="btn" value="<fmt:message key="manager.allRequests.takeOnReview"/>">
                            </div>
                    </form>
                </div>

            </div>
        </c:forEach>
    </div>

    <div class="pagination">
    </div>
</main>

<footer>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/pagination.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.19/dist/sweetalert2.all.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/messages/managerMessages.js"></script>
</footer>
</body>
</html>
