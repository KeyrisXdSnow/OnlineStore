package utils.builders;


import model.beans.Cart;
import model.beans.Product;

import java.util.LinkedHashMap;
import java.util.List;

public class CartBuilder {

    private Cart cart;

    public CartBuilder() {
        cart = new Cart();
    }

    public CartBuilder withCartId (long cartId) {
        this.cart.setCartId(cartId);
        return this;
    }

    public CartBuilder withUserId (long userId) {
        this.cart.setUserId(userId);
        return this;
    }

    public CartBuilder withProductList (LinkedHashMap<? extends Product,Integer> productList) {
        this.cart.setProductList(productList);
        return this;
    }

    public Cart getCart() {
        return cart;
    }
}
