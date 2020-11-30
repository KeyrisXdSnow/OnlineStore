    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

        <ul class="list-group_c">
        <div class="list-group">
        <c:forEach var="product" items="${productList}">
            <c:import url="cartProductListItem.jsp"/>
        </c:forEach>
        </div>
        </ul>