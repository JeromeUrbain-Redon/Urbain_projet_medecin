/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classe.DAO;

import java.util.*;
import java.sql.Connection;
import java.sql.SQLException;
import medecin.metier.Patient;
import myconnections.DBConnection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jerom
 */
public class PatientDAOTest {
    static Connection dbConnect;
    public PatientDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.out.println("connection invalide");
            System.exit(1);
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
        DBConnection.closeConnection();
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of menu method, of class PatientDAO.
     */
    //@Test
    public void testMenu() throws Exception {
        System.out.println("menu");
        PatientDAO instance = new PatientDAO();
        instance.menu();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of read method, of class PatientDAO.
     */
    @Test
    public void testRead() throws Exception {
        System.out.println("read");
        int idpat = 0;
        PatientDAO instance = new PatientDAO();
        instance.setConnection(dbConnect);
        Patient obj = new Patient(0,"TestNom","TestPrenom","TestTel");
        Patient expResult = instance.create(obj);
        idpat=expResult.getIdpat();
        Patient result = instance.read(idpat);
        assertEquals("noms différents",expResult.getNom(), result.getNom());
        assertEquals("prénoms différents",expResult.getPrenom(), result.getPrenom());
        assertEquals("telephones différents",expResult.getTel(), result.getTel());
        
        assertEquals("id différents",expResult.getIdpat(),result.getIdpat());
        try{
            result=instance.read(0);
            fail("exception d'id inconnu non générée");
        }
        catch(SQLException e){}
       instance.delete(result);
    }

    /**
     * Test of create method, of class PatientDAO.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Patient obj = new Patient(0,"TestNom","TestPrenom","TestTel");
        PatientDAO instance = new PatientDAO();
        instance.setConnection(dbConnect);
        Patient expResult = new Patient(0,"TestNom","TestPrenom","TestTel");
        Patient result = instance.create(obj);
        assertEquals("noms différents",expResult.getNom(), result.getNom());
        assertEquals("prénoms différents",expResult.getPrenom(), result.getPrenom());
        assertEquals("telephones différents",expResult.getTel(), result.getTel());
        
        assertNotEquals("id non généré",expResult.getIdpat(),result.getIdpat());
        int idmed=result.getIdpat();
        obj = new Patient(0,"TestNom","TestPrenom","TestTel");
        try{
            Patient result2 = instance.create(obj);
            fail("exception de doublon non déclenchée");
            instance.delete(result2);
        }
        catch(SQLException e){}
        instance.delete(result);
    }

    /**
     * Test of nouveau method, of class PatientDAO.
     */
    //@Test
    public void testNouveau() throws Exception {
        System.out.println("nouveau");
        PatientDAO instance = new PatientDAO();
        instance.nouveau();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class PatientDAO.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        Patient obj = new Patient(0,"TestNom","TestPrenom","TestTel");
        PatientDAO instance = new PatientDAO();
        instance.setConnection(dbConnect);
        obj = instance.create(obj);
        obj.setNom("TestNom2");
        Patient expResult = obj;
        Patient result = instance.update(obj);
        assertEquals(expResult.getNom(), result.getNom());
        instance.delete(obj);
    }

    /**
     * Test of modif method, of class PatientDAO.
     */
    //@Test
    public void testModif() throws Exception {
        System.out.println("modif");
        PatientDAO instance = new PatientDAO();
        instance.modif();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class PatientDAO.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Patient obj = new Patient(0,"TestNom","TestPrenom","TestTel");
        PatientDAO instance = new PatientDAO();
        instance.setConnection(dbConnect);
        obj = instance.create(obj);
        instance.delete(obj);
        try {
            instance.read(obj.getIdpat());
            fail("exception de record introuvable non générée");
        }
        catch(SQLException e){}
    }

    /**
     * Test of suppr method, of class PatientDAO.
     */
    //@Test
    public void testSuppr() throws Exception {
        System.out.println("suppr");
        PatientDAO instance = new PatientDAO();
        instance.suppr();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rechNom method, of class PatientDAO.
     */
    //@Test
    public void testRechNom() throws Exception {
        System.out.println("rechNom");
        String nomrech = "";
        PatientDAO instance = new PatientDAO();
        List<Patient> expResult = null;
        List<Patient> result = instance.rechNom(nomrech);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
