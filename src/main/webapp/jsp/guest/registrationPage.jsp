<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />

<!DOCTYPE>
<html lang="en">
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
        <h1><fmt:message key="registerPage.title"/> </h1>
        <form action="${pageContext.request.contextPath}/registration" method="post">
            <div class="form-control">
                <label><fmt:message key="registerPage.firstName"/>
                    <input type="text" placeholder="<fmt:message key="registerPage.firstName.placeHolder"/>" name="firstname" required="required">
                </label>
            </div>
            <div class="form-control">
                <label><fmt:message key="registerPage.lastName"/>
                    <input type="text" placeholder="<fmt:message key="registerPage.lastName.placeHolder"/>" name="lastname" required="required">
                </label>
            </div>
            <div class="form-control">
                <label><fmt:message key="registerPage.email"/>
                    <input type="email" placeholder="example@gmail.com" name="email" required="required">
                </label>
            </div>
            <div class="form-control">
                <label><fmt:message key="registerPage.password"/>
                    <input type="password" placeholder="<fmt:message key="registerPage.password.placeHolder"/>" name="password" required="required">
                </label>
            </div>
            <div class="form-control">
                <label><fmt:message key="registerPage.repassword"/>
                    <input type="password" placeholder="<fmt:message key="registerPage.repassword.placeHolder"/>" name="re-password" required="required">
                </label>
            </div>
            <div class="form-control">
                <input type="submit" name="signup" value="<fmt:message key="registerPage.button"/>" class="btn">
            </div>
        </form>
    </div>
</main>

<footer class="footer">
    <%@ include file="../partial/footer.jspf" %>

    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.19/dist/sweetalert2.all.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/js/messages/login&RegisterMessages.js"></script>
</footer>

</body>
</html>
