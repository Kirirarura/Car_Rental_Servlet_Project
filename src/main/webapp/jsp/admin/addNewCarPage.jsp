<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/tld/myTagLib.tld" prefix="myTg"%>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add New Car</title>
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

    <c:set var="brandList" value="${sessionScope.brandList}"/>
    <c:set var="qualityClassList" value="${sessionScope.qualityClassList}"/>

    <div class="container">
        <h1><fmt:message key="addNewCar.title"/></h1>
        <form action="${pageContext.request.contextPath}/Admin/addNewCar" method="post">
            <div class="form-control">
                <label><fmt:message key="addNewCar.brand"/>
                    <select name="brandId">
                        <c:forEach items="${brandList}" var="brand" varStatus="loop">
                            <option value="${brand.id}">
                                    ${brand.value}
                            </option>
                        </c:forEach>
                    </select>
                </label>
            </div>
            <div class="form-control">
                <label><fmt:message key="addNewCar.model"/>
                    <input type="text" placeholder="<fmt:message key="addNewCar.model.placeHolder"/>" name="model"
                           required="required">
                </label>
            </div>
            <div class="form-control">
                <label><fmt:message key="addNewCar.price"/>
                    <input type="number" placeholder="<fmt:message key="addNewCar.price.placeHolder"/>" name="price"
                           required="required">
                </label>
            </div>
            <div class="form-control">
                <label><fmt:message key="addNewCar.qualityClass"/>
                    <select name="qualityId">
                        <c:forEach items="${qualityClassList}" var="quality" varStatus="loop">
                            <option value="${quality.id}">
                                <myTg:stars carQuality="${quality.value}"></myTg:stars>
                            </option>
                        </c:forEach>
                    </select>
                </label>
            </div>
            <div class="form-control">
                <label><fmt:message key="addNewCar.description.en"/>
                    <input type="text" placeholder="<fmt:message key="addNewCar.description.placeHolder.en"/>"
                           name="descriptionEn" required="required">
                </label>
            </div>
            <div class="form-control">
                <label><fmt:message key="addNewCar.description.ua"/>
                    <input type="text" placeholder="<fmt:message key="addNewCar.description.placeHolder.ua"/>"
                           name="descriptionUa" required="required">
                </label>
            </div>

            <div class="form-control">
                <input type="submit" class="btn" value="<fmt:message key="addNewCar.button"/>">
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
