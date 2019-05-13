/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classe.DAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import medecin.metier.Medicament;
import myconnections.DBConnection;
import urbain_projet_medecin.DemoGestion;

/**
 *
 * @author jerom
 */
public class MedicamentDAO extends DAO<Medicament> {

    String nom, description,code = "";
    Statement stmt;
    ResultSet rs = null;
    Medicament medoc;
    int id;

    public MedicamentDAO() {
        //this.stmt = null;
    }

    /**
     * Sous-menu de la classe Medicament appelant les methodes CRUD
     * @throws SQLException
     */
    @Override
    public void menu() throws SQLException {
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        int choix;
        do {

            Connection dbConnect = DBConnection.getConnection();
            if (dbConnect == null) {
                System.out.println("Echec de la connexion : fin du programme");
                System.exit(1);
            } else {
                System.out.println("Connexion réussie");
            }

            System.out.println("1.Créer un médicament");
            System.out.println("2.Afficher les médicaments");
            System.out.println("3.Modifier un médicament");
            System.out.println("4.Supprimer un médicament");
            System.out.println("5.Rechercher un médicament sur son nom");
            System.out.println("6.Rechercher un médicament selon sa description");
            System.out.println("7.Retour");

            do {
                System.out.println("Choix ?");
                choix = sc.nextInt();
                if (choix < 1 || choix > 7) {
                    System.out.println("choix incorrect");
                }

            } while (choix < 1 || choix >7);

            switch (choix) {
                case 1:
                    //appelle la méthode d'encodage d'un nouveau médicament
                    System.out.println("== Encodage d'un nouveau médicament ==");
                    nouveau();
                    break;
                case 2:
                    System.out.println("== Voir la liste des médicaments ==");
                    int idread;
                    System.out.println("Quel est l'id du médicament ?");
                    idread = sc.nextInt();
                    try{
                    read(idread);
                    }
                    catch(SQLException e){
                        System.out.println(" Erreur : "+e);
                    }
                    break;
                case 3:
                    //appelle la méthode d'encodage des modifications à apporter à un enregistrement
                    System.out.println("== Modification d'un médicament déjà existant ==");
                    modif();                    
                    break;
                case 4:
                    //appelle la méthode d'encodage de l'identifiant du médicament à supprimer
                    System.out.println("== Suppresion d'un médicament ==");
                    suppr();                    
                    break;
                case 5:
                    System.out.println("== Recherche sur le nom ==");
                    System.out.println("Entrez le nom recherché : ");
                    String nom = sc2.nextLine();
                    try {
                        //appel de la méthode de recherche sur le nom
                        List<Medicament> amedoc = rechNom(nom);
                        for (Medicament medoc : amedoc) {
                            System.out.println(medoc);
                        }
                    }catch (SQLException e) {
                        System.out.println("erreur " + e.getMessage());
                    }
                    break;
                case 6:
                    System.out.println("== Recherche partielle sur la description ==");
                    System.out.println("Entrez le mot clé de votre recherche : ");
                    String desc = sc2.nextLine();
                    try {
                        //appel de la méthode recherche partielle sur la description
                        List<Medicament> amedoc = rechDesc(desc);
                        for (Medicament medoc : amedoc) {
                            System.out.println(medoc);
                        }
                    }catch (SQLException e) {
                        System.out.println("erreur " + e.getMessage());
                    }
                    break;
                case 7:
                    break;
            }

        } while (choix != 7);
    }

