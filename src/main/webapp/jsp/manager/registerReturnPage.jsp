<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf" %>

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
    <div class="container">
        <h1>Register return (ID: ${sessionScope.bookingId})</h1>
        <form action="${pageContext.request.contextPath}/Manager/registerReturn" method="post">
            <p>Returned in time?</p>
            <div class="filter-form-item">
                <p><input class="filter-checkbox-sort" type="checkbox" name="returnInTime">Yes</p>
            </div>
            <p>Damaged?</p>
            <div class="filter-form-item">
                <p><input class="filter-checkbox-order" type="checkbox" name="damaged">Yes</p>
            </div>
            <div class="form-control">
                <input type="submit" name="signup" value="Register" class="btn">
            </div>
        </form>
    </div>
</main>

<footer>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.19/dist/sweetalert2.all.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/messages/login&RegisterMessages.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/filter.js"></script>

</footer>

</body>
</html>