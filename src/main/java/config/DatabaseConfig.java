package config;

import model.entities.Datebase;

public class DatabaseConfig {

    private static final String url = "jdbc:MySQL://localhost:3306/onlinestore?serverTimezone=Europe/Minsk&useSSL=false";
    private static final String user = "root";
    private static final String password = "1111";

    private static final Datebase datebase;

    static  {
        datebase = new Datebase(url,user,password);
    }

    public static Datebase getDatebase() {
        return datebase;
    }
}
