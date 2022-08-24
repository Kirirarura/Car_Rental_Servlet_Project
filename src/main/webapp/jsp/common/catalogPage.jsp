<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf" %>
<!DOCTYPE>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Catalog</title>
    <%@ include file="../partial/head.jspf" %>
    <link rel="stylesheet" href="<c:url value="/static/css/catalogPage.css"/>">
    <link rel="stylesheet" href="<c:url value="/static/css/pagination.css"/>">
    <link rel="stylesheet" href="<c:url value="/static/css/filter.css"/>">

</head>

<body>
<header>
    <tf:chooseHeader role="${sessionScope.role}"/>
</header>
<main>
    <input type="hidden" id="status" value="<%= request.getAttribute("status")%>">
    <c:set var="brandList" value="${sessionScope.brandList}"/>
    <c:set var="qualityClassList" value="${sessionScope.qualityClassList}"/>

    <h1>Catalog</h1>
    <form class="filter-form" method="get" action="${pageContext.request.contextPath}/catalog">
        <div class="filter-form-item">
            <p>Sort by</p>
            <p><input class="filter-checkbox-sort" type="checkbox" name="price"> Price</p>
            <p><input class="filter-checkbox-sort" type="checkbox" name="name"> Name</p>
        </div>
        <div class="filter-form-item">
            <p>Order</p>
            <p><input class="filter-checkbox-order" type="checkbox" name="asc"> Ascending</p>
            <p><input class="filter-checkbox-order" type="checkbox" name="desc"> Descending</p>
        </div>
        <div class="filter-form-item">
            <label><input type="checkbox" name="searchOneBrand"/> Search Brand</label>

                <select name="brandValue">
                    <c:forEach items="${brandList}" var="brand" varStatus="loop">
                        <option value="${brand.value}">
                                ${brand.value}
                        </option>
                    </c:forEach>
                </select>
        </div>
        <div class="filter-form-item">
            <label><input type="checkbox" name="searchOneQuality"> Search Quality</label>
                <select name="qualityValue">
                    <c:forEach items="${qualityClassList}" var="quality" varStatus="loop">
                        <option value="${quality.value}">
                                ${quality.value}
                        </option>
                    </c:forEach>
                </select>
        </div>
        <input type="submit" class="head-btn" value="Search">

        <c:if test="${sessionScope.role=='ADMIN'}">
            <a class="head-btn" href="${pageContext.request.contextPath}/Admin/addNewCar">Add New Car</a>
        </c:if>

    </form>

    <section>
        <ul class="card-content" id="card-content">
            <c:forEach var="car" items="${requestScope.carList}">
                <li class="card-item" id="card-item">

                    <img src="<c:url value="/static/img/car.jpg"/>" alt="Car Image"/>
                    <div class="card-item-content">
                        <h2 class="title"><c:out value="${car.brand} ${car.modelName}"/></h2>
                        <p>Price: <c:out value="${car.price}"/>$/per day</p>
                        <p class="quality">Quality: <c:out value="${car.qualityClass}"/></p>
                        <c:if test="${sessionScope.role=='ADMIN'}">
                            <p><c:out value="${car.status}"/></p>
                        </c:if>
                        <p><c:out value="${car.description}"/></p>


                        <c:choose>
                            <c:when test="${sessionScope.role=='ADMIN'}">
                                <form action="${pageContext.request.contextPath}/Admin/editCar" method="get"
                                      name="edit">
                                    <input type="text" name="id" value="${car.carId}" hidden>
                                    <input type="submit" class="btn" value="Edit">
                                </form>
                                <form action="${pageContext.request.contextPath}/Admin/deleteCar" method="post"
                                      name="edit">
                                    <input type="text" name="id" value="${car.carId}" hidden>
                                    <input type="submit" class="delete-btn" value="Delete">
                                </form>
                            </c:when>
                            <c:otherwise>
                                <form action="${pageContext.request.contextPath}/Customer/rent" method="get"
                                      name="rent">
                                    <input type="text" name="id" value="${car.carId}" hidden>
                                    <input type="submit" class="btn" value="Rent">
                                </form>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </section>

    <div class="pagination">
    </div>
</main>

<footer>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/pagination.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.19/dist/sweetalert2.all.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/js/messages/infoLoadingMessages.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/js/messages/registerRentMessages.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/filter.js"></script>
</footer>
</body>
</html>
