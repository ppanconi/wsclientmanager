    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.panks.opclientmanager.conf;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author paolo.panconi
 */
public class ConfigurationrManager {

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
