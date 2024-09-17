package main.services;

import main.interfaces.UserLoginService;
import main.users.Professor;

import java.util.Scanner;

public class ProfessorLoginService implements UserLoginService<Professor> {

    @Override
    public void login(Scanner scanner, Professor professor) {
        System.out.println("Login successful! Welcome, " + professor.getName() + " (Professor)");

        while (true) {
            professor.displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    professor.viewAssignedCourses();
                    break;
                case 2:
                    professor.manageCourses();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}