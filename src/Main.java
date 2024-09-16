import main.services.UserService;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the University System");

        while (true) {
            System.out.println("1. Enter the Application");
            System.out.println("2. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                boolean stayInApp = true;

                while (stayInApp) {
                    System.out.println("Application Menu");
                    System.out.println("1. Create Account (Student/Professor)");
                    System.out.println("2. Login (Student/Professor)");
                    System.out.println("3. Admin Login");
                    System.out.println("4. Exit");

                    int appChoice = scanner.nextInt();
                    scanner.nextLine();

                    switch (appChoice) {
                        case 1:
                            UserService.createUser(scanner);
                            break;
                        case 2:
                            UserService.loginUser(scanner);
                            break;
                        case 3:
                            UserService.adminLogin(scanner);
                            break;
                        case 4:
                            System.out.println("Exiting...");
                            stayInApp = false;
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                }
            } else if (choice == 2) {
                System.out.println("Exiting...");
                scanner.close();
                return;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
