package admin;

import config.Config;
import database.DBConnector;
import model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Tastum on 28/11/2016.
 */
public class AdminView {

    DBConnector db = new DBConnector();
    Scanner input = new Scanner(System.in);

    public void menu() throws SQLException {

        System.out.print("Velkommen til adminmenuen \nDu har nu følgende muligheder:\n");
        System.out.print("1. Vis alle systemts brugere\n");
        System.out.print("2. Opret en bruger\n");
        System.out.print("3. Slet en bruger\n");
        System.out.print("4. Opret en ny bog til systemet\n");

        int choice = input.nextInt();

        switch(choice) {
            case 1:
                System.out.print("Her er alle brugere: \n");
                viewAllUsers();

                break;
            default:
                System.out.print("You had one job..");
        }

    }

    public void viewAllUsers() throws SQLException {
        ArrayList<User> users = db.getUsers();
        //Header for the view of users
        System.out.printf("%-15s %-15s %-15s %-15s %-25s %-15s\n", "Bruger ID: ", "Brugernavn: ", "Fornavn: ", "Efternavn: ", "Email: ", "Admin-status: ");
        for (User user : users) {
            System.out.printf("%-15d %-15s %-15s %-15s %-25s %-15b\n", user.getUserID(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getUserType());
        }
    }

    public void createUser() throws SQLException {



    }
}
