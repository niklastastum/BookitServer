package admin;

import config.Config;
import database.DBConnector;
import model.Book;
import model.Curriculum;
import model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by Tastum on 28/11/2016.
 */
public class AdminView {

//    Denne klasse er TUI for admins. Den indeholder menuen og alle metoder der gøres brug af.
//    Den bliver kørt fra Main.java

    DBConnector db = new DBConnector();
    Scanner input = new Scanner(System.in);

//    Menuen
    public void menu() throws SQLException {

        do {
            try {

                System.out.print("Velkommen til adminmenuen \nDu har nu følgende muligheder:\n");
                System.out.print("1. Vis alle systemts brugere\n");
                System.out.print("2. Opret en bruger\n");
                System.out.print("3. Slet en bruger\n");
                System.out.print("4. Opret en ny bog til systemet\n");

                int choice = input.nextInt();

//                En switch til at styre admins valg ift metoder
                switch (choice) {
                    case 1:
//                        Viser alle brugere
                        System.out.print("Her er alle brugere: \n");
                        viewAllUsers();
                        break;
                    case 2:
//                        giver admin mulighed for at oprette en bruger
                        System.out.println("Velkommen til brugeroprettelse!\n");
                        createUser();
                        break;
                    case 3:
//                        Giver admin mulighed for at slette en bruger
                        System.out.println("Du kan nu slette en bruger\n");
                        viewAllUsers();
                        System.out.println("Indtast venligst ID-nummeret på brugeren, der ønskes slettet: ");
                        deleteUser();
                        break;
                    case 4:
//                        Her kan admin oprette en bog
                        System.out.println("Du har nu mulighed for at oprette en bog. \n");
//                        Laver et kald til DB med de to returnerede værdier fra de to metoder.
                        db.addCurriculumBook(viewAllCurriculums(), createBook());
                        System.out.println("Din er nu blevet oprettet til den valgte pensumliste");
                        break;
                    default:
//                        Hvis admin taster et andet tal end 1-4, så kører menuen igen
                        System.out.print("Prøv igen\n");
                        break;
                }

            } catch (InputMismatchException e) {
//                Catcher en fejl, hvis der bliver indtastet bogstaver eller tal
                System.out.println("Indtast venligst et tal..\n");
                input.nextLine();
            }
        } while (true);
    }

//    Denne metode viser alle brugere i systemet.
    public void viewAllUsers() throws SQLException {
//        Oprettter en ArrayList med data fra databasen
        ArrayList<User> users = db.getUsers();
        //Header for the view of users w. modulus
        System.out.printf("%-15s %-15s %-15s %-15s %-25s %-15s\n", "Bruger ID: ", "Brugernavn: ", "Fornavn: ", "Efternavn: ", "Email: ", "Admin-status: ");
        for (User user : users) {
//            Printer alle brugerne
            System.out.printf("%-15d %-15s %-15s %-15s %-25s %-15b\n", user.getUserID(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getUserType());
        }
    }

//    Opretter bruger
    public void createUser() throws SQLException {

//        Admin indtaster de nødvendige oplysninger
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

//        Her har admin muligheden for at give den nye bruger admin rettigheder
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

//        Her sættes alle de indtastede værdier ind i et User objekt
        User user = new User();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setUserType(admin);

        try {
//            User objektet bliver sendt til databasen for oprettelse
            db.addUser(user);
            System.out.print("Brugeren er oprettet\n\n");
        } catch (Exception e) {
//            Fejl
            System.out.print("Noget gik galt..");
            e.printStackTrace();
        }

    }

//    Sletter en bruger
    public void deleteUser() {

//        Admin skriver ID på den bruger der skal slettes
        int delete = input.nextInt();

        try {
//            Sender IDnr til databasen
            db.deleteUser(delete);
            System.out.print("Brugeren er slettet\n\n");
        } catch (SQLException e) {
//            Fejl
            System.out.print("Noget gik galt..");
            e.printStackTrace();
        }

    }

//    Opretter bog
    public Book createBook() {

//        Admin indtaster oplsyninger
        System.out.println("Angiv venligst forlaget: ");
        String publisher = input.next();
        System.out.println("Angiv venligst titlen: ");
        String title = input.next();
        System.out.println("Angiv venligst forfatteren: ");
        String author = input.next();
        System.out.println("Angiv venligst versionen: ");
        int version = input.nextInt();
        System.out.println("Angiv venligst ISBN-nummer: ");
        double ISBN = input.nextDouble();
        System.out.println("Angiv venligst pris hos Academic Books: ");
        double priceAB = input.nextDouble();
        System.out.println("Angiv venligst pris hos SAXO.dk: ");
        double priceSAXO = input.nextDouble();
        System.out.println("Angiv venligst pris hos CDON.com: ");
        double priceCDON = input.nextDouble();

//        Book objekt bliver lavet
        Book book = new Book(publisher, title, author, version, ISBN, priceAB, priceSAXO, priceCDON);

//        Metoden returner objektet, så det kan bruges i DB-kaldet
        return book;
    }

//    Viser alle pensumlister, så admin kan tilføje bogen til en af dem.
    public int viewAllCurriculums() {

        System.out.println("Først skal du vælge hvilken pensumliste, bogen skal tilhøre: \n");

//        Fungerer på samme måde som visning af brugere
        ArrayList<Curriculum> curriculums = db.getCurriculums();
        System.out.printf("%-20s %-15s %-15s %-15s\n", "Pensumliste ID: ", "Skole: ", "Uddannelse: ", "Semester: ");
        for (Curriculum curriculum : curriculums) {
            System.out.printf("%-20d %-15s %-15s %-15s\n", curriculum.getCurriculumID(), curriculum.getSchool(), curriculum.getEducation(), curriculum.getSemester());
        }
        System.out.println("Indtast venligst ID-nummeret for den ønskede pensumliste: ");
        int curriculumID = input.nextInt();

//        Curriculum ID returneres, så det kan bruges i kaldet til DB.
        return curriculumID;
    }
}
