<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<div class="list-group" style="width: 100%">
    <div class="list-group-item flex-column align-items-start">
        <div class="d-flex w-100 justify-content-between">
            <h1 class="mb-1">${product.name}</h1>

            <button class="btn btn-outline-light" style="border: 0; width: 10%; float: right;">
                <img src="${btnUrl}" alt="Удалить">
            </button>

        </div>
        <div class="d-flex w-100 justify-content-start">
            <div class="img"><img src="${url}" alt="resources/img/products/not_found.svg'"></div>
            <div class="product_amount_container">

            </div>
            <div class="price">
                <div><h5><strong>${product.cost} деньга/штук.</strong></h5></div>
            </div>
        </div>
    </div>
</div>