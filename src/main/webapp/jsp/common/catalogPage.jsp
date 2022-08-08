<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Catalog</title>
    <%@ include file="../partial/head.jspf" %>
    <link rel="stylesheet" href="<c:url value="/static/css/catalogPage.css"/>">

</head>

<body>
<header>
    <tf:chooseHeader role="${sessionScope.role}"/>
</header>

<main>
    <h1>Catalog</h1>
    <section>
        <ul>
            <c:forEach var="user" items="${requestScope.userList}">
                <li class="card-item">
                    <article>
                        <img src="<c:url value="/static/img/user.jpg"/>" alt="Car Image"/>
                        <div class="card-item-content">
                            <h2>Cheeseburgers</h2>
                            <p>Price :</p>
                            <a href="">Add to Cart</a>
                        </div>
                    </article>
                </li>
            </c:forEach>
        </ul>
    </section>
</main>

<footer>

</footer>
</body>
</html>
