package utils.builders;

import model.beans.User;
import model.entities.Role;

public class UserBuilder {

    private User user;

    public UserBuilder() {
        user = new User();
    }

    public User getUser() {
        return user;
    }

    public UserBuilder withId (long id) {
        this.user.setId(id);
        return this;
    }

    public UserBuilder withUsername (String username) {
        this.user.setUsername(username);
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.user.setEmail(email);
        return this;
    }

    public UserBuilder withPassword (String password) {
        this.user.setPassword(password);
        return this;
    }

    public UserBuilder withBalance(double balance) {
        this.user.setBalance(balance);
        return this;
    }

    public UserBuilder withRole(Role role) {
        this.user.setRole(role);
        return this;
    }
}
