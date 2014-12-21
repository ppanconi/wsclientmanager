/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.panks.opclientmanager.conf.imp;

import it.panks.opclientmanager.conf.BaseOperationConfiguration;
import it.panks.opclientmanager.imp.jaxws.JaxWsOperationConfiguration;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
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
public class XMLFileConfigurationProviderTest {
    
    public XMLFileConfigurationProviderTest() {
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
     * Test of provideConfigurations method, of class XMLFileConfigurationProvider.
     */
    @Test
    public void testProvideConfigurations() throws Exception {
        System.out.println("provideConfigurations");
        
        File file = new File(getClass().getResource("/opclient-config.xml").getFile());
        Properties props = new Properties();
        props.load(new FileInputStream(new File(getClass().getResource("/opclient-config.properties").getFile())));
        
        XMLFileConfigurationProvider instance = new XMLFileConfigurationProvider(file, props);
        
        List<BaseOperationConfiguration> result = instance.provideConfigurations();
        JaxWsOperationConfiguration c = (JaxWsOperationConfiguration) result.get(0);
        assertEquals("http://localhost:8085/customerepo/services/CustomerService", c.getEndpointAddresss());
    }
    
}
