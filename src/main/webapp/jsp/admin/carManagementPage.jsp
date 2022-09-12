<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/tld/myTagLib.tld" prefix="myTg" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Car Details</title>
    <link rel="stylesheet" href="<c:url value="/static/css/form.css"/>">
    <%@ include file="../partial/head.jspf" %>

</head>

<body>
<header>
    <tf:chooseHeader role="${sessionScope.role}"/>
</header>

<main>
    <input type="hidden" id="status" value="<%= request.getAttribute("status")%>">
    <input type="hidden" id="lang" value="<%= session.getAttribute("lang")%>">

    <c:set var="car" value="${sessionScope.car}"/>
    <c:set var="qualityClassList" value="${sessionScope.qualityClassList}"/>
    <c:set var="statusList" value="${sessionScope.statusList}"/>
    <div class="container">
        <h1><fmt:message key="carManagement.title"/> <c:out value="${car.brand} ${car.modelName}"/>, ID:<c:out
                value="${car.carId}"/>)</h1>
        <form action="${pageContext.request.contextPath}/Admin/editCar" method="post">
            <div class="form-control">
                <label><fmt:message key="carManagement.currentPrice"/> <c:out value="${car.price}"/>
                    <input type="number" placeholder="<fmt:message key="carManagement.currentPrice.placeHolder"/>"
                           name="input" required="required">
                </label>
                <input name="label" value="price" type="hidden">
                <input name="id" value="${car.carId}" type="hidden">
                <input name="inputID" value="0" type="hidden">
                <input type="submit" class="btn" value="<fmt:message key="carManagement.button"/>">
            </div>
        </form>
        <form action="${pageContext.request.contextPath}/Admin/editCar" method="post">
            <div class="form-control">
                <label><fmt:message key="carManagement.currentQualityClass"/> <c:out value="${car.qualityClass}"/>
                    <select name="inputID">
                        <c:forEach items="${qualityClassList}" var="qualityClass" varStatus="loop">
                            <option value="${qualityClass.id}">
                                <myTg:stars carQuality="${qualityClass.value}"/>
                            </option>
                        </c:forEach>
                    </select>
                </label>
                <input name="input" value="empty" type="hidden">
                <input name="label" value="quality" type="hidden">
                <input name="id" value="${car.carId}" type="hidden">
                <input type="submit" class="btn" value="<fmt:message key="carManagement.button"/>">
            </div>
        </form>
        <form action="${pageContext.request.contextPath}/Admin/editCar" method="post">
            <div class="form-control">
                <label><fmt:message key="carManagement.currentStatus"/> <c:out value="${car.status}"/>
                    <select name="inputID">
                        <c:forEach items="${statusList}" var="carStatus" varStatus="loop">
                            <option value="${carStatus.id}">
                                    ${carStatus.value}
                            </option>
                        </c:forEach>
                    </select>
                </label>
                <input name="input" value="empty" type="hidden">
                <input name="id" value="${car.carId}" type="hidden">
                <input name="label" value="status" type="hidden">
                <input type="submit" class="btn" value="<fmt:message key="carManagement.button"/>">
            </div>
        </form>
        <form action="${pageContext.request.contextPath}/Admin/editCar" method="post">
            <div class="form-control">
                <label><fmt:message key="carManagement.currentDescription"/> <c:out value="${car.descriptionEn}"/>
                    <input type="text"
                           placeholder="<fmt:message key="carManagement.currentDescription.placeHolder.en"/>"
                           name="input" required="required">
                </label>
                <input name="id" value="${car.carId}" type="hidden">
                <input name="label" value="descriptionEn" type="hidden">
                <input name="inputID" value="0" type="hidden">
                <input type="submit" class="btn" value="<fmt:message key="carManagement.button"/>">
            </div>
        </form>
        <form action="${pageContext.request.contextPath}/Admin/editCar" method="post">
            <div class="form-control">
                <label><fmt:message key="carManagement.currentDescription"/> <c:out value="${car.descriptionUa}"/>
                    <input type="text"
                           placeholder="<fmt:message key="carManagement.currentDescription.placeHolder.ua"/>"
                           name="input" required="required">
                </label>
                <input name="id" value="${car.carId}" type="hidden">
                <input name="label" value="descriptionUa" type="hidden">
                <input name="inputID" value="0" type="hidden">
                <input type="submit" class="btn" value="<fmt:message key="carManagement.button"/>">
            </div>
        </form>

    </div>
</main>

<footer>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.19/dist/sweetalert2.all.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/js/messages/edit&addNewCarMessages.js"></script>
</footer>
</body>
</html>