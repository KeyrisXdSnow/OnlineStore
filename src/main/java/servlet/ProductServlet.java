package servlet;

import config.PagesСonfig;
import model.beans.Cart;
import model.dao.CartDAO;
import utils.AppService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addProduct")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cart cart = AppService.getLoginedUserCart(req.getSession());
        CartDAO.addProductToBasket(cart,0);

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(PagesСonfig.indexPage);
        dispatcher.forward(req, resp);

    }
}
