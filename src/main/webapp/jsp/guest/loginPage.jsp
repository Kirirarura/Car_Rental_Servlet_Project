<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf" %>

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

        <div class="container">
            <h1>Log In</h1>
            <form action="${pageContext.request.contextPath}/login" method="post">
                <div class="form-control">
                    <label>Email
                        <input type="text" placeholder="Type your email" name="email" required="required">
                    </label>
                </div>
                <div class="form-control">
                    <label>Password
                        <input type="password" placeholder="Type your password" name="password" required="required">
                    </label>
                </div>
                <div class="form-control">
                    <input type="submit" class="btn" value="Log in">
                </div>
                <div class="form-control">
                    <p class="text">Don't have an account? <a href="registrationPage.jsp">Register</a></p>
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
