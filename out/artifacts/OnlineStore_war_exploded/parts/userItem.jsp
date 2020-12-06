<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<input type="hidden" name="userId" value="${user.id}" required>
<div class="list-group" style="width: 100%">
    <div class="list-group-item flex-column align-items-start">
        <div class="d-flex w-100 justify-content-between">
            <h1 class="mb-1">${user.username}</h1>

            <button class="${btn}" style="border: 0; width: 15%; float: right; word-wrap: break-word">
                ${btnText}
            </button>

        </div>
        <div class="d-flex w-100 justify-content-start">
            <p>${user.email}<br>
                ${user.balance}<br>
                ${user.role}<br>
                ${user.status}
            </p>
            <div class="img"></div>
            <div class="product_amount_container">

            </div>
            <div class="price">
            </div>
        </div>
    </div>
</div>
