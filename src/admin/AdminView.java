package admin;

import config.Config;
import database.DBConnector;
import model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by Tastum on 28/11/2016.
 */
public class AdminView {

    DBConnector db = new DBConnector();
    Scanner input = new Scanner(System.in);

    public void menu() throws SQLException {

        do {
            try {

                System.out.print("Velkommen til adminmenuen \nDu har nu følgende muligheder:\n");
                System.out.print("1. Vis alle systemts brugere\n");
                System.out.print("2. Opret en bruger\n");
                System.out.print("3. Slet en bruger\n");
                System.out.print("4. Opret en ny bog til systemet\n");

                int choice = input.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Her er alle brugere: \n");
                        viewAllUsers();
                        break;
                    case 2:
                        System.out.println("Velkommen til brugeroprettelse!\n");
                        createUser();
                        break;
                    case 3:
                        System.out.println("Du kan nu slette en bruger\n");
                        viewAllUsers();
                        System.out.println("Indtast venligst ID-nummeret på brugeren, der ønskes slettet: ");
                        deleteUser();
                        break;
                    default:
                        System.out.print("You had one job..\n");
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Indtast venligst et tal..\n");
                input.nextLine();
            }
        } while (true);
    }

    public void viewAllUsers() throws SQLException {
        ArrayList<User> users = db.getUsers();
        //Header for the view of users
        System.out.printf("%-15s %-15s %-15s %-15s %-25s %-15s\n", "Bruger ID: ", "Brugernavn: ", "Fornavn: ", "Efternavn: ", "Email: ", "Admin-status: ");
        for (User user : users) {
            System.out.printf("%-15d %-15s %-15s %-15s %-25s %-15b\n", user.getUserID(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getUserType() + "\n\n");
        }
    }

    public void createUser() throws SQLException {

        System.out.println("Indtast venligst den nye brugers fornavn: ");
        String firstName = input.next();
        System.out.println("Indtast venligst den nye brugers efternavn: ");
        String lastName = input.next();
        System.out.println("Indtast venligst den nye brugers brugernavn: ");
        String username = input.next();
        System.out.println("Indtast venligst den nye brugers email: ");
        String email = input.next();
        System.out.println("Indtast venligst den nye brugers adgangskode: ");
        String password = input.next();

        System.out.println("Skal den nye bruger have admin-rettigheder? (ja/nej)");
        Boolean admin = false;
        String choice = input.next();

        switch (choice) {
            case "ja":
                admin = true;
                break;
            case "nej":
                admin = false;
                break;
            default:
                System.out.println("Indtast venligst enten 'ja' eller 'nej': ");
        }

        User user = new User();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setUserType(admin);

        try {
            db.addUser(user);
            System.out.print("Brugeren er oprettet\n\n");
        } catch (Exception e) {
            System.out.print("Noget gik galt..");
            e.printStackTrace();
        }

    }

    public void deleteUser() {

        int delete = input.nextInt();

        try {
            db.deleteUser(delete);
            System.out.print("Brugeren er slettet\n\n");
        } catch (SQLException e) {
            System.out.print("Noget gik galt..");
            e.printStackTrace();
        }

    }
}
