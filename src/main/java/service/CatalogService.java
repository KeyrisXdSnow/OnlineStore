package service;

import model.beans.Product;
import model.dao.ProductDAO;

import java.util.LinkedHashMap;

public class CatalogService {

    private static LinkedHashMap<Product, String> productsInStore;
    private static LinkedHashMap<Product, String> productsNotInStore;

    public static void loadCatalog () throws NullPointerException {

        var catalog = ProductDAO.getProductList();

        productsInStore = new LinkedHashMap<>();
        productsNotInStore = new LinkedHashMap<>();

        catalog.forEach( (product, s) -> {

            if (product.isInStore()) productsInStore.put(product,s);
            else productsNotInStore.put(product,s);
        });

    }

    public static LinkedHashMap<? extends Product, String> getProductsInStore() {
        return productsInStore;
    }

    public static LinkedHashMap<? extends Product, String> getProductsNotInStore() {
        return productsNotInStore;
    }
}
