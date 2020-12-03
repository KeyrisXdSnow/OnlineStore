package servlet.user.cart;

import config.PagesСonfig;
import utils.CartUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/increaseProductAmount")
public class IncreaseProductAmountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            long productId = Long.parseLong(req.getParameter("productId"));
            CartUtils.increaseProductAmount(req.getSession(), productId);

        } catch (NumberFormatException | NullPointerException | ClassCastException e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(PagesСonfig.cartPage);
        dispatcher.forward(req, resp);
    }
}
