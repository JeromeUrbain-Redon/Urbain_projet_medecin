/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classe.DAO;

import java.sql.*;
import medecin.metier.Patient;
import java.util.*;
import myconnections.DBConnection;
import urbain_projet_medecin.DemoGestion;

/**
 *
 * @author jerom
 */
public class PatientDAO extends DAO<Patient>{
    
    String nom,prenom,tel = "";
    Statement stmt;
    ResultSet rs = null;
    Patient pat;
    int id;
    
    /**
     * Sous-menu de la classe Patient appelant les methodes CRUD
     * @throws SQLException
     */
    @Override
    public void menu() throws SQLException{
        Scanner sc = new Scanner(System.in);
        int choix;
        do{
            
            Connection dbConnect = DBConnection.getConnection();
            if (dbConnect == null) {
                System.out.println("Echec de la connexion : fin du programme");
                System.exit(1);
            }else{
                System.out.println("Connexion réussie");
            }
            
            System.out.println("1.Ajouter un nouveau patient");
            System.out.println("2.Afficher les patients");
            System.out.println("3.Modifier un patient");
            System.out.println("4.Supprimer un patient");
            System.out.println("5.Retour");
            
            do {
                System.out.println("Choix ?");
                choix = sc.nextInt();
                if (choix < 1 || choix > 5) {
                    System.out.println("choix incorrect");
                }

            } while (choix < 1 || choix > 5);
            
            switch(choix){
                case 1:
                    System.out.println("== Encodage d'un nouveau patient ==");
                    nouveau();
                    break;
                case 2:
                    System.out.println("== Voir la liste des patients ==");
                    int idread;
                    System.out.println("Quel est l'id du patient ?");
                    idread = sc.nextInt();
                    try{
                    read(idread);
                    }
                    catch(SQLException e){
                        System.out.println(" Erreur : "+e);
                    }
                    break;
                case 3:
                    System.out.println("== Modification d'un patient déjà encodé ==");
                    modif();                    
                    break;
                case 4:
                    System.out.println("== Suppresion d'un patient ==");
                    suppr();
                    break;
                case 5:
                    System.out.println("Retour au menu");
                    //Retour au menu principal
                    DemoGestion dg = new DemoGestion();
                    dg.gestion();
                    break;
            }
                
        }while(choix!=5);
    }

    @Override
    public Patient read(int idpat) throws SQLException {
        //Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        String req = "select * from patient where idpat = ?";
        
        try (PreparedStatement pstm = dbConnect.prepareStatement(req)){
            pstm.setInt(1, idpat);
            try (ResultSet rs = pstm.executeQuery()){
                if (rs.next()){
                String nom = rs.getString("NOM");
                String prenom = rs.getString("PRENOM");
                String tel = rs.getString("TEL");
                    System.out.println(idpat+" "+nom+" "+prenom+" "+tel);
                return new Patient(idpat,nom,prenom,tel);
                }
                else {
                    throw new SQLException("Code inconnu");
                }
            }
        }
    }
    
    /**
     * création d'un patient sur base des valeurs de son objet métier
     *
     * @throws SQLException erreur de création
     * @param obj patient à créer
     * @return patient créé
     */

