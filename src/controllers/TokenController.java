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

        Token token = new Token();

//        Ved at have tilføjet et user-objekt til Token-klassen gør det muligt at returnere begge til klienten
        User foundUser = db.authenticate(username, password);
        if (foundUser != null) {

//            Hvis der er en bruger med de oplysninger, bliver der oprettet en token med disse kriterier.
//            Oprettelse af token sker i Crypter.java

            token.setToken(Crypter.buildToken("abcdefghijklmnopqrstuvxyz1234567890@&%!?", 25));
            token.setUser(foundUser);

//            Dette gemmer token i DB og sætter den til en bestemt bruger med ID.
            db.addToken(token.getToken(), foundUser.getUserID());

        } else {
            token = null;
        }
        //Retunerer en access token til klienten.
        return token;


    }

//    Modtager en token og sender et User-objekt retur
    public User getUserFromTokens(String token) throws SQLException {
        DBConnector db = new DBConnector();
        User user = db.getUserFromToken(token);
        db.close();
        return user;

    }
//  Sletter en token. Bruges bl.a. ved logud.
    public boolean deleteToken(String token) throws SQLException{
        DBConnector db = new DBConnector();
        boolean deleteToken = db.deleteToken(token);
        db.close();
        return deleteToken;

    }
}
