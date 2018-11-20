package DAL;

import java.sql.*;
import java.util.*;

public class ClientDAO extends Connexion {

    /**
     * Insertion d'un client (nom, prénom et ville) dans la table client
     *
     * @param cli
     */
    public void Insert(Client cli) {
        try {
            PreparedStatement stm = Connection().prepareStatement("INSERT INTO client (cli_nom, cli_prenom, cli_ville) VALUES (?, ?, ?)");
            stm.setString(1, cli.getNom());
            stm.setString(2, cli.getPrenom());
            stm.setString(3, cli.getVille());
            stm.execute();
            Connection().close();
        } catch (SQLException e) {
            System.out.println("Error while inserting entity 'client'");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Modifie les informations d'un client besoin de l'id et d'une autre valeur
     *
     * @param cli
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
        } catch (SQLException e) {
            System.out.println("Error update entity 'client'");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Supprime un client (d'abord dans réservation et ensuite dans client | clé étrangère) besoin de l'id
     *
     * @param cli
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
        } catch (SQLException e) {
            System.out.println("Error deleting entity 'client' or 'reservation'");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Liste contenant toutes les informations d'un client
     *
     * @return
     */
    public List<Client> List() {
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
            System.out.println(e.getMessage());
        }
        return resultat;
    }
}
