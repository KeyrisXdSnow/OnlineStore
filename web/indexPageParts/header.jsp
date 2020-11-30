<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!-- Top header -->
<div id="top" class="shadow">
    <header id="logo"></header>
    <link href='https://fonts.googleapis.com/css?family=Roboto:500,900,100,300,700,400' rel='stylesheet'
          type='text/css'>
    <nav class="menu">
        <ul>
            <li><a href="#">${userInfo.username}</a></li>
            <li>
                <form action="${pageContext.request.contextPath}/cart" method="post">
                    <button>Корзина
                        <img src="${pageContext.request.contextPath}/resources/img/mainCatalog/menu/cart.png" style="width: 22%">
                    </button>
                </form>
            </li>
            <li>
                <form action="${pageContext.request.contextPath}/giveMoney">
                    <button href="">Получить 50 деняг
                        <img src="${pageContext.request.contextPath}/resources/img/mainCatalog/menu/money.png" style="width: 12%">
                    </button>
                </form>
            </li>
            <li>
                <form action="${pageContext.request.contextPath}/logout" method="post">
                    <button href="">Выйти из системы
                        <img src="${pageContext.request.contextPath}/resources/img/mainCatalog/menu/exit.png" style="width: 12%">
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
