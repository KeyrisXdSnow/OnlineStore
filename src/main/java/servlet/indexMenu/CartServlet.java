package servlet.indexMenu;

import model.beans.Cart;
import utils.CartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cart productCart = (Cart) req.getSession().getAttribute("cart");

        var cost = CartService.calculatePrice(productCart.getProductList());
        req.getSession().setAttribute("orderCost", cost);
        req.getSession().setAttribute("productList","123");

        var sas = req.getSession().getAttributeNames();

        resp.sendRedirect(super.getServletContext().getContextPath() + "/cart.jsp");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
        super.doGet(req, resp);
    }
}
