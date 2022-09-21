<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/tld/myTagLib.tld" prefix="myTg" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE>
<html lang="en">
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
    <input type="hidden" id="lang" value="<%= session.getAttribute("lang")%>">

    <c:set var="brandList" value="${sessionScope.brandList}"/>
    <c:set var="qualityClassList" value="${sessionScope.qualityClassList}"/>

    <h1><fmt:message key="catalog.title"/></h1>
    <form class="filter-form" method="get" action="${pageContext.request.contextPath}/catalog">
        <div class="filter-form-item">
            <p><fmt:message key="catalog.sortBy"/></p>
            <p><input class="filter-checkbox-sort" type="checkbox" name="price"> <fmt:message
                    key="catalog.sortBy.price"/></p>
            <p><input class="filter-checkbox-sort" type="checkbox" name="name"> <fmt:message
                    key="catalog.sortBy.name"/>
            </p>
        </div>
        <div class="filter-form-item">
            <p><fmt:message key="catalog.order"/></p>
            <p><input class="filter-checkbox-order" type="checkbox" name="ASC"> <fmt:message key="catalog.order.asc"/>
            </p>
            <p><input class="filter-checkbox-order" type="checkbox" name="DESC"> <fmt:message key="catalog.order.desc"/>
            </p>
        </div>
        <div class="filter-form-item">
            <label><input type="checkbox" name="searchOneBrand"/> <fmt:message key="catalog.search.brand"/></label>

            <select name="brandValue">
                <c:forEach items="${brandList}" var="brand" varStatus="loop">
                    <option value="${brand.value}">
                            ${brand.value}
                    </option>
                </c:forEach>
            </select>
        </div>
        <div class="filter-form-item">
            <label><input type="checkbox" name="searchOneQuality"> <fmt:message key="catalog.search.quality"/></label>
            <select name="qualityValue">
                <c:forEach items="${qualityClassList}" var="quality" varStatus="loop">
                    <option value="${quality.value}">
                        <myTg:stars carQuality="${quality.value}"/>
                    </option>
                </c:forEach>
            </select>
        </div>
        <input type="submit" class="head-btn" value="<fmt:message key="catalog.search.button"/>">

        <c:if test="${sessionScope.role=='ADMIN'}">
            <a class="head-btn" href="${pageContext.request.contextPath}/Admin/addNewCar"><fmt:message
                    key="catalog.addNewCar.button"/></a>
        </c:if>

    </form>

    <section>
        <ul class="card-content" id="card-content">
            <c:forEach var="car" items="${requestScope.carList}">
                <li class="card-item" id="card-item">
                    <c:choose>
                        <c:when test="${car.brand == 'Nissan'}">
                            <img src="<c:url value="/static/img/nissan.jpg"/>" alt="Car Image"/>
                        </c:when>
                        <c:when test="${car.brand == 'Hyundai'}">
                            <img src="<c:url value="/static/img/hyundai.jpg"/>" alt="Car Image"/>
                        </c:when>
                        <c:when test="${car.brand == 'Honda'}">
                            <img src="<c:url value="/static/img/honda.jpg"/>" alt="Car Image"/>
                        </c:when>
                        <c:when test="${car.brand == 'Tesla'}">
                            <img src="<c:url value="/static/img/tesla.jpg"/>" alt="Car Image"/>
                        </c:when>
                        <c:when test="${car.brand == 'BMW'}">
                            <img src="<c:url value="/static/img/bmw.jpg"/>" alt="Car Image"/>
                        </c:when>
                        <c:when test="${car.brand == 'Audi'}">
                            <img src="<c:url value="/static/img/audi.jpg"/>" alt="Car Image"/>
                        </c:when>
                    </c:choose>
                    <div class="card-item-content">
                        <h2 class="title"><c:out value="${car.brand} ${car.modelName}"/></h2>
                        <p><fmt:message key="catalog.price"/>
                            <c:out value="${car.price}"/>$/<fmt:message key="catalog.perDay"/></p>
                        <p class="quality"><fmt:message key="catalog.quality"/> <myTg:stars
                                carQuality="${car.qualityClass}"/></p>
                        <c:if test="${sessionScope.role=='ADMIN'}">
                            <p>
                                <fmt:message key="customer.myRequests.status"/>
                                <c:choose>
                                    <c:when test="${car.status == 'AVAILABLE'}">
                                        <fmt:message key="catalog.status.available"/>
                                    </c:when>
                                    <c:when test="${car.status == 'RESERVED'}">
                                        <fmt:message key="catalog.status.reserved"/>
                                    </c:when>
                                    <c:when test="${car.status == 'UNDER_REPAIR'}">
                                        <fmt:message key="catalog.status.underRepair"/>
                                    </c:when>
                                </c:choose>
                            </p>
                        </c:if>

                        <c:choose>
                            <c:when test="${sessionScope.lang == 'en'}">
                                <p><c:out value="${car.descriptionEn}"/></p>
                            </c:when>
                            <c:otherwise>
                                <p><c:out value="${car.descriptionUa}"/></p>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${sessionScope.role=='ADMIN'}">
                                <form action="${pageContext.request.contextPath}/Admin/editCar" method="get"
                                      name="edit">
                                    <input type="text" name="id" value="${car.carId}" hidden>
                                    <input type="submit" class="btn" value="<fmt:message key="catalog.edit"/>">
                                </form>
                                <form action="${pageContext.request.contextPath}/Admin/deleteCar" method="post"
                                      name="edit">
                                    <input type="text" name="id" value="${car.carId}" hidden>
                                    <input type="submit" class="delete-btn" value="<fmt:message key="catalog.delete"/>">
                                </form>
                            </c:when>
                            <c:otherwise>
                                <form action="${pageContext.request.contextPath}/Customer/rent" method="get"
                                      name="rent">
                                    <input type="text" name="id" value="${car.carId}" hidden>
                                    <input type="submit" class="btn" value="<fmt:message key="catalog.rent"/>">
                                </form>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </section>

    <div class="pagination">
        <c:if test="${requestScope.pagesNumber > 0}">
            <ul class="pagination-list text-center justify-content-center">
                <li>
                    <button type="button" class="prev-page"
                            <c:if test="${requestScope.activePageNumber <= 1}">disabled</c:if> id="prevPageButton">Prev
                    </button>
                </li>

                <li value="${requestScope.activePageNumber}" class="pageNumber"
                    id="pageNumber">${requestScope.activePageNumber}</li>

                <li>
                    <button type="button" class="next-page"
                            <c:if test="${requestScope.activePageNumber >= requestScope.pagesNumber}">disabled</c:if>
                            id="nextPageButton">Next
                    </button>
                </li>
            </ul>
        </c:if>
    </div>

    <c:if test="${empty requestScope.carList}">
        <div class="no-results">
            <h2><fmt:message key="noResults"/></h2>
        </div>
    </c:if>
</main>

<footer class="footer">
    <%@ include file="../partial/footer.jspf" %>

    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/paginationNew.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.19/dist/sweetalert2.all.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/js/messages/infoLoadingMessages.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/filter.js"></script>
</footer>
</body>
</html>