package servlet.editCatalog;

import config.PagesСonfig;
import utils.AdminUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/removeFromStore")
public class RemoveFromStoreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String strId = req.getParameter(AdminUtils.inStoreProductIdAttributeName);
        long id = Long.parseLong(strId);

        AdminUtils.removeProductFromStore(req.getSession(),id);

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(PagesСonfig.editCatalogUrl);
        dispatcher.forward(req, resp);
    }
}
