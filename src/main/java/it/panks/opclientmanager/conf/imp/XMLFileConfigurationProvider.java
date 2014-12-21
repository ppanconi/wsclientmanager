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

//    public static final String DEFAULT_XML_CONFIG_FILE_NAME = "opclient-config.xml";
//    public static final String DEFAULT_POPERTIES_FILE_NAME = "opclient-config.properties";
//    
//    public static final String SYSTEM_PROPERTIES_
//    
    
    private File file;
    private Properties globalProperties;

    public XMLFileConfigurationProvider(File file, Properties globalProperties) {
        this.file = file;
        this.globalProperties = globalProperties;
    }

    public XMLFileConfigurationProvider(File file) {
        this.file = file;
    }
    
    @Override
    public List<BaseOperationConfiguration> provideConfigurations() {
        
        
        try {
            JAXBContext jaxbc = JAXBContext.newInstance(XMLConfiguration.class);
            
            Unmarshaller um = jaxbc.createUnmarshaller();
            um.setAdapter(new XMLConfPropHolderAdapter(globalProperties));
            
            XMLConfiguration conf = (XMLConfiguration) um.unmarshal(file);
            
            return conf.getConfiguration();
            
        } catch (JAXBException ex) {
            Logger.getLogger(XMLFileConfigurationProvider.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Error parsing configuration file " + file.getAbsolutePath(), ex);
        }
        
        
        
    }
    
    
}
