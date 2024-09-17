package main.services;

import main.interfaces.UserLoginService;
import main.users.Administrator;
import java.util.Scanner;

public class AdminLoginService implements UserLoginService<Administrator> {

    @Override
    public void login(Scanner scanner, Administrator admin) {
        System.out.println("Login successful! Welcome, " + admin.getName() + " (Administrator)");

        while (true) {
            admin.displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    AdminService.manageCourses(scanner);
                    break;
                case 2:
                    AdminService.manageStudents(scanner);
                    break;
                case 3:
                    AdminService.handleComplaintManagement(scanner);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
