package filters;

import config.PagesСonfig;
import model.beans.User;
import utils.AppUtils;
import utils.SecurityService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/","/*","/index"})
public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String servletPath = request.getServletPath();

        User user = AppUtils.getLoginedUser(request.getSession());

        if (servletPath.equals("/login") ^ servletPath.equals("/login.jsp")) {

            if (user == null) filterChain.doFilter(servletRequest,servletResponse);
            else response.sendRedirect(request.getContextPath()+"/index");

            return;
        }

        // Страницы требующие входа в систему.
        if (SecurityService.isSecurityPage(request)){

            // Если пользователь еще не вошел в систему,
            // Redirect (перенаправить) к странице логина.
            if (user == null) {
                response.sendRedirect(request.getContextPath()+ PagesСonfig.loginUrl);
                return;

            }

            if (!SecurityService.hasPermission(request, user.getRole())) {
                AppUtils.removeLoginedUser(request.getSession());
                response.sendRedirect(request.getContextPath()+ PagesСonfig.loginUrl);
                return;

            }
        }

        filterChain.doFilter(servletRequest,servletResponse);

    }

    @Override
    public void destroy() {

    }
}
