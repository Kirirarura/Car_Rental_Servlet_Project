<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registered Users</title>
    <%@ include file="../partial/head.jspf" %>
    <link rel="stylesheet" href="<c:url value="/static/css/listPage.css"/>">
    <link rel="stylesheet" href="<c:url value="/static/css/pagination.css"/>">

</head>

<body>
<header>
    <tf:chooseHeader role="${sessionScope.role}"/>
</header>

<main>
    <input type="hidden" id="status" value="<%= request.getAttribute("status")%>">
    <input type="hidden" id="lang" value="<%= session.getAttribute("lang")%>">

    <h1><fmt:message key="userManagement.title"/></h1>
    <div class="card-content">
        <c:forEach var="user" items="${requestScope.userList}">
            <div class="card-item">
                <div class="user">
                    <div class="profile">
                        <img src="<c:url value="/static/img/user.jpg"/>" alt="user profile picture">
                    </div>
                    <div class="details">
                        <h2 class="primaryInfo"><c:out value="${user.firstName} ${user.lastName}"/></h2>
                        <h3 class="secondaryInfo"><c:out value="${user.email}"/></h3>

                    </div>
                </div>

                <form action="${pageContext.request.contextPath}/Admin/blockUnblock"
                      method="post" name="blockUnblock">
                    <input type="text" name="id" value="${user.id}" hidden="hidden">
                    <input type="text" name="isBlocked" value="${user.blocked}" hidden="hidden">
                    <div class="status-container">
                        <div class="status">
                            <c:if test="${!user.blocked}">
                                <p><fmt:message key="userManagement.userNotBlocked"/></p>
                            </c:if>
                            <c:if test="${user.blocked}">
                                <p><fmt:message key="userManagement.userBlocked"/></p>
                            </c:if>
                        </div>
                        <div class="block">
                            <input type="submit" class="btn" value="<fmt:message key="userManagement.button"/>">
                        </div>
                    </div>
                </form>
            </div>
        </c:forEach>
    </div>


    <c:choose>
        <c:when test="${empty requestScope.userList}">
            <div class="no-results">
                <h2><fmt:message key="noResults"/></h2>
            </div>
        </c:when>
        <c:otherwise>
            <div class="pagination"></div>
        </c:otherwise>
    </c:choose>
</main>

<footer class="footer">
    <%@ include file="../partial/footer.jspf" %>

    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/paginationClientSide.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.19/dist/sweetalert2.all.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/js/messages/infoLoadingMessages.js"></script>
</footer>
</body>
</html>
