<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf" %>

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
    <c:set var="car" value="${sessionScope.car}"/>
    <c:set var="qualityClassList" value="${sessionScope.qualityClassList}"/>
    <c:set var="statusList" value="${sessionScope.statusList}"/>
    <div class="container">
        <h1>Edit Car Info (car: <c:out value="${car.brand} ${car.modelName}"/>, ID:<c:out value="${car.carId}"/>)</h1>
        <form action="${pageContext.request.contextPath}/Admin/editCar" method="post">
            <div class="form-control">
                <label>Current price: <c:out value="${car.price}"/>
                    <input type="number" placeholder="Type new price" name="input" required="required">
                </label>
                <input name="label" value="price" type="hidden">
                <input name="id" value="${car.carId}" type="hidden">
                <input name="inputID" value="0" type="hidden">
                <input type="submit" class="btn" value="Update">
            </div>
        </form>
        <form action="${pageContext.request.contextPath}/Admin/editCar" method="post">
            <div class="form-control">
                <label>Current quality class: <c:out value="${car.qualityClass}"/>
                    <select name="inputID">
                        <c:forEach items="${qualityClassList}" var="qualityClass" varStatus="loop">
                            <option value="${qualityClass.id}">
                                    ${qualityClass.value}
                            </option>
                        </c:forEach>
                    </select>
                </label>
                <input name="input" value="empty" type="hidden">
                <input name="label" value="quality" type="hidden">
                <input name="id" value="${car.carId}" type="hidden">
                <input type="submit" class="btn" value="Update">
            </div>
        </form>
        <form action="${pageContext.request.contextPath}/Admin/editCar" method="post">
            <div class="form-control">
                <label>Current status: <c:out value="${car.status}"/>
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
                <input type="submit" class="btn" value="Update">
            </div>
        </form>
        <form action="${pageContext.request.contextPath}/Admin/editCar" method="post">
            <div class="form-control">
                <label>Current description: <c:out value="${car.description}"/>
                    <input type="text" placeholder="Type new description" name="input" required="required">
                </label>
                <input name="id" value="${car.carId}" type="hidden">
                <input name="label" value="description" type="hidden">
                <input name="inputID" value="0" type="hidden">
                <input type="submit" class="btn" value="Update">
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