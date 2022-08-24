<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf" %>

<!DOCTYPE>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home Page</title>
    <%@ include file="../partial/head.jspf" %>
    <link rel="stylesheet" href="<c:url value="/static/css/homepage.css"/>">
</head>
<body>
    <header>
        <tf:chooseHeader role="${sessionScope.role}"/>
    </header>

    <main>
        <section id="hero">
            <div id="hero-content">
                <h2>Need a car?</h2>
                <h2>Need a driver?</h2>
                <p>We have everything you need</p>
                <a href="${pageContext.request.contextPath}/catalog">Discover our huge catalog</a>
            </div>
        </section>
    </main>
    <footer>

    </footer>
</body>
</html>