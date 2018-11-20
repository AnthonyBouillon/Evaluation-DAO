package DAL;

import java.sql.*;

public class Connexion {

    Connection con = null;

    /**
     * Connexion à la base de données ** hotel
     *
     * @return
     */
    public Connection Connection() {
        try {
            String url = "jdbc:mysql://localhost:3306/hotel";
            con = DriverManager.getConnection(url, "root", "leqxd777");
        } catch (SQLException e) {
            System.out.println("Error connection in database");
            System.out.println(e.getMessage());
        }
        return con;
    }
}
