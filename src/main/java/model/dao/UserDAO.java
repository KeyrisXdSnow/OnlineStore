package model.dao;


import config.DatabaseConfig;
import model.beans.User;
import model.entities.Datebase;
import model.entities.Role;
import utils.DatebaseService;
import utils.builders.UserBuilder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

import service.BCrypt;

public class UserDAO {

    private static final Datebase database;

    static {
        database = DatabaseConfig.getDatebase();
    }

    public static User getUserByUsername(String username, String password) {

        Connection connection = DatebaseService.connectToBD(database);

        try {

            password = BCrypt.hashpw(password, getSaleByUsername(username));
            String sql = String.format("SELECT * FROM users WHERE login='%s' AND hash_password='%s'", username, password);
            ResultSet resultSet = DatebaseService.executeQuery(sql, connection);

            try {
                resultSet.next();
                long id = Integer.parseInt(resultSet.getString(1));
                long role_id = Integer.parseInt(resultSet.getString(5));
                String email = resultSet.getString(3);
                double balance = Double.parseDouble(resultSet.getString(7));

                return new UserBuilder()
                        .withId(id)
                        .withUsername(username)
                        .withEmail(email)
                        .withPassword(password)
                        .withRole(getUserRoleByRoleId(role_id))
                        .withBalance(balance).getUser();

            } catch (SQLException | NumberFormatException throwables) {
                throwables.printStackTrace();
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        } finally {
            DatebaseService.closeConnection(connection);
        }

        return null;
    }

    public static String getSaleByUsername(String username) {

        Connection connection = DatebaseService.connectToBD(database);
        try {

            String insertSql = String.format("SELECT salt FROM users WHERE login='%s'", username);
            ResultSet resultSet = DatebaseService.executeQuery(insertSql, connection);
            resultSet.next();
            return resultSet.getString(1);

        } catch (NullPointerException | SQLException e) {

            e.printStackTrace();
            return null;

        } finally {

            DatebaseService.closeConnection(connection);

        }

    }

    public static boolean isUserExist(String username) {

        Connection connection = DatebaseService.connectToBD(database);

        try {
            String sql = String.format("SELECT COUNT(id) FROM users WHERE login='%s'", username);

            ResultSet resultSet = DatebaseService.executeQuery(sql, connection);

            while (resultSet.next()) {

                long idCount = Integer.parseInt(resultSet.getString(1));

                return idCount != 0;

            }

        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        } finally {
            DatebaseService.closeConnection(connection);
        }

        return false;
    }

    public static void insertUser(String username, String email, String password) {

        Connection connection = DatebaseService.connectToBD(database);
        try {

            String salt = BCrypt.gensalt(12);
            password = BCrypt.hashpw(password, salt);

            String insertSql = String.format(
                    "INSERT INTO users(login,email,hash_password,id_role,salt) VALUES ('%s','%s','%s',(SELECT id FROM roles WHERE role_name='USER'),'%s')",
                    username,
                    email,
                    password,
                    salt
            );

            DatebaseService.execute(insertSql, connection);

        } catch (NullPointerException e) {
            e.printStackTrace();
        } finally {
            DatebaseService.closeConnection(connection);
        }

    }

    private static Role getUserRoleByRoleId(long roleId) {

        String getRoleIdSql = String.format("SELECT role_name FROM roles WHERE id=%d", roleId);

        Connection connection = DatebaseService.connectToBD(database);
        try {

            ResultSet roleSet = DatebaseService.executeQuery(getRoleIdSql, connection);
            roleSet.next();

            return Role.valueOf(roleSet.getString(1));


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatebaseService.closeConnection(connection);
        }

        return null;

    }

    public static int getUserIdByUsernameAndEmailAndPassword(String username, String email, String password) {


        Connection connection = DatebaseService.connectToBD(database);

        try {
            password = BCrypt.hashpw(password, getSaleByUsername(username));

            String sql = String.format("SELECT id FROM users WHERE login='%s' AND email='%s' AND hash_password='%s'",
                    username, email, password);

            ResultSet resultSet = DatebaseService.executeQuery(sql, connection);
            resultSet.next();
            return Integer.parseInt(resultSet.getString(1));

        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        } finally {
            DatebaseService.closeConnection(connection);
        }

        return -1;
    }

    public static void updateBalanceByUserId(long userId, double newBalance) throws Exception {

        Connection connection = DatebaseService.connectToBD(database);
        try {

            String insertSql = String.format(Locale.ROOT,"UPDATE users SET balance = %f WHERE id=%d", newBalance, userId);

            DatebaseService.execute(insertSql, connection);
        } finally {
            DatebaseService.closeConnection(connection);
        }

    }


}
