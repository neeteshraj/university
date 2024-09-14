package main.users;

public class Administrator extends User {
    public Administrator(String name, String email, String password) {
        super(name, email, password);
    }

    @Override
    public void displayMenu() {
        System.out.println("Admin Menu: \n1. Manage Courses \n2. Manage Students \n3. Handle Complaints \n4. Go Back");
    }
}