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
    <title>Login page</title>
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
            <h1><fmt:message key="loginPage.title"/></h1>
            <form action="${pageContext.request.contextPath}/login" method="post">
                <div class="form-control">
                    <label><fmt:message key="loginPage.email"/>
                        <input type="text" placeholder="<fmt:message key="loginPage.email.placeHolder"/>"
                               name="email" required="required">
                    </label>
                </div>
                <div class="form-control">
                    <label><fmt:message key="loginPage.password"/>
                        <input type="password" placeholder="<fmt:message key="loginPage.password.placeHolder"/>"
                               name="password" required="required">
                    </label>
                </div>
                <div class="form-control">
                    <input type="submit" class="btn" value="<fmt:message key="loginPage.button"/>">
                </div>
                <div class="form-control">
                    <p class="text"><fmt:message key="loginPage.additionalText"/>
                        <a href="registrationPage.jsp"><fmt:message key="loginPage.register"/></a>
                    </p>
                </div>
            </form>
        </div>
    </main>

    <footer>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.19/dist/sweetalert2.all.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/messages/login&RegisterMessages.js"></script>
    </footer>


</body>
</html>
