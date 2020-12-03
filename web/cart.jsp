<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- Meta -->
    <meta charset="UTF-8">
    <title>Alpaca store</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="content-type" content="text/html; charset=utf-8">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body class="page page-template page-template-template-portfolio page-template-template-portfolio-php menu-left menu-collapsible">

<c:import charEncoding="utf-8" url="indexPageParts/header.jsp"/>

<!-- Main wrapper -->
<div id="main-wrapper">
    <div id="content" class="isotope-container">
        <div class="messages_container" style="display:flex; justify-content: center;">
            <h1><span style="color:red">${ErrorMessage}</span></h1>
        </div>
        <!-- Fluid container -->
        <div class="container-fluid">
            <div class="messages_container" style="display:flex; justify-content: center;">
                <h1><span style="color:#4cae50">${OKMessage}</span></h1>
            </div>
            <!-- Main content -->
            <div class="container-fluid-content">
                <div class="isotope isotope-4 row">
                    <ul class="list-group_productList">
                        <div class="list-group" style="width: 60%">
                            <c:forEach var="country" items="${cart.productList}">
                                <div class="list-group-item flex-column align-items-start">
                                    <form action="${pageContext.request.contextPath}/cancelProduct">

                                        <div class="d-flex w-100 justify-content-between">
                                            <h1 class="mb-1">${country.key.name}</h1>
                                            <input name="productId" type="hidden" value="${country.key.id}" required>

                                            <button class="btn btn-outline-light"
                                                    style="border: 0; width: 10%; float: right;"><img
                                                    src="resources/img/cart/clear.svg">
                                            </button>
                                        </div>
                                    </form>

                                    <div class="d-flex w-100 justify-content-start">
                                        <div class="img"><img
                                                src="${productCatalog.getOrDefault(country.key,'resources/img/products/not_found.svg')}">
                                        </div>
                                        <div class="product_amount_container">
                                            <div>
                                                <form action="${pageContext.request.contextPath}/increaseProductAmount">
                                                    <input name="productId" type="hidden" value="${country.key.id}"
                                                           required>
                                                    <button class="btn btn-dark">+</button>
                                                </form>
                                            </div>
                                            <div class="product_amount"><h6>${country.value}</h6></div>
                                            <div>
                                                <form action="${pageContext.request.contextPath}/reduceProductAmount">
                                                    <input name="productId" type="hidden" value="${country.key.id}"
                                                           required>
                                                    <button class="btn btn-dark">-</button>
                                                </form>
                                            </div>
                                        </div>
                                        <div class="money">
                                            <div><h3><strong>${country.value * country.key.cost} деняг 💵 </strong>
                                            </h3>
                                            </div>
                                        </div>

                                        <div class="price">
                                            <div><h6>${country.key.cost} деньга/штук. 💵</h6></div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </ul>
                </div>
                <form action="${pageContext.request.contextPath}/makeOrder" method="post">
                    <div class="make_order_container">
                        <ul class="list-group" style="width: 100%">
                            <li class="list-group-item">Товаров: ${orderSize}</li>
                            <li class="list-group-item"><strong>Всего: ${orderCost} деняг</strong></li>
                        </ul>
                        <button class="btn btn-success">Купить</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<link rel='stylesheet' id='reset-css' href='resources/css/home_page/reset.css' type='text/css' media='all'/>
<link rel='stylesheet' id='bootstrap-css' href='resources/css/home_page/bootstrap.min.css' type='text/css' media='all'/>
<link rel='stylesheet' id='swiper-css' href='resources/css/home_page/idangerous.swiper.css' type='text/css'
      media='all'/>
<link rel='stylesheet' id='style-css' href='resources/css/home_page/style.css' type='text/css' media='all'/>


<link href='https://fonts.googleapis.com/css?family=Roboto:500,900,100,300,700,400' rel='stylesheet'
      type='text/css'>

<link rel="stylesheet" href="resources/css/parts/header.css">

<link href='http://fonts.googleapis.com/css?family=Comfortaa:400,700,300|Ubuntu:400,700,300&subset=latin,cyrillic'
      rel='stylesheet' type='text/css'>

<link rel="stylesheet" href="resources/css/parts/header.css">
<link rel="stylesheet" href="resources/css/cart/cart.css">

</body>
</html>
