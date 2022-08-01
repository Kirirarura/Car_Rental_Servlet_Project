<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registered Users</title>
    <%@ include file="../partial/head.jspf" %>
    <link rel="stylesheet" href="<c:url value="/static/css/userList.css"/>">
</head>

<body>
    <header>
        <tf:chooseHeader role="${sessionScope.role}"/>
    </header>

    <main>
        <div class="list">
            <c:forEach var="user" items="${requestScope.userList}">
                <div class="line">
                    <div class="user">
                        <div class="profile">
                            <img src="${pageContext.request.contextPath}/static/img/user.jpg" alt="user profile picture">
                        </div>
                        <div class="details">
                            <h1 class="name"><c:out value="${user.firstName} ${user.lastName}"/></h1>
                            <h3 class="email"><c:out value="${user.email}"/></h3>

                        </div>
                    </div>
                    <div class="status-container">
                        <div class="status">
                            <c:if test="${!user.blocked}">
                                <p>User not blocked</p>
                            </c:if>
                            <c:if test="${user.blocked}">
                                <p>User blocked</p>
                            </c:if>
                        </div>
                        <div class="block">
                            <a href="#" class="btn">Block/Unblock</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </main>

    <footer>

    </footer>
</body>
</html>
