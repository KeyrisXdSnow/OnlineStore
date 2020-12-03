package servlet.user.cart;

import config.PagesСonfig;
import utils.CartUtils;
import utils.exceptions.NegativeBalanceException;

import javax.servlet.RequestDispatcher;
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

        try {
            CartUtils.makeOrder(req.getSession());
            req.setAttribute("OKMessage", "Заказ успешно оформлен");

        } catch (NegativeBalanceException e) {

            req.setAttribute("ErrorMessage", "На вашем счёте недостаточно средств");

        }

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(PagesСonfig.cartPage);
        dispatcher.forward(req, resp);


    }
}
