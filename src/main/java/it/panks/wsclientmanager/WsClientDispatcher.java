/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.panks.wsclientmanager;

import javax.xml.bind.JAXBContext;
import javax.xml.namespace.QName;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

/**
 * Super class utility for service proxies.
 *
 * Use JAX_WS dispatch dynamic invocation. This class could be totaly configured
 * by external properties.
 *
 * @author Paolo Panconi (paolo.panconi@avanade.com)
 */
public class WsClientDispatcher {

    /**
     * Invoke the service named by the logical endpoint The invocation is a
     * massage dispatch using JABX object as payload,
     *
     * @param <R>
     * @param serviceName service name
     * @param serviceCategory service name category
     * @param <T> req JAXB object mapping the message request for the service.
     * This object must be XmlRootElement annotated to directly xml marshaling.
     * @param jaxbcxt
     * @param responseType the type for the object response. Also this type must
     * be @XmlRootElement annotated
     * @param endpointAddress
     *
     * @return the JAXB object response
     */
    static public <T, R> R invokeService(QName serviceName, QName portName, 
            T req, Class<R> responseType,
            JAXBContext jaxbcxt, String endpointAddress) {

        Service service = getWsServiceWithPort(serviceName, portName, endpointAddress);
        QName port = service.getPorts().next();

        Dispatch<Object> dispatch = service.createDispatch(port, jaxbcxt, Service.Mode.PAYLOAD);

        Object resp = dispatch.invoke(req);

        return responseType.cast(resp);
    }

    static private Service getWsServiceWithPort(QName serviceName, QName portName, String endpointAddress) {

        Service service = Service.create(serviceName);
        service.addPort(portName, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);

        return service;
    }

}
