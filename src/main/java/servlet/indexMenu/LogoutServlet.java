package servlet.indexMenu;

import utils.AppUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        AppUtils.removeLoginedUser(req.getSession());
        resp.sendRedirect(super.getServletContext().getContextPath());
    }
}
