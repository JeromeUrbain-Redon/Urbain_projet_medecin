/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urbain_projet_medecin;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.*;
import java.util.*;
import classe.DAO.MedecinDAO;
import classe.DAO.MedicamentDAO;
import classe.DAO.DAO;
import classe.DAO.PatientDAO;
import java.sql.ResultSet;
import java.sql.Statement;
import medecin.metier.Medecin;
import medecin.metier.Medicament;
import medecin.metier.Patient;
import myconnections.DBConnection;


public class DemoGestion {

    /**
     * @param args the command line arguments
     */
    Connection dbConnect = DBConnection.getConnection();
    DAO<Medecin> MedecinDAO = null;
    Medecin MedActuel = null;
    DAO<Medicament> MedicamentDAO = null;
    Medicament MedocActuel = null;
    DAO<Patient> PatientDAO = null;
    Patient PatActuel = null;
    
    /**
     * Menu principal du programme appellant les différentes classes 
     * @throws SQLException
     */
    public void gestion() throws SQLException{
       
       MedicamentDAO = new MedicamentDAO();
       MedicamentDAO.setConnection(dbConnect);
       MedecinDAO = new MedecinDAO();
       MedecinDAO.setConnection(dbConnect);
       PatientDAO = new PatientDAO();
       PatientDAO.setConnection(dbConnect);
       if (dbConnect == null) {
            System.out.println("connection invalide");
            System.exit(1);
        }

        System.out.println("connexion établie");
        
        Scanner sc = new Scanner(System.in);
        int choix;

        System.out.println("1. Gérer les médicaments");
        System.out.println("2. Gérer les médecins");
        System.out.println("3. Gérer les patients");
        System.out.println("4. Gérer les prescriptions");
        System.out.println("5. Vue globale des prescriptions");
        System.out.println("6. Voir la quantite prescrite d'un medicament ");
        System.out.println("7. Fin du programme");
        do {
                System.out.println("Choix ?");
                choix = sc.nextInt();
                if (choix < 1 || choix > 7) {
                    System.out.println("choix incorrect");
                }

            } while (choix < 1 || choix > 7);
        
        switch(choix){
            case 1:                
                MedicamentDAO.menu();
                break;
            case 2:
                MedecinDAO.menu();
                break;
            case 3:
                PatientDAO.menu();
                break;
            case 4:
                System.out.println("Cette fonctionnalité n'est pas encore inclue");
                //Retour au menu
                DemoGestion dg = new DemoGestion();
                dg.gestion();
                break;
            case 5:
                vue();
                break;
            case 6 :
                pres();
                break;
            case 7:System.out.println("Fin du programme");
            System.exit(0);break;
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
                
                System.out.println(n+"\t "+nom+"\t "+quantite+"\t "+unite+"\t "+description);
            }
        } catch (SQLException e) {
            System.out.println("erreur SQL " + e);
        }
        
        DBConnection.closeConnection();
        
        //Retour au menu
        DemoGestion dg = new DemoGestion();
        dg.gestion();
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
        //Retour au menu
        DemoGestion dg = new DemoGestion();
        dg.gestion();
       
   }
    public static void main(String[] args) throws SQLException {
        DemoGestion dg = new DemoGestion();
        dg.gestion();
    }
    
}
