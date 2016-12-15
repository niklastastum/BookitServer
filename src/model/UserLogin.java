package model;

public class UserLogin {

    //    Model klasse for UserLogin. Bruges kun til adskillelse af brugernavn og adgangskode v. login

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
