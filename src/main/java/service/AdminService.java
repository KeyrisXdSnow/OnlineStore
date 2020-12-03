package service;

import model.beans.User;
import model.dao.AdminDAO;
import model.entities.UserStatus;

import java.util.ArrayList;
import java.util.List;

public class AdminService {

    private static List<User> banUserList;
    private static List<User> unbanUserList;

    public static void loadUsers() throws NullPointerException {

        var users = AdminDAO.getUsers();

        banUserList = new ArrayList<>();
        unbanUserList = new ArrayList<>();

        users.stream().forEach( (user) -> {

            if (user.getStatus().equals(UserStatus.ACTIVE)) unbanUserList.add(user);
            if (user.getStatus().equals(UserStatus.BAN)) banUserList.add(user);

        });

    }

    public static List<User> getBanUserList() {
        return banUserList;
    }

    public static List<User> getUnbanUserList() {
        return unbanUserList ;
    }
}
