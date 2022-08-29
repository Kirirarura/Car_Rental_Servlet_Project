<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />

<!DOCTYPE>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration page</title>
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

    <div class="container">
        <h1><fmt:message key="manager.registerReturn.title"/> ${sessionScope.bookingId})</h1>
        <form action="${pageContext.request.contextPath}/Manager/registerReturn" method="post">
            <p><fmt:message key="manager.registerReturn.inTime"/></p>
            <div class="filter-form-item">
                <p><input class="filter-checkbox-sort" type="checkbox" name="returnInTime"><fmt:message key="manager.registerReturn.yes"/></p>
            </div>
            <p><fmt:message key="manager.registerReturn.damaged"/></p>
            <div class="filter-form-item">
                <p><input class="filter-checkbox-order" type="checkbox" name="damaged"><fmt:message key="manager.registerReturn.yes"/></p>
            </div>
            <div class="form-control">
                <input type="submit" name="signup" value="<fmt:message key="manager.registerReturn.button"/>" class="btn">
            </div>
        </form>
    </div>
</main>

<footer>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.19/dist/sweetalert2.all.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/messages/managerMessages.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/filter.js"></script>

</footer>

</body>
</html>