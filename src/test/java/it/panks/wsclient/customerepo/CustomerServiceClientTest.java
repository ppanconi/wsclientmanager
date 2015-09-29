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

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import it.panks.customerepo.service.FetchClientProspectResponse;
import it.panks.opclientmanager.core.OperationAsyncHandler;
import it.panks.opclientmanager.core.OperationResponse;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.junit.Rule;
/**
 *
 * @author paolo.panconi
 */
public class CustomerServiceClientTest {
    
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8888);
    
    public CustomerServiceClientTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
//        ApiConfigurationProvider conf =  new ApiConfigurationProvider();
//        JaxWsOperationConfiguration servconf = new JaxWsOperationConfiguration();
//        conf.getConfigurations().add(servconf);
//        
//        servconf.setOperationId("fetchClientProspect");
//        servconf.setJaxbContextPackage("it.panks.customerepo.service");
//        servconf.setServiceName(new QName("http://service.customerepo.panks.it/", "CustomerService"));
//        servconf.setPortName(new QName("http://service.customerepo.panks.it/", "CustomerServicePort"));
//        servconf.setEndpointAddresss("http://localhost:8089/customerepo/services/CustomerService");
//        servconf.setSoapVersion("1.1");
//        
//        ConfigurationrManager.getInstance().synchConfiguration(conf);
//        
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        stubFor(post(urlEqualTo("/customerepo/services/CustomerService"))
//            .withHeader("Content-Type", equalTo("text/xml"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "text/xml;charset=UTF-8")
                .withBody("<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><ns1:fetchClientProspectResponse xmlns:ns1=\"http://service.customerepo.panks.it/\"><ns1:return><ns2:denomination xmlns:ns2=\"http://model.customerepo.panks.it\">Paul Panks</ns2:denomination><ns2:fiscalcode xmlns:ns2=\"http://model.customerepo.panks.it\">16677399223</ns2:fiscalcode><ns2:id xmlns:ns2=\"http://model.customerepo.panks.it\">1</ns2:id><ns2:version xmlns:ns2=\"http://model.customerepo.panks.it\">2</ns2:version></ns1:return></ns1:fetchClientProspectResponse></soap:Body></soap:Envelope>")));
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
        assertEquals(result.getDenomination(), "Paul Panks");
        
    }
    
    /**
     * Test of loadClient method, of class CustomerServiceClient.
     */
    @Test
    public void testAsynchLoadClient() {
        System.out.println("loadClient");
        Long id = 1L;
        CustomerServiceClient instance = new CustomerServiceClient();
        Future result = instance.loadClientAsynch(id, new OperationAsyncHandler<FetchClientProspectResponse>() {

            @Override
            public void handleResponse(OperationResponse<FetchClientProspectResponse> res) {
                String denom;
                try {
                    denom = res.get().getReturn().getDenomination();
                    assertEquals(denom, "Paul Panks");
                } catch (InterruptedException ex) {
                    Logger.getLogger(CustomerServiceClientTest.class.getName()).log(Level.SEVERE, null, ex);
                    throw new RuntimeException(ex);
                } catch (ExecutionException ex) {
                    Logger.getLogger(CustomerServiceClientTest.class.getName()).log(Level.SEVERE, null, ex);
                    throw new RuntimeException(ex);
                }
                
        
            }
        });
        while (! result.isDone()) {            
            try {
                System.out.println("Waiting...");
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(CustomerServiceClientTest.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        }
    }

    @Test
    public void test1000Invoke() {
        
        long t1 = System.currentTimeMillis();
        
        for (int i = 0; i < 1000; i++) {
            Long id = 1L;
            CustomerServiceClient instance = new CustomerServiceClient();
            ClientProspet result = instance.loadClient(id);
            System.out.println(result.getDenomination());
            assertEquals(result.getDenomination(), "Paul Panks");
     
        }
        
        long t2 = System.currentTimeMillis();
        
        System.out.printf("1000 remote calls with optclientmanager OperationDispatcher :%d millisecs \n", t2 - t1);
        
    }
}
