package model;

/**
 * Created by Tastum on 21/11/2016.
 */
public class Token {

    private String token;
    private User user;

//    public Token(String token, User foundUser) {
//        this.token = token;
//        this.user = foundUser;
//    }

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
