package expo.facturation.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbConnection {
    private static final String DBURL = "jdbc:mysql://localhost:3306/facturation";
    private static final String DBUSER = "root";
    private static final String DBPASSWORD = "";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
        } catch (SQLException e) {
            System.out.println("Erreur de connexion à la BD");
            e.printStackTrace();
            return null;
        }
    }
}
