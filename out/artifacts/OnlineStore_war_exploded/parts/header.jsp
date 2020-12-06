<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<div id="top" class="shadow">
    <nav class="menu" style="padding-top: 1.5%">
        <ul>
            <c:if test="${userInfo.role == 'USER'}">
                <li>
                    <form action="${pageContext.request.contextPath}/" method="get">
                        <button> Home page</button>

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
                            <img src="${pageContext.request.contextPath}/resources/img/header/user/money.png"
                                 style="width: 12%">
                        </button>
                    </form>
                </li>
            </c:if>
            <c:if test="${userInfo.role == 'ADMIN'}">
                <li>
                    <form action="${pageContext.request.contextPath}/" method="get">

                        <button>Каталог Товаров</button>

                    </form>
                </li>
                <li>
                    <form action="${pageContext.request.contextPath}/editCatalog" method="get">
                        <button>Изменить каталог товаров
                            <img src="${pageContext.request.contextPath}/resources/img/header/admin/alpaka1.png"
                                 style="width: 10%">
                        </button>
                    </form>
                </li>
                <li>
                    <form action="${pageContext.request.contextPath}/editUser">
                        <button href="">Изменить юзеров
                            <img src="${pageContext.request.contextPath}/resources/img/header/admin/group%20(2).png"
                                 style="width: 4%">
                        </button>
                    </form>
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
