/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.panks.opclientmanager.imp.jaxws;

import it.panks.opclientmanager.core.OperationDispatcher;
import it.panks.opclientmanager.core.OperationDispatcherProvider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

/**
 *
 * @author paolo.panconi
 */
public class JaxWsDispatcherProvider implements OperationDispatcherProvider {

    private JaxWsOperationConfiguration jaxWsOperationConfiguration;

    public JaxWsDispatcherProvider(JaxWsOperationConfiguration jaxWsOperationConfiguration) {
        this.jaxWsOperationConfiguration = jaxWsOperationConfiguration;
    }

    public JaxWsOperationConfiguration getJaxWsOperationConfiguration() {
        return jaxWsOperationConfiguration;
    }

    @Override
    public OperationDispatcher provideDispatcher() {

        if (getJaxWsOperationConfiguration() == null) {
            throw new IllegalStateException("Illegal operation witout Configuration");
        }
        
        JaxWsOperationConfiguration opConf = getJaxWsOperationConfiguration();

        String soapVersionHttpBinding = SOAPBinding.SOAP11HTTP_BINDING;
        if ("1.2".equals(opConf.getSoapVersion())) {
            soapVersionHttpBinding = SOAPBinding.SOAP12HTTP_BINDING;
        }

        Service service = getWsServiceWithPort(opConf.getServiceName(), 
                opConf.getPortName(),
                opConf.getEndpointAddresss(), 
                soapVersionHttpBinding);
        
        QName port = service.getPorts().next();

        JAXBContext jaxbctx = null;
        try {
            jaxbctx = JAXBContext.newInstance(opConf.getJaxbContextPackage());
        } catch (JAXBException e) {
            throw new RuntimeException("Error creating JAXB Context for package it.postecom.traspbank.wscontracts", e);
        }

        Dispatch<Object> dispatch = service.createDispatch(port, jaxbctx, Service.Mode.PAYLOAD);

        return new JaxWsOperationDispatcher(dispatch, opConf.getOperationId());
    }

    private Service getWsServiceWithPort(QName serviceName, QName portName, String endpointAddress,
        String SOAPVersionHttpBinding) {

        Service service = Service.create(serviceName);
//        service.addPort(portName, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);
        service.addPort(portName, SOAPVersionHttpBinding, endpointAddress);
        
        return service;
    }
}
