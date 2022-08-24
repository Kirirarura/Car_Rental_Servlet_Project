<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf" %>

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

    <c:set var="brandList" value="${sessionScope.brandList}"/>
    <c:set var="qualityClassList" value="${sessionScope.qualityClassList}"/>

    <div class="container">
        <h1>Add New Car</h1>
        <form action="${pageContext.request.contextPath}/Admin/addNewCar" method="post">
            <div class="form-control">
                <label>Brand
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
                <label>Model
                    <input type="text" placeholder="Type model" name="model" required="required">
                </label>
            </div>
            <div class="form-control">
                <label>Price
                    <input type="text" placeholder="Type Price" name="price" required="required">
                </label>
            </div>
            <div class="form-control">
                <label>Quality class
                    <select name="qualityId">
                        <c:forEach items="${qualityClassList}" var="quality" varStatus="loop">
                            <option value="${quality.id}">
                                    ${quality.value}
                            </option>
                        </c:forEach>
                    </select>
                </label>
            </div>
            <div class="form-control">
                <label>Description
                    <input type="text" placeholder="Type description" name="description" required="required">
                </label>
            </div>
            <div class="form-control">
                <input type="submit" class="btn" value="Add new car">
            </div>
        </form>
    </div>
</main>

<footer>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.19/dist/sweetalert2.all.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/messages/edit&addNewCarMessages.js"></script>
</footer>

</body>
</html>