    /**
     * création d'un médicament sur base des valeurs de son objet métier
     *
     * @throws SQLException erreur de création
     * @param obj médicament à créer
     * @return médicament créé
     */
    @Override
    public Medicament create(Medicament obj) throws SQLException {
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        String req1 = "insert into medicament(nom,description,code) values(?,?,?)";
        String req2 = "select idmedoc from medicament where nom=? and description= ? and code=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req1);
                PreparedStatement pstm2 = dbConnect.prepareStatement(req2)) {
            pstm.setString(1, obj.getNom());
            pstm.setString(2, obj.getDescription());
            pstm.setString(3, obj.getCode());
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new SQLException("erreur de creation médicament, aucune ligne créée");
            }
            pstm2.setString(1, obj.getNom());
            pstm2.setString(2, obj.getDescription());
            pstm2.setString(3, obj.getCode());
            try (ResultSet rs = pstm2.executeQuery()) {
                if (rs.next()) {
                    int idmedoc = rs.getInt(1);
                    obj.setIdmedoc(idmedoc);
                    return read(idmedoc);
                } else {
                    throw new SQLException("erreur de création médicament, record introuvable");
                }
            }
        }
    }

    /**
     * Methode d'encodage des données à ajouter dans la table medicament
     * @throws SQLException
     */
    public void nouveau() throws SQLException {
        //Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);

        stmt = dbConnect.createStatement();
        rs = stmt.executeQuery("select * from medicament");
        /*System.out.println("Entrez l'id du nouveau médicament");
         id=sc.nextInt();*/
        System.out.println("Entrez le nom du nouveau médicament");
        nom = sc2.nextLine();
        System.out.println("Entrez la description de ce médicament");
        description = sc2.nextLine();
        System.out.println("Entrez le code alphanumérique du médicament");
        code=sc2.nextLine();
        id = 0;
        medoc = new Medicament(id, nom, description,code);
        //System.out.println(id + nom + description);
        try {
            medoc = create(medoc);
            System.out.println(medoc);
        } catch (SQLException e) {
            System.out.println("Erreur : " + e);
        }   
    }

    /**
     * récupération des données d'un médicament sur base de son identifiant
     *
     * @throws SQLException code inconnu
     * @param idmedoc identifiant du médicament
     * @return médicament trouvé
     */
    @Override
    public Medicament read(int idmedoc) throws SQLException {
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        String req = "select * from medicament where idmedoc = ?";

        try (PreparedStatement pstm = dbConnect.prepareStatement(req)) {
            pstm.setInt(1, idmedoc);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    String nom = rs.getString("NOM");
                    String description = rs.getString("DESCRIPTION");
                    String code = rs.getString("CODE");
                    System.out.println(nom + ": " + description + "\tcode:" + code);
                    return new Medicament(idmedoc, nom, description,code);
                } else {
                    throw new SQLException("Code inconnu");
                }
            }
        }
    }

    /**
     * mise à jour des données du médicament sur base de son identifiant
     *
     * @return Medicament
     * @param obj médicament à mettre à jour
     * @throws SQLException erreur de mise à jour
     */
    @Override
    public Medicament update(Medicament obj) throws SQLException {
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        String req = "update medicament set nom=?,description=?,code=? where idmedoc= ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req)) {
            pstm.setInt(4, obj.getIdmedoc());
            pstm.setString(1,obj.getNom());
            pstm.setString(2,obj.getDescription());
            pstm.setString(3,obj.getCode());
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new SQLException("aucune ligne médicament mise à jour");
            }
            return read(obj.getIdmedoc());

        }
    }
    
    /**
     * Methode pour encoder les données à mettre à jour dans la table medicament
     * @throws SQLException
     */
    public void modif() throws SQLException{

        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        System.out.println("Quel est l'id du medicament à modifier ? ");
        id=sc.nextInt();

        stmt = dbConnect.createStatement();
        rs = stmt.executeQuery("select * from medicament");
        while(rs.next()){
            if(rs.getInt("IDMEDOC") == id){
                nom = rs.getString("NOM");
                description = rs.getString("DESCRIPTION");
                code = rs.getString("CODE");
                }
        }
        System.out.println("Nouveau nom ? ");
        nom=sc2.nextLine();
        System.out.println("Nouvelle description ? ");
        description=sc2.nextLine();
        System.out.println("Nouveau code ? ");
        code=sc2.nextLine();
        medoc = new Medicament(id,nom,description,code);
        try{
            update(medoc);
            System.out.println("Modification effectuée");
        }
        catch (SQLException e) {
            System.out.println("Erreur : " + e);
        } 
        
        
        
    }

    /**
     * effacement du médicament sur base de son identifiant
     *
     * @throws SQLException erreur d'effacement
     * @param obj médicament à effacer
     */
    @Override
    public void delete(Medicament obj) throws SQLException {
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        String req = "delete from medicament where idmedoc= ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req)) {

            pstm.setInt(1, obj.getIdmedoc());
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new SQLException("aucune ligne de médicament effacée");
            }

        }
    }
    
    /**
     * Methode pour encoder l'indentifiant du médicament à supprimer
     * @throws SQLException
     */
    public void suppr() throws SQLException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Quel est l'id du medicament à supprimer ? ");
        id=sc.nextInt();
        stmt = dbConnect.createStatement();
        rs = stmt.executeQuery("select * from medicament");
        while(rs.next()){
            if(rs.getInt("IDMEDOC") == id){
                nom = rs.getString("NOM");
                description = rs.getString("DESCRIPTION");
                code = rs.getString("CODE");
                }
        }
        medoc = new Medicament(id,nom,description,code);
        try{
        delete(medoc);
        System.out.println("Suppression effectuée");
        }
        catch (SQLException e) {
            System.out.println("Erreur : " + e);
        }
    }
    /**
     * Methode qui recherche un médicament sur son nom
     * @param nomrech
     * @return
     * @throws SQLException 
     */
    
    public List<Medicament> rechNom(String nomrech) throws SQLException {
        List<Medicament> plusieurs = new ArrayList<>();
        String req = "select * from medicament where nom = ?";

        try (PreparedStatement pstm = dbConnect.prepareStatement(req)) {
            pstm.setString(1, nomrech);
            try (ResultSet rs = pstm.executeQuery()) {
                boolean trouve = false;
                while (rs.next()) {
                    trouve = true;
                    int idmed = rs.getInt("IDMEDOC");
                    String nom = rs.getString("NOM");
                    String description = rs.getString("DESCRIPTION");
                    String code = rs.getString("CODE");
                    plusieurs.add(new Medicament(idmed,nom,description,code));
                }

                if (!trouve) {
                    throw new SQLException("nom inconnu");
                } else {
                    return plusieurs;
                }
            }
        }             
    }
    /**
     * Méthode de recherche partielle sur un mot clé de la description
     * Cette partie est inspirée du code de Hugo Delmarche
     * @param desc
     * @return
     * @throws SQLException 
     */
    
    public List<Medicament> rechDesc(String desc) throws SQLException{
        List<Medicament> plusieurs2 = new ArrayList<>();
        String req2 = "SELECT * FROM medicament WHERE description LIKE ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req2)) {
            pstm.setString(1, "%" + desc + "%");
            try (ResultSet rs = pstm.executeQuery()) {
                boolean trouve = false;
                while (rs.next()) {
                    trouve = true;
                    int idmed = rs.getInt("IDMEDOC");
                    String nom = rs.getString("NOM");
                    String description = rs.getString("DESCRIPTION");
                    String code = rs.getString("CODE");
                    plusieurs2.add(new Medicament(idmed,nom,description,code));
                }

                if (!trouve) {
                    throw new SQLException("nom inconnu");
                } else {
                    return plusieurs2;
                }
            }
        }
    }

}
