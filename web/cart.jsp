<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel='stylesheet' id='main-css' href='resources/css/mainCatalog/style.css' type='text/css' media='all'/>
    <link rel='stylesheet' id='reset-css' href='resources/css/mainCatalog/reset.css' type='text/css' media='all'/>
    <link rel='stylesheet' id='bootstrap-css' href='resources/css/mainCatalog/bootstrap.min.css' type='text/css'
          media='all'/>
    <link rel='stylesheet' id='swiper-css' href='resources/css/mainCatalog/idangerous.swiper.css' type='text/css'
          media='all'/>
    <link rel='stylesheet' id='style-css' href='resources/css/mainCatalog/style.css' type='text/css' media='all'/>

    <script type='text/javascript' src='resources/js/jquery.js'></script>
    <script type='text/javascript' src='resources/js/jquery-migrate.min.js'></script>

    <style type="text/css">

        .list-group_c {
            display: flex;
            width: 40%;
        }

        .img {
            float: left;
            margin-right: 2%;
            width: 50%;
        }

        img {
            width: 100%;
        }

        .delete img {
            float: right;
        }

        .product_amount_container {
            padding-left: 2%;
            display: flex;
            justify-content: center;
            flex-direction: column;
            text-align: center;

            -webkit-align-items: center;
            -webkit-box-align: center;
            -ms-flex-align: center;
            align-items: center;
        }

        .product_amount {
            padding-bottom: 30%;
            padding-top: 30%;
        }

        .money {

            display: flex;
            justify-content: center;
            flex-direction: column;
            text-align: center;

            -webkit-align-items: center;
            -webkit-box-align: center;
            -ms-flex-align: center;
            align-items: center;

            margin-left: 15%;
        }

        .price {
            display: flex;
            justify-content: center;
            flex-direction: column;
            text-align: center;

            -webkit-align-items: center;
            -webkit-box-align: center;
            -ms-flex-align: center;
            align-items: center;

            position: absolute;
            right: 2%;
            bottom: 2%;
        }

    </style>
</head>
<body>
<c:import url="indexPageParts/header.jsp" />
${orderCost}
${productList}
<c:import url="indexPageParts/header.jsp" />
</body>
</html>