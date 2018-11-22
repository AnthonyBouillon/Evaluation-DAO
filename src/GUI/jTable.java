package GUI;

import DAL.Client;
import DAL.ClientDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Modele qui me permet de gerer la jTable pour le jFrame
 *
 * @author 80010-37-15
 */
public class jTable extends AbstractTableModel {

    // Entête du tableau qui ne peut être modifié(final) même dans la classe(private) 
    private final String[] Titres = {"NOM", "PRENOM", "VILLE"};
    ClientDAO clients = new ClientDAO();
    List<Client> clients_list = new ArrayList();

    /**
     * Méthode qui récupère la liste qui a été retourné dans la méthode
     * {@code Insert()} de la classe {@code ClientDAO()}
     *
     * @Construct
     */
    public jTable() {
        clients_list = clients.Read();
    }

    /**
     * Retourne le nombres de colonnes suivant le nombres de titres
     *
     * @return - Retourne le nombre de titre
     */
    @Override
    public int getColumnCount() {
        return Titres.length;
    }

    /**
     * Méthode qui me permet de placer les titres dans les colonnes du jTable
     *
     * @param column Défini le nombre de colonne
     * @return - Retourne les titres dans les colonnes
     */
    @Override
    public String getColumnName(int column) {
        return Titres[column];
    }

    /**
     * Méthode qui me permet de connaitre le nombre de ligne dans la liste
     *
     * @return Retourne la taille de la liste
     */
    @Override
    public int getRowCount() {
        return clients_list.size();
    }

    /**
     * Méthode qui permet d'afficher les clients de la base de données dans le
     * jTable
     *
     * @param row Est le nombre de ligne
     * @param column Est le nombre de colonne
     * @return Retourne les N,P,V des clients dans les colonnes
     */
    @Override
    public Object getValueAt(int row, int column) {
        switch (column) {
            case 0:
                return clients_list.get(row).getNom();
            case 1:
                return clients_list.get(row).getPrenom();
            case 2:
                return clients_list.get(row).getVille();
            default:
                return null;
        }
    }

    /**
     * Ajoute une ligne dans le jTable
     *
     * @param client Contient les données du client
     */
    public void AjouteClient(Client client) {
        clients_list.add(client);
        fireTableRowsInserted(clients_list.size()-1, clients_list.size());
    }

    /**
     * Supprime une ligne dans le jTable
     *
     * @param i Contient le numéro de la ligne à supprimer
     */
    public void SupprimerClient(int i) {
        clients_list.remove(i);
        // Supprime une ligne (Nul besoin de la fonction Actualise()
        fireTableRowsDeleted(i, i);
    }

    /**
     * Actualise la liste de la jTable, en réassignant la liste retourné de la
     * méthode {@code Insert()} de la classe {@code ClientDAO()} dans la
     * nouvelle liste
     */
    public void Actualise() {
        clients_list = clients.Read();
    }
}
