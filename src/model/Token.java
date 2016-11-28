package model;

/**
 * Created by Tastum on 21/11/2016.
 */
public class Token {

    private String token;
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
