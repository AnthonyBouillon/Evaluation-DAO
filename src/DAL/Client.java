package DAL;

/**
 * Classe qui contient les attributs et les getters setters correspondant aux
 * champs dans la table client
 *
 * @author 80010-37-15
 */
public class Client {

    private int Id;
    private String Nom;
    private String Prenom;
    private String Ville;

    /**
     * Getter correspondant à l'attribut privé -> Id
     *
     * @return Id - Retourne l'identifiant du client
     */
    public int getId() {
        return Id;
    }

    /**
     * Setter correspondant à l'attribut privé -> Id
     *
     * @param Id Défini l'identifiant du client
     */
    public void setId(int Id) {
        this.Id = Id;
    }

    /**
     * Getter correspondant à l'attribut privé -> Nom
     *
     * @return Nom - Retourne le nom du client
     */
    public String getNom() {
        return Nom;
    }

    /**
     * Setter correspondant à l'attribut privé -> Nom
     *
     * @param Nom Défini le nom du client
     */
    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    /**
     * Getter correspondant à l'attribut privé -> Prenom
     *
     * @return Prenom - Retourne le prénom du client
     */
    public String getPrenom() {
        return Prenom;
    }

    /**
     * Setter correspondant à l'attribut privé -> Prenom
     *
     * @param Prenom Défini le prénom du client
     */
    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    /**
     * Getter correspondant à l'attribut privé -> Ville
     *
     * @return Ville - Retourne la ville du client
     */
    public String getVille() {
        return Ville;
    }

    /**
     * Setter correspondant à l'attribut privé -> Ville
     *
     * @param Ville Défini la ville du client
     */
    public void setVille(String Ville) {
        this.Ville = Ville;
    }
}
