package config;

import model.entities.Role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class SecurityConfig {

    private static final HashMap<String, List<String>> securityConfig = new HashMap<>();

    static {
        init();
    }

    private static void init() {
        List<String> userAvailableURLs = new ArrayList<>();
        userAvailableURLs.add(PagesСonfig.indexPage);
        userAvailableURLs.add(PagesСonfig.indexUrl);
        userAvailableURLs.add(PagesСonfig.cartPage);
        userAvailableURLs.add(PagesСonfig.cartUrl);
        userAvailableURLs.add("/");

        List<String> adminAvailableURLs = new ArrayList<>();
        adminAvailableURLs.add(PagesСonfig.adminPage);
        adminAvailableURLs.add(PagesСonfig.indexPage);
        adminAvailableURLs.add(PagesСonfig.indexUrl);

        securityConfig.put(Role.ADMIN.toString(), adminAvailableURLs);
        securityConfig.put(Role.USER.toString(), userAvailableURLs);
    }

    public static Set<String> getAllRoles() {
        return securityConfig.keySet();
    }

    public static List<String> getAllUrlsByRole(String role) {
        return securityConfig.get(role);
    }

}
