package model.dao;

import model.beans.Cart;
import model.beans.Product;
import config.DatabaseConfig;
import model.entities.Datebase;
import utils.DatebaseService;
import utils.builders.CartBuilder;
import utils.builders.ProductBuilder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {

    private static final Datebase database;

    static {
        database = DatabaseConfig.getDatebase();
    }

    // TODO: заменить boolean
    // TODO: добавить обновление cost таблицы cart
    // TODO: исправитьдобавление в productList. Убрать дефолнтые данные у Product
    public static boolean addProductToBasket(Cart cart, long productId) {

        Connection connection = DatebaseService.connectToBD(database);
        try {

            long cardId = getCartIdByUserId(cart.getUserId());

            if (cardId == -1) throw new NullPointerException();

            String insertSql = String.format(
                    "INSERT INTO cart_item (id_cart, id_user, id_product) VALUES(%d,%d,%d) ON DUPLICATE KEY UPDATE amount=amount+1",
                    cart.getCartId(), cart.getUserId(), productId
            );

            List productList = cart.getProductList();
            productList.add(new ProductBuilder().withName("").withCost(100).withSpecification(null).getProduct());
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

    public static Cart getCartByUserId(long userId) {

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
                    .withProductList(getProductListByBasketId(cartId))
                    .getCart();

        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DatebaseService.closeConnection(connection);
        }

    }

    public static List<? extends Product> getProductListByBasketId(long cartId) {

        Connection connection = DatebaseService.connectToBD(database);
        try {

            String insertSql = String.format("SELECT * FROM cart_item WHERE id_cart=%d", cartId);
            ResultSet resultSet = DatebaseService.executeQuery(insertSql, connection);

            List productList = new ArrayList<>();
            while (resultSet.next()) {
                for (int i = 0; i < Long.parseLong(resultSet.getString(4)); i++) {
                    productList.add(new ProductBuilder().withName("").withCost(100).getProduct());
                }
            }

            return productList;

        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DatebaseService.closeConnection(connection);
        }

    }

}
