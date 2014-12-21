/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.panks.opclientmanager.imp.jaxws;

import it.panks.opclientmanager.conf.BaseOperationConfiguration;
import it.panks.opclientmanager.conf.OperationConfiguration;
import it.panks.opclientmanager.conf.imp.XMLConfPropHolderAdapter;
import it.panks.opclientmanager.core.OperationDispatcherProvider;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;

/**
 *
 * @author paolo.panconi
 */
@XmlType(namespace = "http://opclientmanager.org")
@XmlAccessorType(XmlAccessType.FIELD)
public class JaxWsOperationConfiguration extends BaseOperationConfiguration implements OperationConfiguration {

    @XmlElement
    private QName serviceName;
    @XmlElement    
    private QName portName;
    @XmlElement
    private String jaxbContextPackage;
    @XmlElement
    private String soapVersion;
    @XmlElement
    @XmlJavaTypeAdapter(value = XMLConfPropHolderAdapter.class)
    private String endpointAddresss;

    public QName getServiceName() {
        return serviceName;
    }

    public void setServiceName(QName serviceName) {
        this.serviceName = serviceName;
    }

    public QName getPortName() {
        return portName;
    }

    public void setPortName(QName portName) {
        this.portName = portName;
    }

    public String getJaxbContextPackage() {
        return jaxbContextPackage;
    }

    public void setJaxbContextPackage(String jaxbContextPackage) {
        this.jaxbContextPackage = jaxbContextPackage;
    }

    public String getSoapVersion() {
        return soapVersion;
    }

    public void setSoapVersion(String soapVersion) {
        this.soapVersion = soapVersion;
    }

    public String getEndpointAddresss() {
        return endpointAddresss;
    }

    public void setEndpointAddresss(String endpointAddresss) {
        this.endpointAddresss = endpointAddresss;
    }

    
    @Override
    public OperationDispatcherProvider getDispatcherProvider() {
        OperationDispatcherProvider d = new JaxWsDispatcherProvider(this);
        return d;
    }
    
}
