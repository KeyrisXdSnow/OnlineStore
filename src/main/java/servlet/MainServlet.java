package servlet;


import model.beans.Cart;
import model.beans.User;
import model.dao.CartDAO;
import model.dao.ProductDAO;
import utils.AppUtils;
import utils.CartUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/","/?","/index"})
public class MainServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(super.getServletContext().getContextPath());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        User user = (User) req.getSession().getAttribute("userInfo");

        var productList = ProductDAO.getProductListInStore(req.getSession());
        Cart cart = CartDAO.getCartByUserId(user.getId(), productList);

        CartUtils.storeCart(req.getSession(),cart);
        AppUtils.storeCatalog(req.getSession(),productList);

        resp.sendRedirect(super.getServletContext().getContextPath());
    }
}
