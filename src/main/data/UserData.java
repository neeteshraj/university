package main.data;

import main.users.Administrator;
import main.users.User;

import java.util.ArrayList;
import java.util.List;

public class UserData {
    private static final List<User> users = new ArrayList<>();

    static {
        users.add(new Administrator("Admin", "admin@university.com", "admin123"));
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public static User loginUser(String email, String password) {
        for (User user : users) {
            if (user.login(email, password)) {
                return user;
            }
        }
        return null;
    }

    public static boolean isEmailTaken(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
}
