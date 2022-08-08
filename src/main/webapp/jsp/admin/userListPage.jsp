<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registered Users</title>
    <%@ include file="../partial/head.jspf"%>
    <link rel="stylesheet" href="<c:url value="/static/css/userListPage.css"/>">
    <link rel="stylesheet" href="<c:url value="/static/css/pagination.css"/>">

</head>

<body>
    <header>
        <tf:chooseHeader role="${sessionScope.role}"/>
    </header>

    <main>
        <div class="card-content">
            <c:forEach var="user" items="${requestScope.userList}">
                <div class="card-item">
                    <div class="user">
                        <div class="profile">
                            <img src="<c:url value="/static/img/user.jpg"/>" alt="user profile picture">
                        </div>
                        <div class="details">
                            <h1 class="name"><c:out value="${user.firstName} ${user.lastName}"/></h1>
                            <h3 class="email"><c:out value="${user.email}"/></h3>

                        </div>
                    </div>
                    <form action="${pageContext.request.contextPath}/Admin/blockUnblock"
                          method="post" name="blockUnblock">
                        <div class="status-container">
                            <div class="status">
                                <input type="text" name="id" value="${user.id}" hidden>
                                <input type="text" name="isBlocked" value="${user.blocked}" hidden>
                                <c:if test="${!user.blocked}">
                                    <p>User not blocked</p>
                                </c:if>
                                <c:if test="${user.blocked}">
                                    <p>User blocked</p>
                                </c:if>
                            </div>
                                <div class="block">
                                    <input type="submit" class="btn" value="Block/Unblock">
                                </div>
                        </div>
                    </form>
                </div>
            </c:forEach>
        </div>

        <div class="pagination">
        </div>
    </main>

    <footer>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/pagination.js"></script>
    </footer>
</body>
</html>
