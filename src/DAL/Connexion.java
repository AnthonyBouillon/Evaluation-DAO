package DAL;

import java.sql.*;

/**
 * Classe qui me permet de me connecter à la base de données (hotel)
 *
 * @author 80010-37-15
 */
public class Connexion {

    Connection con = null;

    /**
     * Méthode contenant l'url de ma base de données ainsi que les identifiants
     * de connexion
     *
     * @return con - Objet de connexion
     * @throws java.sql.SQLException
     */
    public Connection Connection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/hotel";
        con = DriverManager.getConnection(url, "root", "leqxd777");
        return con;
    }
}
