package controllers;

import com.google.gson.Gson;
import database.DBConnector;
import model.Book;
import model.Curriculum;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by mortenlaursen on 17/10/2016.
 */
public class CurriculumController {
//Samme som BookController.java

//    Henter pensumlister til API
    public ArrayList<Curriculum> getCurriculums()throws IllegalAccessException{
        DBConnector db = new DBConnector();
        ArrayList<Curriculum> curriculums = db.getCurriculums();
        db.close();
        return curriculums;
    }

//    Henter et bestemt pensum til API
    public Curriculum getCurriculum(int id) {
    DBConnector db = new DBConnector();
    Curriculum curriculum = db.getCurriculum(id);
    db.close();
    return curriculum;
    }

//    Bruges ikke. Men kan bruges ved udvidelse. Ændre pensum
    public boolean editCurriculum(int id, String data) throws SQLException {
        DBConnector db = new DBConnector();
        boolean editCurriculum = db.editCurriculum(id, data);
        db.close();
        return editCurriculum;
    }
    //    Bruges ikke. Men kan bruges ved udvidelse. slet pensum
    public boolean deleteCurriculum(int id) throws SQLException {
        DBConnector db = new DBConnector();
        boolean deleteCurriculum =  db.deleteCurriculum(id);
        db.close();
        return deleteCurriculum;
    }
    //    Bruges ikke. Men kan bruges ved udvidelse. tilføj pensum
    public boolean addCurriculum(String data) throws SQLException {
        DBConnector db = new DBConnector();
        Curriculum c = new Gson().fromJson(data,Curriculum.class);
        boolean addCurriculum = db.addCurriculum(c);
        db.close();
        return addCurriculum;
    }
//  Henter bøgerne til en bestemt pensumliste og returnerer til API
    public ArrayList<Book> getCurriculumBooks(int curriculumID) {
        DBConnector db = new DBConnector();
        ArrayList<Book> curricilumBooks = db.getCurriculumBooks(curriculumID);
        db.close();
        return curricilumBooks;

    }

}
