<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login page</title>
    <link rel="stylesheet" href="css/login-register.css">
    <%@ include file="include/header.jsp"%>
</head>
<body>
<%@ include file="include/navbar.jsp"%>

<input type="hidden" id="status" value="<%= request.getAttribute("status")%>">
<div class="container">
    <h1>Register</h1>
    <form action="${pageContext.request.contextPath}/RegisterServlet" method="post">
        <div class="form-control">
            <label>First name
            <input type="text" placeholder="Type your first name" name="firstname" required>
            </label>
        </div>
        <div class="form-control">
            <label>Second name
            <input type="text" placeholder="Type your second name" name="secondname" required>
            </label>
        </div>
        <div class="form-control">
            <label>Email
            <input type="email" placeholder="example@gmail.com" name="email" required>
            </label>
        </div>
        <div class="form-control">
            <label>Password
            <input type="password" placeholder="Type your password" name="password" required>
            </label>
        </div>
        <div class="form-control">
            <label>Repeat password
                <input type="password" placeholder="Repeat your password" name="re-password" required>
            </label>
        </div>
        <input type="submit" name="signup" value="Register" class="btn">
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.19/dist/sweetalert2.all.min.js"></script>
<script>
    const status = document.getElementById("status").value;
    if (status === "emailDuplicate"){
        Swal.fire({
            icon: 'error',
            title: 'Such email already used',
            showConfirmButton: false,
            timer: 2000
        })
    } else if (status === "failRegistration"){
        Swal.fire({
            icon: 'error',
            title: 'Registration failed',
            showConfirmButton: false,
            timer: 2000
        })
    }
</script>
</body>
</html>
