package model.dao;

import config.DatabaseConfig;
import model.beans.Product;
import model.entities.Datebase;
import model.entities.Specification;
import utils.AppUtils;
import utils.DatebaseService;
import utils.builders.ProductBuilder;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;

public class ProductDAO {

    private static final Datebase database;

    static {
        database = DatabaseConfig.getDatebase();
    }

    public static LinkedHashMap<? extends Product, String> getProductListInStore(HttpSession session) {

        Connection connection = DatebaseService.connectToBD(database);
        try {

            String insertSql = String.format("SELECT * FROM catalog WHERE inStore=1");

            ResultSet resultSet = DatebaseService.executeQuery(insertSql, connection);

            LinkedHashMap productList = new LinkedHashMap<Product, String>();

            while (resultSet.next()) {
                long id = Long.parseLong(resultSet.getString(1));
                String name = resultSet.getString(2);
                double cost = Double.parseDouble(resultSet.getString(3));
                Specification specification = new Specification(resultSet.getString(4));

                Product product = new ProductBuilder()
                        .withId(id)
                        .withName(name)
                        .withCost(cost)
                        .withSpecification(specification)
                        .getProduct();

                String url = String.format("resources/img/products/%s.jpg", id);
                productList.put(product, url);

            }
            return productList;

        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DatebaseService.closeConnection(connection);
        }

    }

    public static LinkedHashMap<? extends Product, String> getProductList() {

        Connection connection = DatebaseService.connectToBD(database);
        try {
            String insertSql = String.format("SELECT * FROM catalog");

            ResultSet resultSet = DatebaseService.executeQuery(insertSql, connection);

            LinkedHashMap productList = new LinkedHashMap<Product, String>();

            int i = 1;
            while (resultSet.next()) {
                long id = Long.parseLong(resultSet.getString(1));
                String name = resultSet.getString(2);
                double cost = Double.parseDouble(resultSet.getString(3));
                Specification specification = new Specification(resultSet.getString(4));
                boolean inStore = resultSet.getString(5).equals("1");


                Product product = new ProductBuilder()
                        .withId(id)
                        .withName(name)
                        .withCost(cost)
                        .withSpecification(specification)
                        .withinStore(inStore)
                        .getProduct();

                String url = String.format("resources/img/products/%s.jpg", i++);
                productList.put(product, url);

            }
            return productList;

        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DatebaseService.closeConnection(connection);
        }

    }

    public static void updateProductInStoreByProductId(long productId, int newValue) {

        Connection connection = DatebaseService.connectToBD(database);
        try {

            String insertSql = String.format("UPDATE catalog SET inStore=%d WHERE id_product=%d", newValue, productId);
            DatebaseService.executeUpdate(insertSql, connection);

        } catch (NullPointerException e) {
            e.printStackTrace();
        } finally {
            DatebaseService.closeConnection(connection);
        }

    }

}
