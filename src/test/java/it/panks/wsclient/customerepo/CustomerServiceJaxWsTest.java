/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.panks.wsclient.customerepo;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import it.panks.customerepo.model.ClientProspet;
import it.panks.customerepo.service.CrmException_Exception;
import it.panks.customerepo.service.CustomerService;
import it.panks.customerepo.service.CustomerServicePortType;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;

/**
 * This test call a web service using default jax-ws SEI interface It's used to
 * test the traditional behavior for endpoint invocation
 *
 * @author paolo.panconi
 */
public class CustomerServiceJaxWsTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8085);

    public CustomerServiceJaxWsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
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

    @Test
    public void fetctSEI() throws CrmException_Exception {

        CustomerService service = new CustomerService();
        CustomerServicePortType port = service.getCustomerServicePort();

        ClientProspet r = port.fetchClientProspect(1L);
        System.out.println(r.getDenomination());
        assertEquals(r.getDenomination(), "Paul Panks");
    }

    @Test
    public void test1000Invoke() throws CrmException_Exception {

        long t1 = System.currentTimeMillis();

        for (int i = 0; i < 1000; i++) {
            CustomerService service = new CustomerService();
            CustomerServicePortType port = service.getCustomerServicePort();

            ClientProspet r = port.fetchClientProspect(1L);
            System.out.println(r.getDenomination());
            assertEquals(r.getDenomination(), "Paul Panks");
        }

        long t2 = System.currentTimeMillis();

        System.out.printf("1000 remote invocation with JAX-WS sei client :%d millisec \n", t2 - t1);

    }

}
