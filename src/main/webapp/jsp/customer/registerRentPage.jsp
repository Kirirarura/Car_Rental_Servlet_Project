<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE>
<html>
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

    <c:set var="car" value="${sessionScope.car}"/>
    <div class="container">
        <h1><fmt:message key="customer.registerRent.title"/></h1>
        <p><fmt:message key="customer.registerRent.car"/> <c:out value="${car.brand} ${car.modelName}"/>,
            <fmt:message key="customer.registerRent.price"/> <c:out value="${car.price}"/>)</p>
        <form action="${pageContext.request.contextPath}/Customer/rent" method="post">
            <input name="carPrice" value="${car.price}" type="hidden">
            <div class="form-control">
                <label><fmt:message key="customer.registerRent.passport"/>
                    <input type="text" placeholder="<fmt:message key="customer.registerRent.passport.placeHolder"/> "
                           name="passportData" required="required">
                </label>
            </div>
            <p><fmt:message key="customer.registerRent.driver"/></p>
            <p><input class="filter-checkbox-order" type="checkbox" name="withDriver"> <fmt:message
                    key="customer.registerRent.driverCheck"/></p>
            <div class="form-control">
                <label><fmt:message key="customer.registerRent.rentFrom"/>
                    <input autocomplete="off" id="startDateField" type="text"
                           placeholder="<fmt:message key="customer.registerRent.enterDate"/> " name="startDate"
                           required="required" oninput="">
                </label>
            </div>
            <div class="form-control">
                <label><fmt:message key="customer.registerRent.rentTo"/>
                    <input autocomplete="off" id="endDateField" type="text"
                           placeholder="<fmt:message key="customer.registerRent.enterDate"/> " name="endDate"
                           required="required">
                </label>
            </div>
            <div class="form-control">
                <input type="submit" name="signup" value="<fmt:message key="customer.registerRent.button"/> "
                       class="btn">
            </div>
        </form>
    </div>
</main>

<footer>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/js/datePicker.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.19/dist/sweetalert2.all.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/js/messages/customerMessages.js"></script>
</footer>

</body>
</html>
