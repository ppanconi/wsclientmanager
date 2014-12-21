/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.panks.opclientmanager.conf;

import it.panks.opclientmanager.imp.jaxws.JaxWsOperationConfiguration;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author paolo.panconi
 */
@XmlType(namespace = "http://opclientmanager.org", name = "OperationConfiguration")
@XmlSeeAlso(JaxWsOperationConfiguration.class)
public abstract class BaseOperationConfiguration implements OperationConfiguration {
    
    private String operationId;

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }
}
