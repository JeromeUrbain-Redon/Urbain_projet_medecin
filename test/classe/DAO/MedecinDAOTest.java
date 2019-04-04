/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classe.DAO;

import medecin.metier.Medecin;
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
    
    public MedecinDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
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
        Medecin obj = null;
        MedecinDAO instance = new MedecinDAO();
        Medecin expResult = null;
        Medecin result = instance.create(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of read method, of class MedecinDAO.
     */
    @Test
    public void testRead() throws Exception {
        System.out.println("read");
        int idmed = 0;
        MedecinDAO instance = new MedecinDAO();
        Medecin expResult = null;
        Medecin result = instance.read(idmed);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class MedecinDAO.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        Medecin obj = null;
        MedecinDAO instance = new MedecinDAO();
        Medecin expResult = null;
        Medecin result = instance.update(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class MedecinDAO.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Medecin obj = null;
        MedecinDAO instance = new MedecinDAO();
        instance.delete(obj);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
