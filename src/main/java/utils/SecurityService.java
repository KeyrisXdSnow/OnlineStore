package utils;

import config.SecurityConfig;
import model.entities.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SecurityService {

    // Проверить требует ли данный 'request' входа в систему или нет.
    public static boolean isSecurityPage (HttpServletRequest request) {
        String reqUrl = request.getServletPath();

        for (String role :  SecurityConfig.getAllRoles()) {

            List<String> urls = SecurityConfig.getAllUrlsByRole(role);

            if ( urls != null && urls.contains(reqUrl)) return true;

        }
        return false;

    }

    // Проверить имеет ли данный 'request' подходящую роль?
    public static boolean hasPermission(HttpServletRequest request, Role userRoles) {
        String  reqUrl = request.getServletPath();

        for (String role : SecurityConfig.getAllRoles()) {

            if (userRoles.compareTo(Role.valueOf(role)) != 0)
                continue;

            List<String> urls = SecurityConfig.getAllUrlsByRole(role);
            if (urls != null && urls.contains(reqUrl))
                return true;
        }
        return false;
    }






}
