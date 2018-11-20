package GUI;

import DAL.Client;
import DAL.ClientDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class jTable extends AbstractTableModel {

    // Entête du tableau qui ne peut être modifié(final) même dans la classe(private) 
    private final String[] Titres = {"NOM", "PRENOM", "VILLE"};
    // Instance d'une classe et d'une liste
    ClientDAO clients = new ClientDAO();
    List<Client> clients_list = new ArrayList();

    /**
     * Liste qui contiendra les données du clients (une autre liste) récupérer
     * grâce à la méthode List() qui retourne toutes les données de la table
     * client
     *
     * @Constructeur
     */
    public jTable() {
        clients_list = clients.List();
    }

    /**
     * Retourne le nombres de colonnes 3 titres : 3 colonnes | 4 titres : 4
     * colonnes
     *
     * @return
     */
    @Override
    public int getColumnCount() {
        return Titres.length;
    }

    /**
     * Retourne les titres dans les colonnes du jTable
     *
     * @param column
     * @return
     */
    @Override
    public String getColumnName(int column) {
        return Titres[column];
    }

    /**
     * Retourne le nombre de ligne dans la liste
     *
     * @return
     */
    @Override
    public int getRowCount() {
        return clients_list.size();
    }

    /**
     * Pour chaque colonne (row), affiche les noms, prénoms et villes les
     * données viennent de la liste des clients récupéré dans la base de données
     *
     * @param row
     * @param column
     * @return
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
     * Ajoute une ligne dans le jTable (client : données à insérer) je récupère
     * les données du client avec les ** client.setters() AND jtable.getText()
     *
     **
     * @param client
     */
    public void AjouteClient(Client client) {
        clients_list.add(client);
        fireTableRowsInserted(clients_list.size() + 1, clients_list.size());
    }

    /**
     * Supprime une ligne dans le jTable et dans la liste (i : la ligne à
     * supprimer) je récupère la ligne sélectionnée avec ** getSelectedRow()
     *
     **
     * @param i
     */
    public void SupprimerClient(int i) {
        clients_list.remove(i);
        fireTableRowsDeleted(i, i);
    }

    /**
     * Permet d'actualiser la liste de client
     */
    public void Actualise() {
        clients_list = clients.List();
    }
}
