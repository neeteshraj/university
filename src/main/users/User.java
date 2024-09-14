package main.users;

public abstract class User {
    public String name;
    public String email;
    protected String password;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    public abstract void displayMenu();

    public boolean login(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }
}

