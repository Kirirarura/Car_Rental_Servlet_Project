<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf" %>

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home Page</title>
    <%@ include file="../partial/head.jspf" %>
</head>
<body>
    <header>
        <tf:chooseHeader role="${sessionScope.role}"/>
    </header>

    <h1>This is home page</h1>
</body>
</html>