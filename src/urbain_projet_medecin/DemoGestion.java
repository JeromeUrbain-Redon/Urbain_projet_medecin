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
import classe.DAO.PrescriptionDAO;
import java.sql.ResultSet;
import java.sql.Statement;
import medecin.metier.Medecin;
import medecin.metier.Medicament;
import medecin.metier.Patient;
import medecin.metier.Prescription;
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
    DAO<Prescription> PrescriptionDAO=null;
    Prescription PresActuel = null;
    
    
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
       PrescriptionDAO = new PrescriptionDAO();
       PrescriptionDAO.setConnection(dbConnect);
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
        System.out.println("5. Fin du programme");
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
                PrescriptionDAO.menu();
                break;
            case 5:System.out.println("Fin du programme");
            System.exit(0);break;
        }
   }

    public static void main(String[] args) throws SQLException {
        DemoGestion dg = new DemoGestion();
        dg.gestion();
    }
    
}
