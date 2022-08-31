<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf" %>

<!DOCTYPE>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>500 Error Page</title>
    <link rel="stylesheet" href="<c:url value="/static/css/error.css"/>">
    <%@ include file="/jsp/partial/head.jspf" %>
</head>
<body>
<tf:chooseHeader role="${sessionScope.role}"/>


<div class="error-container">
    <h2 class="error-name">Error 500</h2>
    <p class="error text">Internal Server Error</p>
    <a class="home-button" href="${pageContext.request.contextPath}/index">Home</a>
</div>
</body>
</html>