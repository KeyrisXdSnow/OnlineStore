package servlet.admin.editUser;

import config.PagesСonfig;
import utils.AdminUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editUser")
public class GiveUserListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        AdminUtils.loadUserList(req.getSession());

        resp.sendRedirect(super.getServletContext().getContextPath() + PagesСonfig.editUserPage);

    }
}
