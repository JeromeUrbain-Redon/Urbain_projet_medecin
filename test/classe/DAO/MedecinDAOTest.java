/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classe.DAO;

import java.util.*;
import java.sql.Connection;
import java.sql.SQLException;
import medecin.metier.Medecin;
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
public class MedecinDAOTest {
    static Connection dbConnect;
    public MedecinDAOTest() {
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
     * Test of create method, of class MedecinDAO.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Medecin obj = new Medecin(0,"TestMat","TestNom","TestPrenom","TestTel");
        MedecinDAO instance = new MedecinDAO();
        instance.setConnection(dbConnect);
        Medecin expResult = new Medecin(0,"TestMat","TestNom","TestPrenom","TestTel");
        Medecin result = instance.create(obj);
        assertEquals("noms différents",expResult.getNom(), result.getNom());
        assertEquals("prénoms différents",expResult.getPrenom(), result.getPrenom());
        assertEquals("telephones différents",expResult.getTel(), result.getTel());
        
        assertNotEquals("id non généré",expResult.getIdmed(),result.getIdmed());
        int idmed=result.getIdmed();
        obj = new Medecin(0,"TestMat2","TestNom","TestPrenom","TestTel");
        try{
            Medecin result2 = instance.create(obj);
            fail("exception de doublon non déclenchée");
            instance.delete(result2);
        }
        catch(SQLException e){}
        instance.delete(result);
    }

    /**
     * Test of read method, of class MedecinDAO.
     */
    @Test
    public void testRead() throws Exception {
        System.out.println("read");
        int idmed = 0;
        MedecinDAO instance = new MedecinDAO();
        instance.setConnection(dbConnect);
        Medecin obj = new Medecin(0,"TestMat","TestNom","TestPrenom","TestTel");
        Medecin expResult = instance.create(obj);
        idmed=expResult.getIdmed();
        Medecin result = instance.read(idmed);
        assertEquals("noms différents",expResult.getNom(), result.getNom());
        assertEquals("prénoms différents",expResult.getPrenom(), result.getPrenom());
        assertEquals("telephones différents",expResult.getTel(), result.getTel());
        
        assertEquals("id différents",expResult.getIdmed(),result.getIdmed());
        try{
            result=instance.read(0);
            fail("exception d'id inconnu non générée");
        }
        catch(SQLException e){}
       instance.delete(result);
    }

    /**
     * Test of update method, of class MedecinDAO.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        Medecin obj = new Medecin(0,"TestMat","TestNom","TestPrenom","TestTel");
        MedecinDAO instance = new MedecinDAO();
        instance.setConnection(dbConnect);
        obj = instance.create(obj);
        obj.setMatricule("TestMat2");
        obj.setNom("TestNom2");
        Medecin expResult = obj;
        Medecin result = instance.update(obj);
        assertEquals(expResult.getMatricule(), result.getMatricule());
        assertEquals(expResult.getNom(), result.getNom());
        instance.delete(obj);
    }

    /**
     * Test of delete method, of class MedecinDAO.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Medecin obj = new Medecin(0,"TestMat","TestNom","TestPrenom","TestTel");
        MedecinDAO instance = new MedecinDAO();
        instance.setConnection(dbConnect);
        obj = instance.create(obj);
        instance.delete(obj);
        try {
            instance.read(obj.getIdmed());
            fail("exception de record introuvable non générée");
        }
        catch(SQLException e){}
    }
    
}
