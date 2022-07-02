<nav>
    <ul class="menu">
        <li class="logo"><a href="index.jsp">Car Rental</a></li>
        <li class="item"><a href="index.jsp">Home</a></li>
        <li class="item"><a href="#">Order</a></li>
        <% if (session.getAttribute("auth") == null) {%>
        <li class="item button"><a href="login.jsp">Login</a></li>
        <li class="item button secondary "><a href="registration.jsp">Sigh Up</a></li>
        <%}%>
        <% if (session.getAttribute("auth") != null) {%>
        <li class="item"><a href="#"><%=session.getAttribute("name")%>
        </a></li>
        <li class="item"><a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a></li>
        <%}%>

        <li class="toggle"><span class="bars"></span></li>
    </ul>
</nav>