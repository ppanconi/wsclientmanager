/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.panks.wsclient.customerepo;

import it.panks.customerepo.model.ClientProspet;
import it.panks.opclientmanager.conf.ConfigurationrManager;
import it.panks.opclientmanager.conf.imp.ApiConfigurationProvider;
import it.panks.opclientmanager.conf.imp.XMLConfiguration;
import it.panks.opclientmanager.imp.jaxws.JaxWsOperationConfiguration;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
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
public class CustomerServiceClientTest {
    
    public CustomerServiceClientTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        ApiConfigurationProvider conf =  new ApiConfigurationProvider();
        JaxWsOperationConfiguration servconf = new JaxWsOperationConfiguration();
        conf.getConfigurations().add(servconf);
        
        servconf.setOperationId("fetchClientProspect");
        servconf.setJaxbContextPackage("it.panks.customerepo.service");
        servconf.setServiceName(new QName("http://service.customerepo.panks.it/", "CustomerService"));
        servconf.setPortName(new QName("http://service.customerepo.panks.it/", "CustomerServicePort"));
        servconf.setEndpointAddresss("http://localhost:8085/customerepo/services/CustomerService");
//        servconf.setSoapVersion("1.1");
        
        ConfigurationrManager.getInstance().synchConfiguration(conf);
        
        XMLConfiguration confs = new XMLConfiguration();
        confs.getConfiguration().add(servconf);
        
        try {
            JAXBContext.newInstance(XMLConfiguration.class).createMarshaller().marshal(confs, new File("/opt/conf.xml"));
        } catch (JAXBException ex) {
            Logger.getLogger(CustomerServiceClientTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
     * Test of loadClient method, of class CustomerServiceClient.
     */
    @Test
    public void testLoadClient() {
        System.out.println("loadClient");
        Long id = 1L;
        CustomerServiceClient instance = new CustomerServiceClient();
        ClientProspet result = instance.loadClient(id);
        System.out.println(result.getDenomination());
        assertEquals(result.getDenomination(), "Segone");
        
    }
    
}
