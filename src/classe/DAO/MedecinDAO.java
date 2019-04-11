/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classe.DAO;

/**
 *
 * @author jerom
 */

import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import medecin.metier.Medecin;
import myconnections.DBConnection;
import urbain_projet_medecin.DemoGestion;


public class MedecinDAO extends DAO<Medecin> {
    
    String matricule,nom,prenom,tel = "";
    Statement stmt;
    ResultSet rs = null;
    Medecin med;
    int id;

    /**
     * Sous-menu de la classe medecin permettant d'appeller les methodes CRUD
     * @throws SQLException
     */
    @Override
    public void menu() throws SQLException{
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        int choix;
        do{
            
            Connection dbConnect = DBConnection.getConnection();
            if (dbConnect == null) {
                System.out.println("Echec de la connexion : fin du programme");
                System.exit(1);
            }else{
                System.out.println("Connexion réussie");
            }
            
            System.out.println("1.Ajouter un nouveau medecin");
            System.out.println("2.Afficher les medecins");
            System.out.println("3.Modifier un medecin");
            System.out.println("4.Supprimer un medecin");
            System.out.println("5.Rechercher un medecin sur son nom");
            System.out.println("6.Retour");
            
            do {
                System.out.println("Choix ?");
                choix = sc.nextInt();
                if (choix < 1 || choix > 6) {
                    System.out.println("choix incorrect");
                }

            } while (choix < 1 || choix > 6);
            
            switch(choix){
                case 1:
                    System.out.println("== Création d'un nouveau medecin ==");
                    nouveau();
                    break;
                case 2:
                    System.out.println("== Voir la liste des medecins ==");
                    int idread;
                    System.out.println("Quel est l'id du medecin ?");
                    idread = sc.nextInt();
                    try{
                    read(idread);
                    }
                    catch(SQLException e){
                        System.out.println(" Erreur : "+e);
                    }
                    break;
                case 3:
                    System.out.println("== Modification d'un medecin déjà existant ==");
                    modif();                    
                    break;
                case 4:
                    System.out.println("== Suppresion d'un medecin ==");
                    suppr();
                    break;
                case 5 :
                    System.out.println("== Recherche sur le nom ==");
                    System.out.println("Entrez le nom recherché : ");
                    String nom = sc2.nextLine();
                    try {
                        //appel de la méthode de recherche sur le nom
                        List<Medecin> amed = rechNom(nom);
                        for (Medecin med : amed) {
                            System.out.println(med);
                        }
                    }catch (SQLException e) {
                        System.out.println("erreur " + e.getMessage());
                    }
                    break;
                case 6:
                    System.out.println("Retour au menu");
                    //Retour au menu princpal
                    DemoGestion dg = new DemoGestion();
                    dg.gestion();
                    break;
            }
                
        }while(choix!=6);
    }
    /**
     * création d'un medecin sur base des valeurs de son objet métier
     *
     * @throws SQLException erreur de création
     * @param obj medecin à créer
     * @return medecin créé
     */
    @Override
    public Medecin create(Medecin obj) throws SQLException {
        //Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        String req1 = "insert into medecin(matricule,nom,prenom,tel) values(?,?,?,?)";
        String req2 = "select idmed from medecin where matricule=? and nom=? and prenom=? and tel=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req1);
                PreparedStatement pstm2 = dbConnect.prepareStatement(req2)){
            pstm.setString(1, obj.getMatricule());
            pstm.setString(2, obj.getNom());
            pstm.setString(3, obj.getPrenom());
            pstm.setString(4, obj.getTel());
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new SQLException("erreur de creation medecin, aucune ligne créée");
            }
            pstm2.setString(1, obj.getMatricule());
            pstm2.setString(2, obj.getNom());
            pstm2.setString(3, obj.getPrenom());
            pstm2.setString(4, obj.getTel());
            try (ResultSet rs = pstm2.executeQuery()) {
                if (rs.next()) {
                    int idmed = rs.getInt(1);
                    obj.setIdmed(idmed);
                    return read(idmed);
                } else {
                    throw new SQLException("erreur de création medecin, record introuvable");
                }
            }
        }
    }
    
    /**
     * Methode d'encodage des données à ajouter dans la table medecin
     * @throws SQLException
     */
    public void nouveau() throws SQLException{
        Scanner sc2 = new Scanner(System.in);

        stmt = dbConnect.createStatement();
        rs = stmt.executeQuery("select * from medecin");
        System.out.println("Entrez un matricule : ");
        matricule=sc2.nextLine();
        System.out.println("Entrez un nom : ");
        nom=sc2.nextLine();
        System.out.println("Entrez un prenom : ");
        prenom=sc2.nextLine();
        System.out.println("Entrez un numero de telephone");
        tel=sc2.nextLine();
        id=0;
        med = new Medecin(id,matricule,nom,prenom,tel);
        try {
            med = create(med);
            System.out.println(med);
        } catch (SQLException e) {
            System.out.println("Erreur : " + e);
        } 
    }
    
    /**
     * récupération des données d'un medecin sur base de son identifiant
     *
     * @throws SQLException code inconnu
     * @param idmed identifiant du medecin
     * @return medecin trouvéé
     */

    @Override
    public Medecin read(int idmed) throws SQLException {
        //Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        String req = "select * from medecin where idmed = ?";
        
        try (PreparedStatement pstm = dbConnect.prepareStatement(req)){
            pstm.setInt(1, idmed);
            try (ResultSet rs = pstm.executeQuery()){
                if (rs.next()){
                String nom = rs.getString("NOM");
                String prenom = rs.getString("PRENOM");
                String matricule = rs.getString("MATRICULE");
                String tel = rs.getString("TEL");
                    System.out.println(idmed+" "+matricule+" "+nom+" "+prenom+" "+tel);
                return new Medecin(idmed,matricule,nom,prenom,tel);
                }
                else {
                    throw new SQLException("Code inconnu");
                }
            }
        }
    }
    /**
     * mise à jour des données du medecin sur base de son identifiant
     *
     * @return Medecin
     * @param obj medecin à mettre à jour
     * @throws SQLException erreur de mise à jour
     */
    @Override
    public Medecin update(Medecin obj) throws SQLException {
        //Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        String name,p,m,t;
        Scanner sc = new Scanner(System.in);
        String req = "update medecin set matricule=?, nom=?,prenom=?,tel=? where idmed= ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req)){
            System.out.println("Nouveau matricule ? ");
            m=sc.nextLine();
            System.out.println("Nouveau nom ? ");
            name=sc.nextLine();
            System.out.println("Nouveau prenom ? ");
            p=sc.nextLine();
            System.out.println("Nouveau numero de tel ? ");
            t=sc.nextLine();
            pstm.setInt(5, obj.getIdmed());
            pstm.setString(1, obj.getMatricule());
            pstm.setString(2, obj.getNom());
            pstm.setString(3, obj.getPrenom());
            pstm.setString(4, obj.getTel());
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new SQLException("aucune ligne medecin mise à jour");
            }
            return read(obj.getIdmed());
        }
    }
    
    /**
     * Methode pour encoder les données à mettre à jour dans la table medicament
     * @throws SQLException
     */
    public void modif() throws SQLException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Quel est l'id du medecin à modifier ? ");
        id=sc.nextInt();
        stmt = dbConnect.createStatement();
        rs = stmt.executeQuery("select * from medecin");
        while(rs.next()){
            if(rs.getInt("IDMED") == id){
                matricule = rs.getString("MATRICULE");
                nom = rs.getString("NOM");
                prenom = rs.getString("PRENOM");
                tel = rs.getString("TEL");
                }
        }
        med = new Medecin(id,matricule,nom,prenom,tel);
        try{
            update(med);
            System.out.println("Modification effectuée");
        }
        catch (SQLException e) {
            System.out.println("Erreur : " + e);
        }
    }
    /**
     * effacement du medecin sur base de son identifiant
     *
     * @throws SQLException erreur d'effacement
     * @param obj medecin à effacer
     */
    @Override
    public void delete(Medecin obj) throws SQLException {
        //Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        String req = "delete from medecin where idmed= ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req)) {

            pstm.setInt(1, obj.getIdmed());
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new SQLException("aucune ligne de medecin effacée");
            }

        }
    }
    
    /**
     * Methode pour encoder l'indentifiant du medecin à supprimer
     * @throws SQLException
     */
    public void suppr() throws SQLException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Quel est l'id du medecin à supprimer ? ");
        id=sc.nextInt();
        stmt = dbConnect.createStatement();
        rs = stmt.executeQuery("select * from medecin");
        while(rs.next()){
            if(rs.getInt("IDMED") == id){
                matricule = rs.getString("MATRICULE");
                nom = rs.getString("NOM");
                prenom = rs.getString("PRENOM");
                tel = rs.getString("TEL");
                }
        }
        med = new Medecin(id,matricule,nom,prenom,tel);
        try{
        delete(med);
        System.out.println("Suppression effectuée");
        }
        catch (SQLException e) {
            System.out.println("Erreur : " + e);
        }
    }
    
    public List<Medecin> rechNom(String nomrech) throws SQLException {
        List<Medecin> plusieurs = new ArrayList<>();
        String req = "select * from medecin where nom = ?";

        try (PreparedStatement pstm = dbConnect.prepareStatement(req)) {
            pstm.setString(1, nomrech);
            try (ResultSet rs = pstm.executeQuery()) {
                boolean trouve = false;
                while (rs.next()) {
                    trouve = true;
                    int idmed = rs.getInt("IDMED");
                    String matricule = rs.getString("MATRICULE");
                    String nom = rs.getString("NOM");
                    String prenom = rs.getString("PRENOM");
                    String tel = rs.getString("TEL");
                    plusieurs.add(new Medecin(idmed,matricule,nom,prenom,tel));
                }

                if (!trouve) {
                    throw new SQLException("nom inconnu");
                } else {
                    return plusieurs;
                }
            }
        }             
    }
    
}
