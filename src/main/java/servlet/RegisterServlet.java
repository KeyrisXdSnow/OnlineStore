package servlet;

import config.PagesСonfig;
import model.dao.CartDAO;
import model.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@WebServlet("/reg")
// TODO: убрать бизнесс логику из интерфейса
public class RegisterServlet extends HttpServlet {


    private static MessageDigest digest;

    static {
        try {
            digest = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    // TODO: проверки на соотвитсвие почты почту пароль чтобы не  простой и т.д.
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username, password, email;
        String page = PagesСonfig.regUrl;

        try {

            digest.reset();
            digest.update(req.getParameter("psw").getBytes("utf8"));

            username = req.getParameter("uname");
            email = req.getParameter("email");
            password = String.format("%040x", new BigInteger(1, digest.digest()));

            if (UserDAO.isUserExist(username)) {
                req.setAttribute("ErrorMessage", "This user already exist");


            } else {

                try {
                    UserDAO.insertUser(username, email, password);
                    CartDAO.insertCart(UserDAO.getUserIdByUsernameAndEmailAndPassword(username, email, password));
                    req.setAttribute("OKMessage", "Registration completed successfully");
                    page = PagesСonfig.loginUrl;

                } catch (Exception e) {
                    req.setAttribute("ErrorMessage", "Server error");
                }
            }

        } catch (NullPointerException e) {
            req.setAttribute("ErrorMessage", "Wrong username register data");
        }


        resp.sendRedirect(super.getServletContext().getContextPath()+page);

    }
}

