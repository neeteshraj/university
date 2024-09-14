import main.services.UserService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Welcome to the University System");
            System.out.println("1. Enter the Application");
            System.out.println("2. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    showApplicationMenu(scanner);
                    break;
                case 2:
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option, try again.");
            }
        }
    }

    private static void showApplicationMenu(Scanner scanner) {
        boolean inAppMenu = true;
        while (inAppMenu) {
            System.out.println("Application Menu");
            System.out.println("1. Create Account (Student/Professor)");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    UserService.createUser(scanner);
                    break;
                case 2:
                    UserService.loginUser(scanner);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    inAppMenu = false;
                    break;
                default:
                    System.out.println("Invalid option, try again.");
            }
        }
    }
}
