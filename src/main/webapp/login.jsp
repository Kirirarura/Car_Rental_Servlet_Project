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
  <h1>Log In</h1>
  <form action="${pageContext.request.contextPath}/LoginServlet" method="post">
    <div class="form-control">
      <label>Email
      <input type="text" placeholder="Type your email" name="email" required>
      </label>
    </div>
    <div class="form-control">
      <label>Password
      <input type="password" placeholder="Type your password" name="password" required>
      </label>
    </div>
    <input type="submit" class="btn" value="Log in">
    <p class="text">Don't have an account?<a href="registration.jsp"> Register</a></p>
  </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.19/dist/sweetalert2.all.min.js"></script>
<script>
  const status = document.getElementById("status").value;
  if (status === "failedLogin"){
    Swal.fire({
      icon: 'error',
      title: 'Email or Login are wrong',
      showConfirmButton: true
    })
  } else if (status === "successRegistration"){
    Swal.fire({
      icon: 'success',
      title: 'Your account registered',
      showConfirmButton: false,
      timer: 2000
    })
  }
</script>
</body>
</html>
