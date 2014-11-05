/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.panks.wsclient.customerepo;

import it.panks.customerepo.model.ClientProspet;
import it.panks.customerepo.service.CustomerService;
import it.panks.customerepo.service.FetchClientProspect;
import it.panks.customerepo.service.FetchClientProspectResponse;
import it.panks.wsclientmanager.WsClientDispatcher;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;

/**
 *
 * @author paolo.panconi
 */
public class CustomerServiceClient {

    public ClientProspet loadClient(Long id) {

        QName srvName = new QName("http://service.customerepo.panks.it/", "CustomerService");
        QName portName = new QName("http://service.customerepo.panks.it/", "CustomerServicePort");
        FetchClientProspect req = new FetchClientProspect();
        req.setId(id);

        JAXBContext jaxbctx = null;
        try {
            jaxbctx = JAXBContext.newInstance(it.panks.customerepo.service.ObjectFactory.class);
        } catch (JAXBException e) {
            throw new RuntimeException("Error creating JAXB Context for package it.postecom.traspbank.wscontracts", e);
        }
        
        FetchClientProspectResponse c = WsClientDispatcher.invokeService(srvName, portName,
                req,
                FetchClientProspectResponse.class,
                jaxbctx,
                "http://localhost:8085/customerepo/services/CustomerService");

        if (c != null) {
            return c.getReturn();
        } else {
            return null;
        }

    }
}
