<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register rent page</title>
    <link rel="stylesheet" href="<c:url value="/static/css/form.css"/>">
    <%@ include file="../partial/head.jspf" %>
</head>
<body>
<header>
    <tf:chooseHeader role="${sessionScope.role}"/>
</header>

<main>
    <input type="hidden" id="status" value="<%= request.getAttribute("status")%>">
    <input type="hidden" id="lang" value="<%= session.getAttribute("lang")%>">

    <c:set var="booking" value="${sessionScope.bookingDto}"/>
    <div class="container">
        <h1><fmt:message key="customer.registerRent.title"/></h1>
        <h1><fmt:message key="customer.registerRent.pleaseCheck"/></h1>

        <form action="${pageContext.request.contextPath}/Customer/rent" method="post">
            <p><fmt:message key="customer.registerRent.car"/> <c:out value="${booking.car.brand} ${booking.car.modelName}"/>)</p>
            <p><fmt:message key="customer.registerRent.passport"/> <c:out value="${booking.userDetails}"/></p>
            <c:choose>
                <c:when test="${booking.withDriver}">
                    <p><fmt:message key="manager.myRequests.withDriver"/></p>
                </c:when>
                <c:otherwise>
                    <p><fmt:message key="manager.myRequests.withoutDriver"/></p>
                </c:otherwise>
            </c:choose>
            <p><fmt:message key="customer.registerRent.price"/> <c:out value="${booking.price}"/></p>
            <div class="form-control">
                <input type="submit" name="signup" value="<fmt:message key="customer.registerRent.button"/> "
                       class="btn">
            </div>
            <a href="${pageContext.request.contextPath}/catalog"><fmt:message key="cancelButton"/></a>
        </form>
    </div>
</main>
<footer class="footer">
    <%@ include file="../partial/footer.jspf" %>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.19/dist/sweetalert2.all.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/js/messages/customerMessages.js"></script>
</footer>
</body>
</html>