<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf" %>

<!DOCTYPE>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add New Car</title>
    <link rel="stylesheet" href="<c:url value="/static/css/form.css"/>">
    <%@ include file="/jsp/partial/head.jspf" %>
</head>

<tf:chooseHeader role="${sessionScope.role}"/>

<body>
<div class="container">
    <div class="row text-center">
        <p>Error 403</p>
        <p>Please contact our help center</p>
    </div>
</div>
</body>
</html>
