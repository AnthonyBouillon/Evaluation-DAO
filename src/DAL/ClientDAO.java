package DAL;

import java.sql.*;
import java.util.*;

/**
 * Classe utilisée pour la gestion d'un ou de plusieurs clients | Méthode
 * contenu -> CREATE READ UPDATE DELETE (CRUD)
 *
 * @author 80010-37-15
 */
public class ClientDAO extends Connexion {

    public String message = "";
    public boolean validate_method = false;

    /**
     * Insertion d'un client (nom, prénom et ville) dans la bdd
     *
     * @param cli Contient toutes les données pour un client
     * @throws java.sql.SQLException
     */
    public void Create(Client cli) throws SQLException {
        PreparedStatement stm = Connection().prepareStatement("INSERT INTO client (cli_nom, cli_prenom, cli_ville) VALUES (?, ?, ?)");
        stm.setString(1, cli.getNom());
        stm.setString(2, cli.getPrenom());
        stm.setString(3, cli.getVille());
        stm.execute();
        Connection().close();
    }

    /**
     * Modifie les informations (nom, prénom et ville) d'un client dans la bdd
     *
     * @param cli Contient toutes les nouvelles données pour un client
     */
    public void Update(Client cli) {
        try {
            PreparedStatement stm = Connection().prepareStatement("UPDATE client SET cli_nom = ?, cli_prenom = ?, cli_ville = ? WHERE cli_id = ?");
            stm.setInt(4, cli.getId());
            stm.setString(1, cli.getNom());
            stm.setString(2, cli.getPrenom());
            stm.setString(3, cli.getVille());
            stm.execute();
            Connection().close();
            message = "Client modifié avec succès";
            validate_method = true;
        } catch (SQLException e) {
            validate_method = false;
            message = "Désolé, le client n'a pas pu être modifié";
            System.out.println("Error update entity 'client'");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Supprime un client (d'abord dans réservation et ensuite dans client | clé
     * étrangère)
     *
     * @param cli Contient l'identifiant du client à supprimer
     */
    public void Delete(Client cli) {
        try {
            PreparedStatement stm;
            stm = Connection().prepareStatement("DELETE FROM reservation WHERE res_cli_id = ?");
            stm.setInt(1, cli.getId());
            stm.execute();
            stm = Connection().prepareStatement("DELETE FROM client WHERE cli_id = ?");
            stm.setInt(1, cli.getId());
            stm.execute();
            Connection().close();
            message = "Client supprimé avec succès";
        } catch (SQLException e) {
            message = "Désolé, le client n'a pas pu être supprimé";
            System.out.println("Error deleting entity 'client' or 'reservation'");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Liste contenant toutes les informations des clients de la base de données
     *
     * @return - Retourne une liste contenant les informations de tout les
     * clients
     */
    public List<Client> Read() {
        List<Client> resultat = new ArrayList();
        try {
            Statement stm = Connection().createStatement();
            ResultSet result = stm.executeQuery("SELECT * FROM client");
            // Lis ligne par ligne
            while (result.next()) {
                Client c = new Client();
                c.setId(result.getInt("cli_id"));
                c.setNom(result.getString("cli_nom"));
                c.setPrenom(result.getString("cli_prenom"));
                c.setVille(result.getString("cli_ville"));
                // récupère toutes les informations du client et les ajoutent dans la liste 
                resultat.add(c);
            }
            Connection().close();
        } catch (SQLException e) {
            System.out.println("Error reading 'client'");
            message = null;
            System.out.println(e.getMessage());
        }
        return resultat;
    }
}
