package utils;

import model.entities.Datebase;

import java.sql.*;

public class DatebaseService {

    public static Connection connectToBD(Datebase datebase)  {

        Connection connection = null;
        try {

            //TODO: Move data to private config file
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(datebase.getUrl(), datebase.getUsername(), datebase.getPassword());

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        System.out.println("\nDatabase Connection Established...");    //TODO: Add logging
        return connection;

    }

    /**
     * This method is used to execute the SQL statements witch retrieve some date from database
     * @param sql - request in mysql
     * @param connection -
     * @return
     */
    public static ResultSet executeQuery(String sql, Connection connection) {

        ResultSet resultSet = null;

        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;

    }

    /**
     * This method is used to any kind of SQL statements.
     * @param sql
     * @param connection
     * @return
     */
    public static void execute(String sql, Connection connection) {

        boolean resultSet;

        try {

            Statement statement = connection.createStatement();
            resultSet = statement.execute(sql);

            if (!resultSet) throw new SQLException("User was not inserted into the database. SQL: " + sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public static void closeConnection(Connection connection) {

        try {

            connection.close();

        } catch (ClassCastException | SQLException e) {
            e.printStackTrace();
        }

    }


}
