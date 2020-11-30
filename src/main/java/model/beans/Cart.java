package model.beans;

import java.io.Serializable;
import java.util.List;

public class Cart implements Serializable {

    private long cartId;
    private long userId;
    private List<? extends Product> productList ;

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

    public List<? extends Product> getProductList() {
        return productList;
    }

    public void setProductList(List<? extends Product> productList) {
        this.productList = productList;
    }
}
