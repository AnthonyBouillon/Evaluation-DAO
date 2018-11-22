/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import DAL.Connexion;
import DAL.Client;
import DAL.ClientDAO;
import java.sql.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author 80010-37-15
 */
public class Test_crud {

    Connexion connection = new Connexion();
    ClientDAO clients = new ClientDAO();
    Client cli = new Client();

    // Test si la méthode Insert() fonctionne
    @Test
    public void Create_test() {
        boolean resultat_attendu = false;
        // Données que je veut inserer
        cli.setNom("Bouillon");
        cli.setPrenom("Anthony");
        cli.setVille("Corbie");
        clients.Create(cli);
        for (Client liste : clients.Read()) {
            resultat_attendu = cli.getNom().equals(liste.getNom()) && cli.getPrenom().equals(liste.getPrenom()) && cli.getVille().equals(liste.getVille());
        }
        assertTrue(resultat_attendu);
    }

    // Test si la méthode Read() fonctionne
    @Test
    public void Read_test() {
        boolean resultat_attendu = false;
        for (int i = 0; i < clients.Read().size(); i++) {
            if (i > 0) {
                resultat_attendu = true;
            }
        }
        assertTrue(resultat_attendu);
    }
// Test si la méthode Update() fonctionne

    @Test
    public void update_test() {
        boolean resultat_attendu = false;
        cli.setId(292);
        cli.setNom("Jean mis");
        cli.setPrenom("Prenom");
        cli.setVille("City");
        clients.Update(cli);
        resultat_attendu = clients.validate_method == true;
        assertTrue(resultat_attendu);
    }

    // Test une erreur volontaire
    @Test
    public void update_test_2() {
        boolean resultat_attendu = false;
        cli.setId(292);
        cli.setVille("Mouchille");
        clients.Update(cli);
        resultat_attendu = clients.validate_method != false;
        assertFalse(resultat_attendu);
    }
    // Test si la méthode Delete() fonctionne

    /**
     * Méthode Qui me permet de testé la méthode {@code Delete()} de la classe
     * {@code ClientDAO()} qui supprime un client de la base de données
     *
     * @throws Exception
     */
    @Test
    public void Delete_test() throws SQLException {
        boolean resultat_attendu = true;
        // Je demande la suppression de se client
        cli.setId(318);
        clients.Delete(cli);
        // Je recherche les informations du client "supprimer"
        Statement stm = connection.Connection().createStatement();
        ResultSet result = stm.executeQuery("SELECT * FROM client WHERE cli_id = 318");
        // Si les informations du client existe encore -> la requête Delete() ne marche pas
        while (result.next()) {
            cli.setId(result.getInt("cli_id"));
            cli.setNom(result.getString("cli_nom"));
            resultat_attendu = false;
        }
        connection.Connection().close();
        assertTrue(resultat_attendu);
    }

}
