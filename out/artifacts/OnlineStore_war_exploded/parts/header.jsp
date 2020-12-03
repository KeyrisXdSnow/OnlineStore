<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<div id="top" class="shadow">
    <nav class="menu">
        <ul>

            <c:if test="${userInfo.role == 'USER'}">
                <li>
                    <form>
                        <a href="${pageContext.request.contextPath}/">Home page</a>
                    </form>
                </li>
                <li>
                    <form action="${pageContext.request.contextPath}/cart" method="get">
                        <button>Корзина
                            <img src="${pageContext.request.contextPath}/resources/img/header/user/cart.png"
                                 style="width: 22%">
                        </button>
                    </form>
                </li>
                <li>
                    <form action="${pageContext.request.contextPath}/giveMoney">
                        <button href="">Получить 50 деняг
                            <img src="${pageContext.request.contextPath}/resources/img/header/user/money.png" style="width: 12%">
                        </button>
                    </form>
                </li>
            </c:if>
            <c:if test="${userInfo.role == 'ADMIN'}">
                <li>
                    <form>
                        <a href="${pageContext.request.contextPath}/">Каталог товаров</a>
                    </form>
                </li>
                <li><a href="${pageContext.request.contextPath}/editCatalog"> Изменить Каталог товаров
                    <img src="${pageContext.request.contextPath}/resources/img/header/admin/alpaca.svg" style="width: 6%">
                </a>
                </li>
                <li><a href="${pageContext.request.contextPath}/editUser"> Каталог юезров
                    <img src="${pageContext.request.contextPath}/resources/img/header/admin/group.svg" style="width: 12%">
                </a>
                </li>
            </c:if>
            <li>
                <form action="${pageContext.request.contextPath}/logout" method="post">
                    <button href="">Выход из системы
                        <img src="${pageContext.request.contextPath}/resources/img/header/exit.png" style="width: 11%">
                    </button>
                </form>
            </li>
        </ul>
    </nav>

    <nav class="user_info">
        <ul>
            <li><a href="#">${userInfo.username}</a></li>
            <li>${userInfo.balance}</li>
        </ul>
    </nav>
</div>
