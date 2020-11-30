package servlet;

import model.config.PagesСonfig;
import model.entities.beans.Cart;
import model.entities.beans.User;
import model.entities.dao.CartDAO;
import model.entities.dao.UserDAO;
import model.services.AppService;

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

        Cart cart = CartDAO.getCartByUserId(user.getId());
        AppService.storeLoginedUser(req.getSession(), user,cart);

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(PagesСonfig.indexPage);
        dispatcher.forward(req, resp);
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
