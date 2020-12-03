package utils;

import model.beans.Product;
import model.beans.User;
import model.dao.AdminDAO;
import model.dao.ProductDAO;
import model.entities.UserStatus;
import service.AdminService;
import service.CatalogService;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AdminUtils {

    // view edit Catalog
    public static final String inStoreProductIdAttribute = "inStoreId";
    public static final String inStoreProductsAttribute = "InStore";

    public static final String notInStoreProductIdAttribute = "notInStoreId";
    public static final String notInStoreProductsAttribute = "NotInStore";

    // view edit user list (ban/unban)

    public static final String banUserListAttribute = "banUserList";
    public static final String unbanUserListAttribute = "unbanUserList";

    private static LinkedHashMap<Product, String> notInStoreList;

    static {

        notInStoreList = new LinkedHashMap<>();
    }


    public static void removeAdminAttributes (HttpSession session) {
        removeBanUserList(session);
        removeUnbanUserList(session);
        removeProductListNotInStore(session);

    }



    public static void storeProductListNotInStore(HttpSession session, LinkedHashMap<? extends Product, String> productsNotInStore) {
        session.setAttribute(notInStoreProductsAttribute, productsNotInStore);
    }

    public static void removeProductListNotInStore(HttpSession session) {
        session.removeAttribute(notInStoreProductsAttribute);
    }

    public static LinkedHashMap<? extends Product, String> getProductListNotInStore(HttpSession session) {
        return (LinkedHashMap<? extends Product, String>) session.getAttribute(notInStoreProductsAttribute);
    }

    public static void storeBanUserList(HttpSession session, List<User> userList) {
        session.setAttribute(banUserListAttribute, userList);
    }

    public static void removeBanUserList(HttpSession session) {
        session.removeAttribute(banUserListAttribute);
    }

    public static List<User> getBanUserList(HttpSession session) {
        return (List<User>) session.getAttribute(banUserListAttribute);
    }

    public static void storeUnbanUserList(HttpSession session, List<User> userList) {
        session.setAttribute(unbanUserListAttribute, userList);
    }

    public static void removeUnbanUserList(HttpSession session) {
        session.removeAttribute(unbanUserListAttribute);
    }

    public static List<User> getUnbanUserList(HttpSession session) {
        return (List<User>) session.getAttribute(unbanUserListAttribute);
    }






    public static void addProductToStore(HttpSession session, long productId) {

        try {


            notInStoreList = (LinkedHashMap<Product, String>) AdminUtils.getProductListNotInStore(session);
            var catalog = (LinkedHashMap<Product, String>) AppUtils.getCatalog(session);

            Map.Entry<Product, String> product = notInStoreList.entrySet().stream()
                    .filter(productStringEntry -> productStringEntry.getKey().getId() == productId)
                    .findFirst()
                    .orElse(null);

            notInStoreList.remove(product.getKey());
            catalog.put(product.getKey(), product.getValue());
            ProductDAO.updateProductInStoreByProductId(productId, 1);

            AdminUtils.storeProductListNotInStore(session,notInStoreList);
            AppUtils.storeCatalog(session, catalog);

        } catch (NullPointerException | NumberFormatException e) {
            e.printStackTrace();
        }

    }

    public static void removeProductFromStore(HttpSession session, long productId) {

        try {

            notInStoreList = (LinkedHashMap<Product, String>) session.getAttribute(notInStoreProductsAttribute);
            var catalog = (LinkedHashMap<Product, String>) AppUtils.getCatalog(session);

            Map.Entry<Product, String> product = catalog.entrySet().stream()
                    .filter(productStringEntry -> productStringEntry.getKey().getId() == productId)
                    .findFirst()
                    .orElse(null);

            catalog.remove(product.getKey());
            notInStoreList.put(product.getKey(), product.getValue());
            ProductDAO.updateProductInStoreByProductId(productId, 0);

            AdminUtils.storeProductListNotInStore(session,notInStoreList);
            AppUtils.storeCatalog(session, catalog);

        } catch (NullPointerException | NumberFormatException e) {
            e.printStackTrace();
        }

    }


    public static void loadProductsLists(HttpSession session) {
        try {

            if (getProductListNotInStore(session) == null) {

                CatalogService.loadCatalog();
                session.setAttribute(AdminUtils.inStoreProductsAttribute, CatalogService.getProductsInStore());
                session.setAttribute(AdminUtils.notInStoreProductsAttribute, CatalogService.getProductsNotInStore());
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public static void unbanUser(HttpSession session, long userId) {

        try {

            var statuses = AdminDAO.getUsersStatus();
            long statusId = statuses.entrySet().stream().filter(entry -> entry.getValue().equals(UserStatus.ACTIVE)).findFirst().get().getKey();

            AdminDAO.updateUserByUserId(userId,statusId);

            var unbanUserList = AdminUtils.getUnbanUserList(session);
            var banUserList = AdminUtils.getBanUserList(session);

            User user = banUserList.stream().filter( us -> us.getId() == userId ).findFirst().orElse(null);

            banUserList.remove(user);
            unbanUserList.add(user);

            AdminUtils.storeBanUserList(session,banUserList);
            AdminUtils.storeUnbanUserList(session, unbanUserList);


        } catch (NullPointerException | NumberFormatException e) {
            e.printStackTrace();
        }

    }

    public static void banUser(HttpSession session, long userId) {

        try {

            var statuses = AdminDAO.getUsersStatus();
            long statusId = statuses.entrySet().stream().filter(entry -> entry.getValue().equals(UserStatus.BAN)).findFirst().get().getKey();

            AdminDAO.updateUserByUserId(userId,statusId);

            var unbanUserList = AdminUtils.getUnbanUserList(session);
            var banUserList = AdminUtils.getBanUserList(session);

            User user = unbanUserList.stream().filter( us -> us.getId() == userId ).findFirst().orElse(null);

            unbanUserList.remove(user);
            banUserList.add(user);

            AdminUtils.storeBanUserList(session,banUserList);
            AdminUtils.storeUnbanUserList(session, unbanUserList);


        } catch (NullPointerException | NumberFormatException e) {
            e.printStackTrace();
        }

    }

    public static void loadUserList(HttpSession session) {

        try {

            if (getBanUserList(session) == null || getUnbanUserList(session) == null ) {

                AdminService.loadUsers();

                session.setAttribute(banUserListAttribute, AdminService.getBanUserList());
                session.setAttribute(unbanUserListAttribute, AdminService.getUnbanUserList());
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

}
