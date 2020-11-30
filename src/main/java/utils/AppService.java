package utils;

import model.beans.Cart;
import model.beans.User;

import javax.servlet.http.HttpSession;

public class AppService {

    private static final String attributeUser = "userInfo";
    private static final String attributeCart = "cart";

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
    }


    public static Cart getLoginedUserCart (HttpSession session) {
        return  (Cart) session.getAttribute(attributeCart);
    }





}
