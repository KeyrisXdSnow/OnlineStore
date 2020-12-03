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


//TODO: пофиксить все урлы на cart servlets

@WebServlet("/cancelProduct")
public class CancelProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            long productId = Long.parseLong(req.getParameter("productId"));
            CartUtils.cancelProduct(req.getSession(), productId);

        } catch (NumberFormatException | NullPointerException e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(PagesСonfig.cartPage);
        dispatcher.forward(req, resp);
    }
}
