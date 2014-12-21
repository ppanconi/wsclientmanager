/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.panks.opclientmanager.conf.imp;

import it.panks.opclientmanager.conf.BaseOperationConfiguration;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author paolo.panconi
 */
@XmlType(namespace = "http://opclientmanager.org", name = "OperationConfigurations")
@XmlRootElement(namespace = "http://opclientmanager.org", name = "configurations")
public class XMLConfiguration {
    
    @XmlElement
    private List<BaseOperationConfiguration> configuration;

    public List<BaseOperationConfiguration> getConfiguration() {
        if (configuration ==  null) configuration = new ArrayList<>();
        
        return configuration;
    }
    
    
}
