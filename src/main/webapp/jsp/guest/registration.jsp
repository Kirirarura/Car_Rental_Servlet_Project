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
    <h1>Register</h1>
    <form action="${pageContext.request.contextPath}/registration" method="post">
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
        <input type="submit" name="signup" value="Register" class="btn">
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.19/dist/sweetalert2.all.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/messages.js"></script>
</body>
</html>
