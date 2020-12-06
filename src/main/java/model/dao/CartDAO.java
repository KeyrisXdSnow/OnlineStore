package model.dao;

import config.DatabaseConfig;
import model.beans.Cart;
import model.beans.Product;
import model.entities.Datebase;
import utils.DatebaseService;
import utils.builders.CartBuilder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;

public class CartDAO {

    private static final Datebase database;

    static {
        database = DatabaseConfig.getDatebase();
    }

    // TODO: заменить boolean
    // TODO: добавить обновление cost таблицы cart
    // TODO: исправитьдобавление в productList. Убрать дефолнтые данные у Product
    public static boolean addProductToBasket(Cart cart, Product product) {

        Connection connection = DatebaseService.connectToBD(database);
        try {

            long cardId = getCartIdByUserId(cart.getUserId());

            if (cardId == -1) throw new NullPointerException();

            String insertSql = String.format(
                    "INSERT INTO cart_item (id_cart, id_user, id_product) VALUES(%d,%d,%d) ON DUPLICATE KEY UPDATE amount=amount+1",
                    cart.getCartId(), cart.getUserId(), product.getId()
            );

            LinkedHashMap productList = cart.getProductList();

            if (productList.containsKey(product)) productList.replace(product, ((int) productList.get(product)) + 1);
            else productList.put(product, 1);

            cart.setProductList(productList);

            DatebaseService.execute(insertSql, connection);

        } catch (NullPointerException e) {

            e.printStackTrace();
            return false;

        } finally {
            DatebaseService.closeConnection(connection);
        }

        return true;
    }


    // TODO: заменить boolean
    public static boolean removeProductFromBasket(long userId, long cartId, long productId) {

        Connection connection = DatebaseService.connectToBD(database);
        try {

            String insertSql = String.format(
                    "DELETE FROM cart_item WHERE id_cart=%d AND id_user=%d AND id_product=%d",
                    cartId, userId, productId
            );

            DatebaseService.execute(insertSql, connection);

        } catch (NullPointerException e) {

            e.printStackTrace();
            return false;

        } finally {

            DatebaseService.closeConnection(connection);

        }

        return true;
    }

    public static void insertCart(long userId) {

        Connection connection = DatebaseService.connectToBD(database);
        try {

            String insertSql = String.format("INSERT INTO carts(id_user) VALUES (%d)", userId);

            DatebaseService.execute(insertSql, connection);

        } catch (NullPointerException e) {
            e.printStackTrace();
        } finally {
            DatebaseService.closeConnection(connection);
        }

    }

    public static long getCartIdByUserId(long userId) {

        Connection connection = DatebaseService.connectToBD(database);
        try {
            String insertSql = String.format("SELECT id_cart FROM carts WHERE id_user=%d", userId);
            ResultSet resultSet = DatebaseService.executeQuery(insertSql, connection);
            resultSet.next();
            return Long.parseLong(resultSet.getString(1));

        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            DatebaseService.closeConnection(connection);
        }

    }

    public static Cart getCartByUserId(long userId, LinkedHashMap<? extends Product, String> catalog) {

        Connection connection = DatebaseService.connectToBD(database);
        try {

            String insertSql = String.format("SELECT * FROM carts WHERE id_user=%d", userId);
            ResultSet resultSet = DatebaseService.executeQuery(insertSql, connection);
            resultSet.next();

            long cartId = Long.parseLong(resultSet.getString(1));

            return new
                    CartBuilder()
                    .withCartId(cartId)
                    .withUserId(userId)
                    .withProductList(getProductListByCartId(cartId, catalog))
                    .getCart();

        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DatebaseService.closeConnection(connection);
        }

    }

    public static LinkedHashMap<? extends Product, Integer> getProductListByCartId(long cartId, LinkedHashMap<? extends Product, String> catalog) {

        Connection connection = DatebaseService.connectToBD(database);
        try {

            String insertSql = String.format("SELECT * FROM cart_item WHERE id_cart=%d", cartId);
            ResultSet resultSet = DatebaseService.executeQuery(insertSql, connection);

            LinkedHashMap productList = new LinkedHashMap();

            while (resultSet.next()) {

                long productId = Long.parseLong(resultSet.getString(3));
                long amount = Long.parseLong(resultSet.getString(4));

                catalog.keySet().forEach(product -> {
                    if (product.getId() == productId)
                        productList.put(product, amount);
                });

            }

            return productList;

        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DatebaseService.closeConnection(connection);
        }
    }


    public static void deleteProductFromCartByCartIdAndProductId(long cartId, long productId) {

        Connection connection = DatebaseService.connectToBD(database);
        try {

            String insertSql = String.format("DELETE FROM cart_item WHERE id_cart=%d AND id_product=%d", cartId, productId);
            DatebaseService.execute(insertSql, connection);

        } catch (NullPointerException e) {
            e.printStackTrace();

        } finally {
            DatebaseService.closeConnection(connection);
        }
    }

    public static void deleteAllProductFromCartByCId(long cartId) {

        Connection connection = DatebaseService.connectToBD(database);
        try {

            String insertSql = String.format("DELETE FROM cart_item WHERE id_cart=%d", cartId);
            DatebaseService.execute(insertSql, connection);

        } catch (NullPointerException e) {
            e.printStackTrace();

        } finally {
            DatebaseService.closeConnection(connection);
        }
    }



    public static void updateProductAmountByProductId(long cartId, long productId, int newValue) {

        Connection connection = DatebaseService.connectToBD(database);
        try {

            String insertSql = String.format("UPDATE cart_item SET amount=%d WHERE id_cart=%d AND id_product=%d",
                    newValue, cartId, productId);
            DatebaseService.executeUpdate(insertSql, connection);

        } catch (NullPointerException e) {
            e.printStackTrace();

        } finally {
            DatebaseService.closeConnection(connection);
        }
    }
}


