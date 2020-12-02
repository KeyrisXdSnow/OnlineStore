package servlet;

import config.PagesСonfig;
import model.beans.Cart;
import model.beans.Product;
import model.dao.CartDAO;
import utils.AppUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addProduct")
public class AddProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cart cart = AppUtils.getLoginedUserCart(req.getSession());
        long id = Long.parseLong(req.getParameter("productId"));

        Product product = AppUtils.getCatalog(req.getSession())
                .keySet()
                .stream()
                .filter(prod -> prod.getId() == id  )
                .findFirst()
                .orElse(null);

        CartDAO.addProductToBasket(cart,product);

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(PagesСonfig.indexPage);
        dispatcher.forward(req, resp);

    }


}
