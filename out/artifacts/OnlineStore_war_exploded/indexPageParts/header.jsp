<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<div id="top" class="shadow">
    <nav class="menu">
        <ul>
            <li><a href="${pageContext.request.contextPath}/editCatalog">${userInfo.username}</a></li>
            <li>
                <form action="${pageContext.request.contextPath}/cart" method="get">
                    <button>Корзина
                        <img src="${pageContext.request.contextPath}/resources/img/header/cart.png" style="width: 22%">
                    </button>
                </form>
            </li>
            <li>
                <form action="${pageContext.request.contextPath}/giveMoney">
                    <button href="">Получить 50 деняг
                        <img src="${pageContext.request.contextPath}/resources/img/header/money.png" style="width: 12%">
                    </button>
                </form>
            </li>
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
