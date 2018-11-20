package DAL;

public class Client {

    // Attributs correspondant à mes champs dans la base de données de la table client
    private int Id;
    private String Nom;
    private String Prenom;
    private String Ville;

    /**
     * Lis l'identifiant du client
     *
     * @return
     */
    public int getId() {
        return Id;
    }

    /**
     * Récupère l'identifiant du client
     *
     * @param Id
     */
    public void setId(int Id) {
        this.Id = Id;
    }

    /**
     * Lis le nom du client
     *
     * @return
     */
    public String getNom() {
        return Nom;
    }

    /**
     * Récupère le nom du client
     *
     * @param Nom
     */
    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    /**
     * Lis le prénom du client
     *
     * @return
     */
    public String getPrenom() {
        return Prenom;
    }

    /**
     * Récupère le prénom du client
     *
     * @param Prenom
     */
    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    /**
     * Lis la ville du client
     *
     * @return
     */
    public String getVille() {
        return Ville;
    }

    /**
     * Récupère la ville du client
     *
     * @param Ville
     */
    public void setVille(String Ville) {
        this.Ville = Ville;
    }

}
