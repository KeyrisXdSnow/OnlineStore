package servlet.user;

import model.beans.User;
import model.dao.UserDAO;
import utils.AppUtils;
import utils.UserUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/giveMoney")
public class GiveMoneyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        UserUtils.addMoneyOnBalance(req.getSession());

        resp.sendRedirect(super.getServletContext().getContextPath());
    }
}
