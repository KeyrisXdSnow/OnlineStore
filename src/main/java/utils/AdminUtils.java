package utils;

import model.beans.Product;
import model.dao.ProductDAO;
import service.CatalogService;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

public class AdminUtils {

    public static final String inStoreProductIdAttributeName = "inStoreId";
    public static final String inStoreProductsAttributeName = "InStore";

    public static final String notInStoreProductIdAttributeName = "notInStoreId";
    public static final String notInStoreProductsAttributeName = "NotInStore";

    private static LinkedHashMap<Product, String> notInStoreList;

    static {

        notInStoreList = new LinkedHashMap<>();
    }

    public static void storeProductListNotInStore (HttpSession session, LinkedHashMap<? extends Product,String> productsNotInStore) {
        session.setAttribute(notInStoreProductsAttributeName,productsNotInStore);
    }


    public static void removeProductListNotInStore (HttpSession session) {
        session.removeAttribute(notInStoreProductsAttributeName);
    }

    public static LinkedHashMap<? extends Product,String> getProductListNotInStore (HttpSession session) {
        return (LinkedHashMap<? extends Product, String>) session.getAttribute(notInStoreProductsAttributeName);
    }


    public static void addProductToStore(HttpSession session, long productId) {

        try {

            notInStoreList = (LinkedHashMap<Product, String>) session.getAttribute(notInStoreProductsAttributeName);
            var catalog = (LinkedHashMap<Product, String>) AppUtils.getCatalog(session);

            Map.Entry<Product, String> product = notInStoreList.entrySet().stream()
                    .filter(productStringEntry -> productStringEntry.getKey().getId() == productId)
                    .findFirst()
                    .orElse(null);

            notInStoreList.remove(product.getKey());
            catalog.put(product.getKey(), product.getValue());
            ProductDAO.updateProductInStoreByProductId(productId, 1);

            session.setAttribute(notInStoreProductsAttributeName, notInStoreList);
            AppUtils.storeCatalog(session, catalog);

        } catch (NullPointerException | NumberFormatException e) {
            e.printStackTrace();
        }

    }

    public static void removeProductFromStore(HttpSession session, long productId) {

        try {

            notInStoreList = (LinkedHashMap<Product, String>) session.getAttribute(notInStoreProductsAttributeName);
            var catalog = (LinkedHashMap<Product, String>) AppUtils.getCatalog(session);

            Map.Entry<Product, String> product = catalog.entrySet().stream()
                    .filter(productStringEntry -> productStringEntry.getKey().getId() == productId)
                    .findFirst()
                    .orElse(null);

            catalog.remove(product.getKey());
            notInStoreList.put(product.getKey(), product.getValue());
            ProductDAO.updateProductInStoreByProductId(productId, 0);

            session.setAttribute(notInStoreProductsAttributeName, notInStoreList);
            AppUtils.storeCatalog(session, catalog);

        } catch (NullPointerException | NumberFormatException e) {
            e.printStackTrace();
        }

    }

    public static void loadProductsLists (HttpSession session) {
        try {

            if (getProductListNotInStore(session) == null) {

                CatalogService.loadCatalog();
                session.setAttribute(AdminUtils.inStoreProductsAttributeName, CatalogService.getProductsInStore());
                session.setAttribute(AdminUtils.notInStoreProductsAttributeName, CatalogService.getProductsNotInStore());
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

}
