package utils;

import config.PagesСonfig;
import model.beans.Cart;
import model.beans.Product;
import model.beans.User;
import model.dao.CartDAO;
import model.dao.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

public class UserUtils {

    public static final double money = 50.0;

    public static void addProductToCart(HttpSession session, long productId) {

        Cart cart = CartUtils.getCart(session);

        Product product = AppUtils.getCatalog(session)
                .keySet()
                .stream()
                .filter(prod -> prod.getId() == productId)
                .findFirst()
                .orElse(null);

        CartDAO.addProductToBasket(cart, product);
    }

    public static void addMoneyOnBalance (HttpSession session) {

        User user = AppUtils.getLoginedUser(session);
        user.setBalance(user.getBalance()+money);

        try {

            UserDAO.updateBalanceByUserId(user.getId(),user.getBalance());
            AppUtils.storeLoginedUserInfo(session,user);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
