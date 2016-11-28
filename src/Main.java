import admin.AdminView;
import config.Config;

import java.sql.SQLException;

/**
 * Created by Tastum on 28/11/2016.
 */
public class Main {

    public static void main (String [] args) {

        Config.setDbName("Bookit");
        Config.setDbUrl("localhost");
        Config.setDbPort("3306");
        Config.setDbUserName("root");
        Config.setDbPassword("java");

        AdminView adminView = new AdminView();

        try {
            adminView.menu();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
