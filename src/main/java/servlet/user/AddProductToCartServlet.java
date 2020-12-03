package servlet.user;

import config.PagesСonfig;
import model.beans.Cart;
import model.beans.Product;
import model.dao.CartDAO;
import utils.AppUtils;
import utils.UserUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addProduct")
public class AddProductToCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            long id = Long.parseLong(req.getParameter("productId"));
            UserUtils.addProductToCart(req.getSession(),id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(PagesСonfig.indexPage);
        dispatcher.forward(req, resp);

    }


}
