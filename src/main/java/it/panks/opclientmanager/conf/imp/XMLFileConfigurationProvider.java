/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.panks.opclientmanager.conf.imp;

import it.panks.opclientmanager.conf.BaseOperationConfiguration;
import it.panks.opclientmanager.conf.ConfigurationProvider;
import it.panks.opclientmanager.conf.OperationConfiguration;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author paolo.panconi
 */
public class XMLFileConfigurationProvider implements ConfigurationProvider {

    private InputStream stream;
    private Properties globalProperties;

    public XMLFileConfigurationProvider(File file, Properties globalProperties) throws FileNotFoundException {
        this.stream = new FileInputStream(file);
        this.globalProperties = globalProperties;
    }

    public XMLFileConfigurationProvider(InputStream stream, Properties globalProperties) {
        this.stream = stream;
        this.globalProperties = globalProperties;
    }

    public XMLFileConfigurationProvider(File file) throws FileNotFoundException {
        this.stream = new FileInputStream(file);
    }

    public XMLFileConfigurationProvider(InputStream stream) {
        this.stream = stream;
    }
    
    @Override
    public List<BaseOperationConfiguration> provideConfigurations() {
        try {
            JAXBContext jaxbc = JAXBContext.newInstance(XMLConfiguration.class);
            
            Unmarshaller um = jaxbc.createUnmarshaller();
            um.setAdapter(new XMLConfPropHolderAdapter(globalProperties));
            
            XMLConfiguration conf = (XMLConfiguration) um.unmarshal(stream);
            
            return conf.getConfiguration();
            
        } catch (JAXBException ex) {
            Logger.getLogger(XMLFileConfigurationProvider.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Error parsing configuration", ex);
        }
    }
    
    
}