    @Override
    public Patient create(Patient obj) throws SQLException {
        //Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        String req1 = "insert into patient(nom,prenom,tel) values(?,?,?)";
        String req2 = "select idpat from patient where nom=? and prenom=? and tel=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req1);
                PreparedStatement pstm2 = dbConnect.prepareStatement(req2)){
            pstm.setString(1, obj.getNom());
            pstm.setString(2, obj.getPrenom());
            pstm.setString(3, obj.getTel());
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new SQLException("erreur de creation patient, aucune ligne créée");
            }
            pstm2.setString(1, obj.getNom());
            pstm2.setString(2, obj.getPrenom());
            pstm2.setString(3, obj.getTel());
            try (ResultSet rs = pstm2.executeQuery()) {
                if (rs.next()) {
                    int idpat = rs.getInt(1);
                    obj.setIdpat(idpat);
                    return read(idpat);
                } else {
                    throw new SQLException("erreur de création patient, record introuvable");
                }
            }
        }
    }
    
    /**
     * Methode d'encodage des données à ajouter dans la table patient
     * @throws SQLException
     */
    public void nouveau() throws SQLException{
        Scanner sc2 = new Scanner(System.in);

        stmt = dbConnect.createStatement();
        rs = stmt.executeQuery("select * from patient");;
        System.out.println("Entrez un nom : ");
        nom=sc2.nextLine();
        System.out.println("Entrez un prenom : ");
        prenom=sc2.nextLine();
        System.out.println("Entrez un numero de telephone");
        tel=sc2.nextLine();
        id=0;
        pat = new Patient(id,nom,prenom,tel);
        try {
            pat = create(pat);
            System.out.println(pat);
        } catch (SQLException e) {
            System.out.println("Erreur : " + e);
        } 
    }
    
    /**
     * mise à jour des données du patient sur base de son identifiant
     *
     * @return Patient
     * @param obj patient à mettre à jour
     * @throws SQLException erreur de mise à jour
     */

    @Override
    public Patient update(Patient obj) throws SQLException {
        //Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        String name,p,t;
        Scanner sc = new Scanner(System.in);
        String req = "update patient set nom=?,prenom=?,tel=? where idpat= ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req)){
            System.out.println("Nouveau nom ? ");
            name=sc.nextLine();
            System.out.println("Nouveau prenom ? ");
            p=sc.nextLine();
            System.out.println("Nouveau numero de tel ? ");
            t=sc.nextLine();
            pstm.setInt(4, obj.getIdpat());
            pstm.setString(1, obj.getNom());
            pstm.setString(2, obj.getPrenom());
            pstm.setString(3, obj.getTel());
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new SQLException("aucune ligne patient mise à jour");
            }
            return read(obj.getIdpat());
        }
    }
    
    /**
     * Methode pour encoder les données à mettre à jour dans la table medicament
     * @throws SQLException
     */
    public void modif() throws SQLException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Quel est l'id du patient à modifier ? ");
        id=sc.nextInt();
        stmt = dbConnect.createStatement();
        rs = stmt.executeQuery("select * from patient");
        while(rs.next()){
            if(rs.getInt("IDPAT") == id){
                nom = rs.getString("NOM");
                prenom = rs.getString("PRENOM");
                tel = rs.getString("TEL");
                }
        }
        pat = new Patient(id,nom,prenom,tel);
        try{
            update(pat);
            System.out.println("Modification effectuée");
        }
        catch (SQLException e) {
            System.out.println("Erreur : " + e);
        }
    }
    
    /**
     * effacement du patient sur base de son identifiant
     *
     * @throws SQLException erreur d'effacement
     * @param obj patient à effacer
     */

    @Override
    public void delete(Patient obj) throws SQLException {
        //Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        String req = "delete from patient where idpat= ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req)) {

            pstm.setInt(1, obj.getIdpat());
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new SQLException("aucune ligne de patient effacée");
            }

        }
    }
    
    /** 
     * Methode pour encoder l'indentifiant du médecin à supprimer
     * @throws SQLException
     */
    public void suppr() throws SQLException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Quel est l'id du patient à supprimer ? ");
        id=sc.nextInt();
        stmt = dbConnect.createStatement();
        rs = stmt.executeQuery("select * from patient");
        while(rs.next()){
            if(rs.getInt("IDPAT") == id){
                nom = rs.getString("NOM");
                prenom = rs.getString("PRENOM");
                tel = rs.getString("TEL");
                }
        }
        pat = new Patient(id,nom,prenom,tel);
        try{
        delete(pat);
        System.out.println("Suppression effectuée");
        }
        catch (SQLException e) {
            System.out.println("Erreur : " + e);
        }
    }
    
}
