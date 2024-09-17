package main.interfaces;

import main.users.User;
import java.util.Scanner;

public interface UserLoginService<T extends User> {
    void login(Scanner scanner, T user);
}