<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="role" required="true" %>

<c:choose>

    <c:when test="${role.equals('GUEST')}">
        <%@include file="../../jsp/partial/guestHeader.jspf" %>
    </c:when>

    <c:when test="${role.equals('CUSTOMER')}">
        <%@include file="../../jsp/partial/customerHeader.jspf" %>
    </c:when>

<%--    <c:when test="${role.equals('MANAGER')}">--%>
<%--        <%@include file="../../template/partial/managerHeader.jspf" %>--%>
<%--    </c:when>--%>

    <c:when test="${role.equals('ADMIN')}">
        <%@include file="../../jsp/partial/adminHeader.jspf" %>
    </c:when>

</c:choose>
