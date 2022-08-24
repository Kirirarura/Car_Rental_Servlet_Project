<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf" %>

<!DOCTYPE>
<html>
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
    <div class="container">
        <h1>Register Manager</h1>
        <form action="${pageContext.request.contextPath}/Admin/registrationManager" method="post">
            <div class="form-control">
                <label>First name
                    <input type="text" placeholder="Type your firstname" name="firstname" required="required">
                </label>
            </div>
            <div class="form-control">
                <label>Second name
                    <input type="text" placeholder="Type your lastname" name="lastname" required="required">
                </label>
            </div>
            <div class="form-control">
                <label>Email
                    <input type="email" placeholder="example@gmail.com" name="email" required="required">
                </label>
            </div>
            <div class="form-control">
                <label>Password
                    <input type="password" placeholder="Type your password" name="password" required="required">
                </label>
            </div>
            <div class="form-control">
                <label>Repeat password
                    <input type="password" placeholder="Repeat your password" name="re-password" required="required">
                </label>
            </div>
            <div class="form-control">
                <input type="submit" name="signup" value="Register" class="btn">
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