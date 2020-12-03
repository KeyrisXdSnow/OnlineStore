package servlet.admin.editUser;

import config.PagesСonfig;
import utils.AdminUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpRequest;

@WebServlet("/banUser")
public class BanUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long userId = Long.parseLong(req.getParameter("userId"));
        AdminUtils.banUser(req.getSession(), userId);

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(PagesСonfig.editUserPage);
        dispatcher.forward(req, resp);
    }
}
