package controllers;

import com.google.gson.Gson;
import database.DBConnector;
import model.Book;

import java.util.ArrayList;

public class BookController {

//    Controller klasse til book. står for at lave kald til databasen.
//    Forbinder API med databasen. Bedre kendt som logik laget.

    public ArrayList<Book> getBooks() throws Exception {
        DBConnector db = new DBConnector();
        ArrayList<Book> books = db.getBooks();
        db.close();
        return books;
    }

    public Book getBook(int id) throws Exception {
        DBConnector db = new DBConnector();
        Book book = db.getBook(id);
        db.close();
        return book;
    }

    public boolean editBook(int id, String data) throws Exception {
        DBConnector db = new DBConnector();
        boolean editBook = db.editBook(id, data);
        db.close();
        return editBook;
    }

    public boolean deleteBook(int id) throws Exception {
        DBConnector db = new DBConnector();
        boolean deleteBook = db.deleteBook(id);
        db.close();
        return deleteBook;
    }
}
