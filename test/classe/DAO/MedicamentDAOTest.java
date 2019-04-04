/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classe.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import medecin.metier.Medicament;
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
public class MedicamentDAOTest {
    static Connection dbConnect;
    public MedicamentDAOTest() {
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
     * Test of menu method, of class MedicamentDAO.
     */
    //@Test
    public void testMenu() throws Exception {
        System.out.println("menu");
        MedicamentDAO instance = new MedicamentDAO();
        instance.menu();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class MedicamentDAO.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Medicament obj = new Medicament(0,"TestNom","TestDescription");
        MedicamentDAO instance = new MedicamentDAO();
        instance.setConnection(dbConnect);
        Medicament expResult = new Medicament(0,"TestNom","TestDescription");
        Medicament result = instance.create(obj);
        assertEquals(expResult, result);
        assertNotEquals("id non généré",expResult.getIdmedoc(),result.getIdmedoc());
        int idmedoc=result.getIdmedoc();
        obj=new Medicament(0,"TestNom","TestDescription");
        try{
            Medicament result2 = instance.create(obj);
            fail("exception de doublon non déclenchée");
            instance.delete(result2);
        }
        catch(SQLException e){}
        instance.delete(result);
    }

    /**
     * Test of nouveau method, of class MedicamentDAO.
     */
    //@Test
    public void testNouveau() throws Exception {
        System.out.println("nouveau");
        MedicamentDAO instance = new MedicamentDAO();
        instance.nouveau();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of read method, of class MedicamentDAO.
     */
    @Test
    public void testRead() throws Exception {
        System.out.println("read");
        int idmedoc = 0;
        MedicamentDAO instance = new MedicamentDAO();
        Medicament expResult = null;
        Medicament result = instance.read(idmedoc);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class MedicamentDAO.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        Medicament obj = null;
        MedicamentDAO instance = new MedicamentDAO();
        Medicament expResult = null;
        Medicament result = instance.update(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modif method, of class MedicamentDAO.
     */
    //@Test
    public void testModif() throws Exception {
        System.out.println("modif");
        MedicamentDAO instance = new MedicamentDAO();
        instance.modif();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class MedicamentDAO.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Medicament obj = null;
        MedicamentDAO instance = new MedicamentDAO();
        instance.delete(obj);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of suppr method, of class MedicamentDAO.
     */
    //@Test
    public void testSuppr() throws Exception {
        System.out.println("suppr");
        MedicamentDAO instance = new MedicamentDAO();
        instance.suppr();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
