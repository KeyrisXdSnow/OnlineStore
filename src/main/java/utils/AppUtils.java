package utils;

import model.beans.Cart;
import model.beans.Product;
import model.beans.User;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.List;

public class AppUtils {

    private static final String attributeUser = "userInfo";
    private static final String attributeCart = "cart";
    public static final String attributeCatalog = "productCatalog";

    public static void storeLoginedUser(HttpSession session, User user, Cart cart) {
        session.setAttribute(attributeUser, user);
        session.setAttribute(attributeCart, cart);
    }

    public static void storeLoginedUserInfo(HttpSession session, User user) {
        session.setAttribute(attributeUser, user);
    }

    public static User getLoginedUser (HttpSession session) {
        return  (User) session.getAttribute(attributeUser);
    }

    public static void removeLoginedUser (HttpSession session) {
        session.removeAttribute(attributeCart);
        session.removeAttribute(attributeUser);
        session.removeAttribute(attributeCatalog);
    }

    public static Cart getLoginedUserCart (HttpSession session) {
        return  (Cart) session.getAttribute(attributeCart);
    }




    public static void storeCatalog(HttpSession session, LinkedHashMap<? extends Product, String> productList) {
        session.setAttribute(attributeCatalog, productList);
    }


    public static LinkedHashMap<? extends Product, String> getCatalog (HttpSession session) {
        return (LinkedHashMap<? extends Product, String>) session.getAttribute(attributeCatalog);
    }


    public static void removeCatalog (HttpSession session) {
        session.removeAttribute(attributeCatalog);
    }

    public static boolean catalogIsExist (HttpSession session) {

        return session.getAttribute(attributeCatalog) != null;
    }






}
