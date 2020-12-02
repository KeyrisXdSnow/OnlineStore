<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <!-- Meta -->
    <meta charset="UTF-8">
    <title>Rayleigh</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="content-type" content="text/html; charset=utf-8">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body class="page page-template page-template-template-portfolio page-template-template-portfolio-php menu-left menu-collapsible">

<c:import url="indexPageParts/header.jsp"></c:import>

<!-- Main wrapper -->
<div id="main-wrapper">

    <div id="content" class="isotope-container">
        <!-- Fluid container -->
        <div class="container-fluid">
            <!-- Main content -->
            <div class="isotope isotope-4 row">
                <c:forEach var="country" items="${productCatalog}">
                    <article id="post-147"
                             class="post-147 portfolio type-portfolio status-publish has-post-thumbnail hentry isotope-item col-3 3d design">
                        <a href="#" class="image" style="background-image: url(${country.value})"
                           data-portfolio="147"></a>
                        <div class="inner">
                            <header>
                                <form action="${pageContext.request.contextPath}/addProduct" method="get">
                                    <input type="hidden" name="productId" value="${country.key.id}" required>

                                    <div class="btn_container">
                                        <button class="btn btn-outline-success"><p>💰</p></button>
                                    </div>
                                    <h4>${country.key.name}</h4>
                                    <p>Тут будет какой-то текст =З</p>
                                </form>
                            </header>
                        </div>
                    </article>
                </c:forEach>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="resources/js/masonry.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.isotope.min.js"></script>
<script type="text/javascript" src="resources/js/custom.js"></script>
<script type='text/javascript' src='resources/js/jquery.js'></script>
<script type='text/javascript' src='resources/js/jquery-migrate.min.js'></script>

<script type="text/javascript">

    jQuery(document).ready(function ($) {

        'use strict';

        $('article.portfolio a').click(function () {
            var itemID = $(this).attr('data-portfolio');
            $('#top').addClass('portfolio-open');
            $('#portfolio-full').html('<div class="portfolio-loader"><div class="windows8"><div class="wBall" id="wBall_1"><div class="wInnerBall"></div></div><div class="wBall" id="wBall_2"><div class="wInnerBall"></div></div><div class="wBall" id="wBall_3"><div class="wInnerBall"></div></div><div class="wBall" id="wBall_4"><div class="wInnerBall"></div></div><div class="wBall" id="wBall_5"><div class="wInnerBall"></div></div></div></div>');
            $('#portfolio-full').addClass('portfolio-open');
            $.ajax({
                type: 'POST',
                url: 'http://r6themes.com/rayleigh-wp/light/wp-admin/admin-ajax.php',
                data: {action: 'r6_ajax_portfolio_hook', id: itemID}
            }).done(function (msg) {
                $('#portfolio-full').html(msg);
                $('.swiper').trigger('initSwiper');
            });
            return false;
        });

        $('#portfolio-close').click(function () {
            $('#portfolio-full').removeClass('portfolio-open');
            $('#top').removeClass('portfolio-open');
            $('#portfolio-full').html('');
            return false;
        });

    });
</script>


<link rel='stylesheet' id='reset-css' href='resources/css/home_page/reset.css' type='text/css' media='all'/>
<link rel='stylesheet' id='bootstrap-css' href='resources/css/home_page/bootstrap.min.css' type='text/css' media='all'/>
<link rel='stylesheet' id='swiper-css' href='resources/css/home_page/idangerous.swiper.css' type='text/css'
      media='all'/>
<link rel='stylesheet' id='style-css' href='resources/css/home_page/style.css' type='text/css' media='all'/>

<link href='https://fonts.googleapis.com/css?family=Roboto:500,900,100,300,700,400' rel='stylesheet'
      type='text/css'>


<link href='http://fonts.googleapis.com/css?family=Comfortaa:400,700,300|Ubuntu:400,700,300&subset=latin,cyrillic'
      rel='stylesheet' type='text/css'>

<link rel="stylesheet" href="resources/css/parts/header.css">
<link rel="stylesheet" href="resources/css/index.css">

</body>
</html>

