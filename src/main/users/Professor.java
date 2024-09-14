package main.users;

public class Professor extends User {
    public Professor(String name, String email, String password) {
        super(name, email, password);
    }

    @Override
    public void displayMenu() {
        System.out.println("Professor Menu: \n1. View Courses \n2. Manage Courses");
    }
}