package controllers;

import Encrypters.*;
import database.DBConnector;
import model.Token;
import model.User;

import java.sql.SQLException;

/**
 * Created by Tastum on 19/10/2016.
 */
public class TokenController {

    DBConnector db = new DBConnector();

    public Token authenticate(String username, String password) throws SQLException {

        // Authenticate the user using the credentials provided

//        String token;

        Token token = new Token();

        User foundUser = db.authenticate(username, password);
        if (foundUser != null) {

            token.setToken(Crypter.buildToken("abcdefghijklmnopqrstuvxyz1234567890@&%!?", 25));
            token.setUser(foundUser);

            db.addToken(token.getToken(), foundUser.getUserID());

        } else {
            token = null;
        }
        //Retunerer en access token til klienten.
        return token;


    }

    public User getUserFromTokens(String token) throws SQLException {
        DBConnector db = new DBConnector();
        User user = db.getUserFromToken(token);
        db.close();
        return user;

    }

    public boolean deleteToken(String token) throws SQLException{
        DBConnector db = new DBConnector();
        boolean deleteToken = db.deleteToken(token);
        db.close();
        return deleteToken;

    }
}
