<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
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

<c:import charEncoding="utf-8" url="parts/header.jsp"/>

<!-- Main wrapper -->
<div id="main-wrapper">
    <div id="content" class="isotope-container">
        <!-- Fluid container -->
        <div class="container-fluid">
            <!-- Main content -->
            <div class="container-fluid-content">
                <ul class="list-group_productList" style="width: 50%">
                    <c:forEach var="entry" items="${productCatalog}">
                        <c:set var="product" value="${entry.key}" scope="request"/>
                        <c:set var="url" value="${entry.value}" scope="request"/>
                        <c:set var="btnUrl" value="resources/img/cart/clear.svg" scope="request"/>
                        <form action="${pageContext.request.contextPath}/removeFromStore">
                            <input type="hidden" name="inStoreId" value="${product.id}" required>
                            <c:import url="parts/productItem.jsp"/>
                        </form>
                    </c:forEach>
                </ul>

                <ul class="list-group_productList" style="width: 50%">
                    <c:forEach var="entry" items="${NotInStore}">
                        <c:set var="product" value="${entry.key}" scope="request"/>
                        <c:set var="url" value="${entry.value}" scope="request"/>
                        <c:set var="btnUrl" value="resources/img/cart/plus.svg" scope="request"/>
                        <form action="${pageContext.request.contextPath}/addToStore">
                            <input type="hidden" name="notInStoreId" value="${product.id}" required>
                            <c:import url="parts/productItem.jsp"/>
                        </form>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>
<link rel='stylesheet' id='reset-css' href='resources/css/home_page/reset.css' type='text/css' media='all'/>
<link rel='stylesheet' id='bootstrap-css' href='resources/css/home_page/bootstrap.min.css' type='text/css' media='all'/>
<link rel='stylesheet' id='style-css' href='resources/css/home_page/style.css' type='text/css' media='all'/>


<link href='https://fonts.googleapis.com/css?family=Roboto:500,900,100,300,700,400' rel='stylesheet'
      type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Comfortaa:400,700,300|Ubuntu:400,700,300&subset=latin,cyrillic'
      rel='stylesheet' type='text/css'>

<link rel="stylesheet" href="resources/css/parts/header.css">
<link rel="stylesheet" href="resources/css/cart/cart.css">

</body>
</html>

