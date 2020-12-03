package utils;

import model.beans.Cart;
import model.beans.Product;
import model.beans.User;
import model.dao.CartDAO;
import utils.exceptions.NegativeBalanceException;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

public class CartUtils {

    public static final String attributeCart = "cart";
    public static final String orderCostAttribute = "orderCost";
    public static final String orderSizeAttribute = "orderSize";


    public static void storeCart (HttpSession session, Cart cart) {
        session.setAttribute(attributeCart, cart);
    }

    public static void removeCart (HttpSession session) {
        session.removeAttribute(attributeCart);
    }

    public static Cart getCart (HttpSession session) {
        return (Cart) session.getAttribute(attributeCart);
    }

    public static void storeOrderSize (HttpSession session, int size) {
        session.setAttribute(orderSizeAttribute, size);
    }

    public static void removeOrderSize (HttpSession session) {
        session.removeAttribute(orderSizeAttribute);
    }

    public static Cart getOrderSize (HttpSession session) {
        return (Cart) session.getAttribute(orderSizeAttribute);
    }

    public static void loadCast(HttpSession session) {

        Cart productCart = getCart(session);

        var cost = CartUtils.calculatePrice(productCart.getProductList());

        updateOrderCost(session, cost);
        storeOrderSize(session,productCart.getProductList().size());

    }

    public static void cancelProduct(HttpSession session, long productId) {

        Cart productCart = getCart(session);

        Product product = productCart.getProductList().keySet().stream().filter(pr -> pr.getId() == productId).findFirst().orElse(null);
        productCart.getProductList().remove(product);

        CartDAO.deleteProductFromCartByCartIdAndProductId(productCart.getCartId(),productId);

        storeCart(session,productCart);
        updateOrderCost(session, calculatePrice(productCart.getProductList()));
        updateOrderSize(session);
    }

    public static void increaseProductAmount(HttpSession session, long productId) throws NullPointerException, ClassCastException {

        Cart productCart = getCart(session);

        Map.Entry productEntry = productCart
                .getProductList()
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().getId() == productId)
                .findFirst()
                .orElse(null);
        ;

        Integer newValue = (Integer.parseInt(productEntry.getValue().toString())) + 1;
        productEntry.setValue(newValue);

        CartDAO.updateProductAmountByProductId(productCart.getCartId(),productId, newValue);

        storeCart(session,productCart);
        updateOrderCost(session, calculatePrice(productCart.getProductList()));

    }

    public static void reduceProductAmount(HttpSession session, long productId) throws NullPointerException, ClassCastException {

        Cart productCart = getCart(session);

        Map.Entry productEntry = productCart
                .getProductList()
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().getId() == productId)
                .findFirst()
                .orElse(null);
        ;

        Integer newValue = (Integer.parseInt(productEntry.getValue().toString())) - 1;

        if (newValue == 0) {

            CartDAO.deleteProductFromCartByCartIdAndProductId(productCart.getCartId(), productId);
            CartUtils.cancelProduct(session, productId);

        } else {
            CartDAO.updateProductAmountByProductId(productCart.getCartId(),productId, newValue);
            productEntry.setValue(newValue);
        }

        storeCart(session,productCart);
        updateOrderCost(session, calculatePrice(productCart.getProductList()));

    }

    public static void makeOrder(HttpSession session) throws NegativeBalanceException {

        Cart productCart = getCart(session);
        User user = AppUtils.getLoginedUser(session);

        double fullCost = calculatePrice(productCart.getProductList());

        if (user.getBalance() >= fullCost) {

            user.setBalance(user.getBalance() - fullCost);
            productCart.getProductList().clear();

            CartDAO.deleteAllProductFromCartByCId(productCart.getCartId());

            storeCart(session,productCart);
            updateOrderCost(session, calculatePrice(productCart.getProductList()));
            removeOrderSize(session);
            //session.setAttribute(orderSizeAttribute, 0);

        } else throw new NegativeBalanceException();

    }

    private static double calculatePrice(LinkedHashMap<? extends Product, Integer> cartList) {

        var values = cartList.values().toArray();
        var keys = cartList.keySet().toArray();

        double cost = 0;
        for (int i = 0; i < keys.length; i++) {
            cost += ((Product) keys[i]).getCost() * Integer.parseInt(values[i].toString());
        }

        return cost;
    }

    private static void updateOrderCost(HttpSession session, double newValue) {
        session.setAttribute(orderCostAttribute, newValue);
    }

    private static void updateOrderSize(HttpSession session) {
        int size = (int) session.getAttribute(orderSizeAttribute);
        session.setAttribute(orderSizeAttribute, size - 1);
    }


}
