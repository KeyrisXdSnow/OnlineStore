package model.dao;

import config.DatabaseConfig;
import model.beans.User;
import model.entities.Datebase;
import model.entities.Role;
import model.entities.UserStatus;
import utils.DatebaseService;
import utils.builders.UserBuilder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AdminDAO {

    private static final Datebase database;

    static {
        database = DatabaseConfig.getDatebase();
    }

    public static List<User> getUsers() {

        Connection connection = DatebaseService.connectToBD(database);
        var statuses = getUsersStatus();

        try {
            String sql = String.format("SELECT * FROM users WHERE id_role=(SELECT id FROM roles WHERE role_name='USER')");
            ResultSet resultSet = DatebaseService.executeQuery(sql, connection);
            List<User> userList = new ArrayList<>();

            while (resultSet.next()) {

                long id = Integer.parseInt(resultSet.getString(1));
                String username = resultSet.getString(2);
                String email = resultSet.getString(3);
                double balance =  Double.parseDouble(resultSet.getString(7));
                UserStatus status = statuses.get(Long.parseLong(resultSet.getString(8)));

                userList.add(new UserBuilder()
                        .withId(id)
                        .withUsername(username)
                        .withEmail(email)
                        .withBalance(balance)
                        .withRole(Role.USER)
                        .withStatus(status)
                        .getUser());
            }

            return userList;

        } catch (SQLException | NumberFormatException throwables) {
            throwables.printStackTrace();
        } finally {
            DatebaseService.closeConnection(connection);
        }
        return null;
    }

    public static HashMap<Long, UserStatus> getUsersStatus () {

        Connection connection = DatebaseService.connectToBD(database);

        try {
            String sql = String.format("SELECT * FROM user_status");
            ResultSet resultSet = DatebaseService.executeQuery(sql, connection);

            HashMap<Long,UserStatus> statuses = new HashMap<>();

            while (resultSet.next()) {
                statuses.put(Long.parseLong(resultSet.getString(1)),UserStatus.valueOf(resultSet.getString(2)));
            }

            return statuses;

        } catch (SQLException | NumberFormatException throwables) {
            throwables.printStackTrace();
        } finally {
            DatebaseService.closeConnection(connection);
        }
        return null;

    }

    public static void updateUserByUserId(long userId, long newValue) {

        Connection connection = DatebaseService.connectToBD(database);
        try {

            String insertSql = String.format("UPDATE users SET status=%d WHERE id=%d", newValue, userId);
            DatebaseService.execute(insertSql, connection);

        } catch (NullPointerException e) {
            e.printStackTrace();

        } finally {
            DatebaseService.closeConnection(connection);
        }
    }
}
