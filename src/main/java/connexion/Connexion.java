package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    private static final String URL = "jdbc:mysql://localhost:3306/demoJDBC";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    private static Connection connection;
    
    private Connexion() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexion établie");
        } catch (SQLException ex) {
            System.out.println("Erreur de connexion: " + ex.getMessage());
        }
    }
    
    public static Connection getConnection() {
        if (connection == null) {
            new Connexion();
        }
        return connection;
    }
}