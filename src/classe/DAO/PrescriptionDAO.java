/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classe.DAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import medecin.metier.Prescription;
import medecin.metier.Infos;
import myconnections.DBConnection;
import urbain_projet_medecin.DemoGestion;

/**
 *
 * @author jerom
 */
public class PrescriptionDAO extends DAO<Prescription>{
    
    String datepres;
    Statement stmt;
    ResultSet rs = null;
    int id,idmed,idpat;
    Prescription pres;
    
    /**
     * Sous-menu de la classe Prescription appelant les methodes CRUD
     * Ainsi que les vues
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
            
            System.out.println("1.Ajouter une prescription");
            System.out.println("2.Modifier une prescription");
            System.out.println("3.Supprimer une prescription");
            System.out.println("4.Vue globale des prescriptions");
            System.out.println("5.Voir la quantite prescrite d'un medicament ");
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
                    System.out.println("== Rédaction d'une nouvelle prescription ==");
                    nouveau();
                    break;
                case 2:
                    System.out.println("== Modification d'une prescription déjà souscrite ==");
                    modif();
                    break;
                case 3:
                    System.out.println("== Suppression d'une prescription ==");
                    suppr();
                    break;
                case 4:
                    vue();
                    break;
                case 5:
                    pres();
                    break;
                case 6:
                    DemoGestion dg = new DemoGestion();
                    dg.gestion();
                    break;
            }
            
        }while(choix!=6);
    }
    
    /**
     * récupération des données d'une prescription sur base de son identifiant
     *
     * @throws SQLException code inconnu
     * @param idpres identifiant du médicament
     * @return prescription trouvée
     */

