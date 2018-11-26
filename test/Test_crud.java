/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import DAL.Connexion;
import DAL.Client;
import DAL.ClientDAO;
import GUI.jTable;
import java.sql.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author 80010-37-15
 */
public class Test_crud {

    Connexion connection;
    ClientDAO clients;
    Client cli;
    jTable jtable;

    // Instance de mes quatres classes
    public Test_crud() throws SQLException {
        this.jtable = new jTable();
        this.connection = new Connexion();
        this.clients = new ClientDAO();
        this.cli = new Client();
    }

    /**
     * Méthode qui me permet de tester la méthode {@code Create()} de la classe
     * {@code ClientDAO()}.
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void Create_test() throws SQLException {
        boolean resultat_attendu = false;
        // Données que je veut inserer
        cli.setNom("Bouillon");
        cli.setPrenom("Anthony");
        cli.setVille("Corbie");
        clients.Create(cli);
        // Vérifie si la dernière ligne de la base de données correspond au information insérer par l'utilisateur
        for (Client liste : clients.Read()) {
            resultat_attendu = cli.getNom().equals(liste.getNom()) && cli.getPrenom().equals(liste.getPrenom()) && cli.getVille().equals(liste.getVille());
        }
        assertTrue(resultat_attendu);
    }

    /**
     * Méthode qui me permet de tester la méthode {@code Read()} de la classe
     * {@code ClientDAO()}. Si la liste contient au moins une ligne, c'est que
     * la liste contient des valeurs provenant de la base de données
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void Read_test() throws SQLException {
        boolean resultat_attendu = false;
        for (int i = 0; i < clients.Read().size(); i++) {
            if (i > 0) {
                resultat_attendu = true;
            }
        }
        assertTrue(resultat_attendu);
    }

    /**
     * Vérifie si le nombre de ligne de la première liste est le même que celui
     * de la deuxième liste
     *
     * @throws SQLException
     */
    @Test
    public void Read_test_2() throws SQLException {
        boolean resultat_attendu = false;
        if (clients.Read().size() == jtable.getRowCount()) {
            resultat_attendu = true;
        }
        assertTrue(resultat_attendu);
    }

    /**
     * Vérifie si le dernier id de la liste correspond au dernier id de la table
     * client
     *
     * @throws SQLException
     */
    @Test
    public void Read_test_3() throws SQLException {
        boolean resultat_attendu = false;
        Statement stm = connection.Connection().createStatement();
        ResultSet result = stm.executeQuery("SELECT MAX(cli_id) as cli_id FROM client");
        while (result.next()) {
            cli.setId(result.getInt("cli_id"));
        }
        if (cli.getId() == clients.Read().get(clients.Read().size() - 1).getId()) {
            resultat_attendu = true;
        }
        assertTrue(resultat_attendu);
    }

    /**
     * Méthode qui me permet de tester la méthode {@code Update()} de la classe
     * {@code ClientDAO()}. Modifie les données d'un client (Nom, Prénom, Ville)
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void update_test() throws SQLException {
        boolean resultat_attendu = false;
        cli.setId(292);
        cli.setNom("Jean mis");
        cli.setPrenom("Prenom");
        cli.setVille("City");
        clients.Update(cli);
        resultat_attendu = clients.validate_method == true;
        assertTrue(resultat_attendu);
    }

    /**
     * Méthode Qui me permet de testé la méthode {@code Delete()} de la classe
     * {@code ClientDAO()} qui supprime un client de la base de données
     *
     * @throws java.sql.SQLException
     */
    @Test
    public void Delete_test() throws SQLException {
        // On part du principe que la requête marche
        boolean resultat_attendu = true;
        // Je demande la suppression de se client
        cli.setId(318);
        clients.Delete(cli);
        // Je recherche les informations du client qui a été "supprimer"
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

    /**
     * Méthode qui me permet de tester si la connexion à la base de données
     * fonctionne
     *
     * @throws SQLException
     */
    @Test
    public void Connection_test() throws SQLException {
        connection.Connection();
    }
}
