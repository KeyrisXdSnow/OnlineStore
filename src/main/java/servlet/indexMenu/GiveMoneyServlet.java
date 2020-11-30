package servlet.indexMenu;

import model.beans.User;
import model.dao.UserDAO;
import utils.AppService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/giveMoney")
public class GiveMoneyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("userInfo");
        user.setBalance(user.getBalance()+50);

        try {

            UserDAO.updateBalanceByUserId(user.getId(),user.getBalance());
            AppService.storeLoginedUserInfo(req.getSession(),user);

        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.sendRedirect(super.getServletContext().getContextPath());
    }
}
