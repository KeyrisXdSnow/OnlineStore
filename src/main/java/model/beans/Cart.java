package model.beans;

import java.io.Serializable;
import java.util.LinkedHashMap;

public class Cart implements Serializable {

    private long cartId;
    private long userId;
    private LinkedHashMap<? extends Product,Integer> productList ;



    public Cart(){
    }

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LinkedHashMap<? extends Product, Integer> getProductList() {
        return productList;
    }

    public void setProductList(LinkedHashMap<? extends Product, Integer> productList) {
        this.productList = productList;
    }
}
