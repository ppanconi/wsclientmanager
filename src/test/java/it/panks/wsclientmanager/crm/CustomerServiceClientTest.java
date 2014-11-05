/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.panks.wsclientmanager.crm;

import it.panks.customerepo.model.ClientProspet;
import it.panks.customerepo.service.CrmException_Exception;
import it.panks.customerepo.service.CustomerService;
import it.panks.customerepo.service.CustomerServicePortType;
import it.panks.wsclient.customerepo.CustomerServiceClient;
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
    
    CustomerServiceClient client;
    
    CustomerService service;
    CustomerServicePortType port;
    
    
    public CustomerServiceClientTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.service  = new CustomerService();
        this.port = service.getCustomerServicePort();
        
        this.client = new CustomerServiceClient();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void hello() {
        String r = port.hello("panks");
        System.out.println(r);
        assertEquals(r, "Hello panks !");
    }
    
    @Test
    public void fetctSEI() throws CrmException_Exception {
        ClientProspet r = port.fetchClientProspect(1L);
        System.out.println(r.getDenomination());
        assertEquals(r.getDenomination(), "Segone");
    }
    
    @Test
    public void fetct() throws CrmException_Exception {
        ClientProspet r = client.loadClient(1L);
        System.out.println(r.getDenomination());
        assertEquals(r.getDenomination(), "Segone");
    }
    
}
