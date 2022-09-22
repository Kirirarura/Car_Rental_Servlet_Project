<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages" />

<!DOCTYPE>
<html lang="en">
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
                <h2><fmt:message key="index.firstTitle"/> </h2>
                <h2><fmt:message key="index.secondTitle"/></h2>
                <p><fmt:message key="index.text"/></p>
                <c:choose>
                    <c:when test="${sessionScope.role == 'MANAGER'}">
                        <a href="${pageContext.request.contextPath}/Manager/allRequests"><fmt:message key="index.link"/></a>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/catalog"><fmt:message key="index.link"/></a>
                    </c:otherwise>
                </c:choose>
            </div>
        </section>
    </main>
    <footer class="footer">
        <%@ include file="../partial/footer.jspf" %>
    </footer>
</body>
</html>