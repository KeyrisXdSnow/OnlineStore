package utils.builders;

import model.entities.beans.Cart;
import model.entities.beans.Product;

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

    public CartBuilder withProductList (List<? extends Product> productList) {
        this.cart.setProductList(productList);
        return this;
    }

    public Cart getCart() {
        return cart;
    }
}
