/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.panks.opclientmanager.conf.imp;

import java.util.Properties;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author paolo.panconi
 */
public class PropertyStringReplacerTest {
    
    public PropertyStringReplacerTest() {
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
     * Test of replace method, of class PropertyStringReplacer.
     */
    @Test
    public void testReplace() throws Exception {
        System.out.println("replace");
        
        String v = "${crm.protocol}://${crm.host}:${crm.port}/customerepo/${crm.host}/CustomerService";
        Properties props = new Properties();
        props.setProperty("crm.protocol", "http");
        props.setProperty("crm.host", "localhost");
        props.setProperty("crm.port", "8085");
        PropertyStringReplacer instance = new PropertyStringReplacer(v, props);
        
        String expResult = "http://localhost:8085/customerepo/localhost/CustomerService";
        String result = instance.replace();
        assertEquals(expResult, result);
    }
    
}
