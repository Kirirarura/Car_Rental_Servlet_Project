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
    <title>Add New Car</title>
    <link rel="stylesheet" href="<c:url value="/static/css/form.css"/>">
    <%@ include file="/jsp/partial/head.jspf" %>
</head>
<body>
<tf:chooseHeader role="${sessionScope.role}"/>

<div class="container">
    <div class="row text-center">
        <p><fmt:message key="manager.error.title"/> </p>
        <a href="${pageContext.request.contextPath}/Manager/allRequests"> <fmt:message key="manager.error.back"/></a>
    </div>
</div>
</body>
</html>
