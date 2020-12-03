package servlet;


import config.PagesСonfig;
import model.beans.Cart;
import model.beans.User;
import model.dao.CartDAO;
import model.dao.ProductDAO;
import model.dao.UserDAO;
import model.entities.UserStatus;
import utils.AppUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@WebServlet( name = "LogInServlet", urlPatterns = {"/login"})
public class LogInServlet extends HttpServlet {

    private static MessageDigest digest;

    static {
        try {
            digest = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public LogInServlet() {
        super();
    }



    @Override
    public void init() throws ServletException {
        super.init();
    }

    // TODO: сделать нормальный редирект чтобы был новый урл а не старый
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username, password;
        try {

            digest.reset();
            digest.update(req.getParameter("psw").getBytes(StandardCharsets.UTF_8));

            username = req.getParameter("uname");
            password = String.format("%040x", new BigInteger(1, digest.digest()));

        } catch (NullPointerException e) {
            req.setAttribute("ErrorMessage","Wrong username or password");
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(PagesСonfig.loginPage);
            dispatcher.forward(req, resp);
            return;
        }

        User user = UserDAO.getUserByUsername(username, password);

        if ( user == null) {



            req.setAttribute("ErrorMessage","Wrong username or password");
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(PagesСonfig.loginPage);
            dispatcher.forward(req, resp);
            return;
        }


        if (user.getStatus().equals(UserStatus.BAN)) {
            req.setAttribute("ErrorMessage","You in ban");
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(PagesСonfig.loginPage);
            dispatcher.forward(req, resp);
            return;
        }

        var productList = ProductDAO.getProductListInStore();
        Cart cart = CartDAO.getCartByUserId(user.getId(), productList);
        AppUtils.storeLoginedUser(req.getSession(), user,cart);
        AppUtils.storeCatalog(req.getSession(),productList);



        resp.sendRedirect(super.getServletContext().getContextPath()+ PagesСonfig.indexUrl);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(PagesСonfig.loginPage);
        dispatcher.forward(req, resp);
    }


    @Override
    public void destroy() {
        super.destroy();
    }
}
