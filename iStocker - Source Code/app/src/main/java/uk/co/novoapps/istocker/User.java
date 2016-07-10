package uk.co.novoapps.istocker;

public class User {

    String name, username, email, password;

    public User(String name, String username, String email, String password){
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String email, String password){
        this.email = email;
        this.password = password;
        this.username = "";
        this.name = "";
    }
}
