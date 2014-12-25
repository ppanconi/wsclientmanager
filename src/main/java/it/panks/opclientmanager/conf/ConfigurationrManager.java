    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.panks.opclientmanager.conf;

import it.panks.opclientmanager.conf.imp.XMLFileConfigurationProvider;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.ConfigurationException;

/**
 * @author paolo.panconi
 */
public class ConfigurationrManager {
    
    public static final String DEFAULT_XML_CONFIG_FILE_NAME = "opclient-config.xml";
    public static final String DEFAULT_GLOBAL_POPERTIES_FILE_NAME = "opclient-config.properties";
    
    /** Singleton instance **/
    private static ConfigurationrManager instance;

    public static ConfigurationrManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationrManager();
        }
        
        return instance;
    }
    
    /** instances members **/
    
    private Map<String, OperationConfiguration> confs = new ConcurrentHashMap<>();
    private Set<ConfigurationEventListener> listeners = new HashSet<>();

    public ConfigurationrManager() {
        
        try {
            File file = new File(getClass().getResource("/" + DEFAULT_XML_CONFIG_FILE_NAME).getFile());
            Properties props = new Properties();
            props.load(new FileInputStream(new File(getClass().getResource("/" + DEFAULT_GLOBAL_POPERTIES_FILE_NAME).getFile())));
            XMLFileConfigurationProvider xmlConfProvider = new XMLFileConfigurationProvider(file, props);
            
            synchConfiguration(xmlConfProvider);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConfigurationrManager.class.getName()).log(Level.WARNING, "Default config file not foud", ex);
        } catch (IOException ex) {
            Logger.getLogger(ConfigurationrManager.class.getName()).log(Level.SEVERE, "Error loading gobal properties file " + DEFAULT_GLOBAL_POPERTIES_FILE_NAME, ex);
            throw new RuntimeException("Error loading gobal properties file " + DEFAULT_GLOBAL_POPERTIES_FILE_NAME);
        }
        
    }
    
    synchronized public void synchConfiguration(ConfigurationProvider provider) {
        for(OperationConfiguration config: provider.provideConfigurations() ) {
            confs.put(config.getOperationId(), config);
        }
        
        for (ConfigurationEventListener listener : listeners) {
            listener.notifyConfigurationRefresh();
        }
    }
    
    synchronized public void registerListner(ConfigurationEventListener l) {
        listeners.add(l);
    }
    
    public OperationConfiguration provideConfiguration(String operationId) {
        OperationConfiguration conf = confs.get(operationId);
        return conf;
    }
}
