package servlet;

import model.beans.User;
import utils.AppUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/makeOrder")
public class MakeOrderServlet extends HttpServlet {

    // TODO доделать
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        double cost = (double) req.getSession().getAttribute("cost");
        User user = AppUtils.getLoginedUser(req.getSession());

        if (user.getBalance() > cost) {
            user.setBalance(user.getBalance() - cost);

        } else {
            
        }


    }
}
