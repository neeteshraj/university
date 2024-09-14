package main.users;

public class Administrator extends User {
    public Administrator(String name, String email, String password) {
        super(name, email, password);
    }

    @Override
    public void displayMenu() {
        System.out.println("Admin Menu: \n1. Manage Courses");
    }
}