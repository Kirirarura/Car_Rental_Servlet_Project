<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/login-register.css">
    <%@ include file="../partial/header.jspf" %>
</head>
<body>
<%@ include file="../partial/navbar.jspf" %>
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
        <input type="submit" class="btn" value="Log in">
        <div class="form-control">
            <p class="text">Don't have an account? <a href="registration.jsp">Register</a></p>
        </div>

    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.19/dist/sweetalert2.all.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/messages.js"></script>
</body>
</html>
