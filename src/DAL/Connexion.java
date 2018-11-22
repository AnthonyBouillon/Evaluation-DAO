package DAL;

import java.sql.*;

/**
 * Classe qui me permet de me connecter à la base de données (hotel)
 * @author 80010-37-15
 */
public class Connexion {

    Connection con = null;

    /**
     * Méthode contenant l'url de ma base de données ainsi que les identifiants
     * de connexion
     *
     * @return con - Objet de connexion
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
