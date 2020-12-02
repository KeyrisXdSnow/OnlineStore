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
        <!-- Fluid container -->
        <div class="container-fluid">
            <!-- Main content -->
            <div class="container-fluid-content">
                <div class="isotope isotope-4 row">
                    <ul class="list-group_productList">
                        <div class="list-group" style="width: 60%">

                            <c:forEach var="country" items="${productList}">
                                <div class="list-group-item flex-column align-items-start">
                                    <div class="d-flex w-100 justify-content-between">
                                        <h1 class="mb-1">${country.key.name}</h1>
                                        <button type="button" class="btn btn-outline-light"
                                                style="border: 0; width: 10%; float: right;"><img
                                                src="resources/img/cart/clear.svg">
                                        </button>
                                    </div>
                                    <div class="d-flex w-100 justify-content-start">
                                        <div class="img"><img
                                                src="${productCatalog.getOrDefault(country.key,'resources/img/products/not_found.svg')}">
                                        </div>
                                        <div class="product_amount_container">
                                            <div>

                                                <button type="button" class="btn btn-dark">-</button>
                                            </div>
                                            <div class="product_amount">${country.value}</div>
                                            <div>
                                                <button type="button" class="btn btn-dark">+</button>
                                            </div>
                                        </div>
                                        <div class="money">
                                            <div><p>${country.value * country.key.cost}</p></div>
                                        </div>

                                        <div class="price">
                                            <div><p>${country.key.cost} деньга/штук.</p></div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </ul>
                </div>
                <form action="/makeOrder" method="post" >
                    <div class="make_order_container">
                        <ul class="list-group" style="width: 100%">
                            <li class="list-group-item">Товаров: 1</li>
                            <li class="list-group-item"><strong>Всего: 40 деняг</strong></li>
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
