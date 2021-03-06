package servlet.admin.editCatalog;

import config.PagesСonfig;
import utils.AdminUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addToStore")
public class AddToStoreServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String strId = req.getParameter(AdminUtils.notInStoreProductIdAttribute);
            long id = Long.parseLong(strId);

            AdminUtils.addProductToStore(req.getSession(), id);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(PagesСonfig.editCatalogUrl);
        dispatcher.forward(req, resp);
    }

}