    @Override
    public Prescription read(int idpres) throws SQLException {
        if (dbConnect == null) {
            System.exit(1);
        }
        String req = "select * from prescription where idpres = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req)) {
            pstm.setInt(1, idpres);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    String datepres = rs.getString("DATEPRES");
                    int idmed = rs.getInt("idmed");
                    int idpat = rs.getInt("idpat");
                    System.out.println(idpres+"\tMedecin: "+idmed+"\tPatient: "+idpat+"\tDate: "+datepres);
                    return new Prescription(idpres, datepres, idmed, idpat);
                } else {
                    throw new SQLException("Code inconnu");
                }
            }
        }
    }
    
    /**
     * création d'une prescription sur base des valeurs de son objet métier
     *
     * @throws SQLException erreur de création
     * @param obj prescription à créer
     * @return prescription créé
     */

    @Override
    public Prescription create(Prescription obj) throws SQLException {
        if (dbConnect == null) {
            System.exit(1);
        }
        String req1 = "insert into prescription(idpres,datepres,idmed,idpat) values(?,?,?,?)";
        String req2 = "select idpres from prescription where datepres=? and idmed= ?, and idpat=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req1);
                PreparedStatement pstm2 = dbConnect.prepareStatement(req2)){
            pstm.setString(1, obj.getDatepres());
            pstm.setInt(2, obj.getIdmed());
            pstm.setInt(3, obj.getIdpat());
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new SQLException("erreur de creation prescription, aucune ligne créée");
            }
            pstm2.setString(1, obj.getDatepres());
            pstm2.setInt(2, obj.getIdmed());
            pstm2.setInt(3, obj.getIdpat());
            try (ResultSet rs = pstm2.executeQuery()) {
                if (rs.next()) {
                    int idpres = rs.getInt(1);
                    obj.setIdpres(idpres);
                    return read(idpres);
                } else {
                    throw new SQLException("erreur de création prescription, record introuvable");
                }
            }
        }
    }
    
    /**
     * Methode d'encodage des données à ajouter dans la table prescription
     * @throws SQLException
     */
    
    public void nouveau() throws SQLException{
        Scanner sc2 = new Scanner(System.in);

        stmt = dbConnect.createStatement();
        rs = stmt.executeQuery("select * from prescription");
        System.out.println("Entrez la date de prescription");
        datepres=saisir("[0-9]{2}/[0-9]{2}/[0-9]{4}");
        System.out.println("Entrez l'identifiant du medecin rédigeant la prescription :");
        idmed=sc2.nextInt();
        System.out.println("Entrez l'identifiant du patient faisant l'objet de la prescription");
        idpat=sc2.nextInt();
        id=0;
        pres = new Prescription(id,datepres,idmed,idpat);
        try {
            pres = create(pres);
            System.out.println(pres);
        } catch (SQLException e) {
            System.out.println("Erreur : " + e);
        } 
    }
    
    /**
     * mise à jour des données de la prescription sur base de son identifiant
     *
     * @return Prescription
     * @param obj prescription à mettre à jour
     * @throws SQLException erreur de mise à jour
     */

    @Override
    public Prescription update(Prescription obj) throws SQLException {
        if (dbConnect == null) {
            System.exit(1);
        }
        String req = "update prescription set datepres=? ,idmed=? ,idpat=? where idpres= ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req)) {
            pstm.setInt(4, obj.getIdpres());
            pstm.setString(1,obj.getDatepres());
            pstm.setInt(2, obj.getIdmed());
            pstm.setInt(3, obj.getIdpat());
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new SQLException("aucune ligne prescription mise à jour");
            }
            return read(obj.getIdpres());

        }
    }
    
    /**
     * Methode pour encoder les données à mettre à jour dans la table prescription
     * @throws SQLException
     */
    
    public void modif() throws SQLException{
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        System.out.println("Quel est l'id de la prescription à modifier ? ");
        id=sc.nextInt();

        stmt = dbConnect.createStatement();
        rs = stmt.executeQuery("select * from prescription");
        while(rs.next()){
            if(rs.getInt("IDPRES") == id){
                datepres = rs.getString("DATEPRES");
                idmed = rs.getInt("IDMED");
                idpat = rs.getInt("IDPAT");
                }
        }
        System.out.println("Nouvelle date de prescription ? ");
        datepres=saisir("[0-9]{2}/[0-9]{2}/[0-9]{4}");
        System.out.println("Changer de medecin assigné à cette prescription ? ");
        idmed=sc2.nextInt();
        System.out.println("Changer le patient concerné par cette description ? ");
        idpat=sc2.nextInt();
        pres = new Prescription(id,datepres,idmed,idpat);
        try{
            update(pres);
            System.out.println("Modification effectuée");
        }
        catch (SQLException e) {
            System.out.println("Erreur : " + e);
        } 
    }
    
    /**
     * effacement de la prescription sur base de son identifiant
     *
     * @throws SQLException erreur d'effacement
     * @param obj prescription à effacer
     */

    @Override
    public void delete(Prescription obj) throws SQLException {
        if (dbConnect == null) {
            System.exit(1);
        }
        String req = "delete from prescription where idpres= ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req)) {

            pstm.setInt(1, obj.getIdpres());
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new SQLException("aucune ligne de prescription effacée");
            }

        }
    }
    
    /**
     * Methode pour encoder l'indentifiant de la prescription à supprimer
     * @throws SQLException
     */
    
        public void suppr() throws SQLException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Quel est l'id de la prescription à supprimer ? ");
        id=sc.nextInt();
        stmt = dbConnect.createStatement();
        rs = stmt.executeQuery("select * from prescription");
        while(rs.next()){
            if(rs.getInt("IDPRES") == id){
                datepres = rs.getString("DATEPRES");
                idmed = rs.getInt("IDMED");
                idpat = rs.getInt("IDPAT");
                }
        }
        pres = new Prescription(id,datepres,idmed,idpat);
        try{
            delete(pres);
            System.out.println("Modification effectuée");
        }
        catch (SQLException e) {
            System.out.println("Erreur : " + e);
        } 
    }
    
    /**
     * Méthode de la vue affichant les informations globales d'une prescription données
     */
    
    public void vue() throws SQLException{

        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        int i;
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez le numero de la prescription recherchée : ");
        i=sc.nextInt();
        //System.out.println("connexion établie");
        String query = "select * from INFO_PRESCRIPTION WHERE idpres="+i;
        try (Statement stmt = dbConnect.createStatement();
                ResultSet rs = stmt.executeQuery(query);) {

            while (rs.next()) {
                String n = "" + rs.getInt("IDPRES");
                String nom = rs.getString("NOM");
                String quantite = rs.getString("QUANTITE");
                String unite = rs.getString("UNITE");
                String description = rs.getString("DESCRIPTION");
                
                System.out.println(n+"\t "+nom+"\t qtté: "+quantite+"\t "+unite+"\t desc: "+description);
            }
        } catch (SQLException e) {
            System.out.println("erreur SQL " + e);
        }
        
        DBConnection.closeConnection();
    }
    
    /**
     * Méthode affichant la quantité totale d'un médicament toute prescription confondues
     */
    
    public void pres() throws SQLException{
       Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        int i;
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez le numero du médicament recherché : ");
        i=sc.nextInt();
        //System.out.println("connexion établie");
        String query = "select sum(quantite) AS QTOT from INFO_PRESCRIPTION WHERE idmedoc="+i;
        String query2 = "select DISTINCT nom,unite FROM INFO_PRESCRIPTION WHERE idmedoc="+i;
        try (Statement stmt = dbConnect.createStatement();
                ResultSet rs = stmt.executeQuery(query2);) {

            while (rs.next()) {
                String nom = rs.getString("NOM");
                String unite = rs.getString("UNITE");
                
                System.out.println("Quantité totale prescrite pour "+nom+" "+unite+" : ");
                
            }
        } catch (SQLException e) {
            System.out.println("erreur SQL " + e);
        }
        try (Statement stmt = dbConnect.createStatement();
                ResultSet rs = stmt.executeQuery(query);) {

            while (rs.next()) {
                String quantite = rs.getString("QTOT");
                
                System.out.println(quantite);
                
                
            }
        } catch (SQLException e) {
            System.out.println("erreur SQL " + e);
        }
        DBConnection.closeConnection();
       
   }
    
    public String saisir(String regex){
       boolean flag=false;
       Scanner sc=new Scanner(System.in);
       String tmp;
       do
       {
       tmp=sc.nextLine();
       if(tmp.matches(regex))
       {
           flag=true;
       }
       else{
           System.out.println("Erreur de saisie ! ");
       }
       }
       while(!flag);
       return tmp;
    }
    
}
