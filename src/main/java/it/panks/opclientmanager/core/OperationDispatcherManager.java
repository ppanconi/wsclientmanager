/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.panks.opclientmanager.core;

import it.panks.opclientmanager.cache.DispatcherCacheForThread;
import it.panks.opclientmanager.conf.ConfigurationEventListener;
import it.panks.opclientmanager.conf.ConfigurationrManager;
import it.panks.opclientmanager.conf.OperationConfiguration;
import javax.cache.Cache;

/**
 *
 * @author paolo.panconi
 */
public class OperationDispatcherManager implements ConfigurationEventListener {
    
    /** Singleton instance **/
    private static OperationDispatcherManager instance;

    public static OperationDispatcherManager getInstance() {
        if (instance == null) {
            instance = new OperationDispatcherManager();
        }
        
        return instance;
    }
    
    /** instances members **/
    private Cache<String, OperationDispatcher> dispatcherCache;

    private OperationDispatcherManager() {
        this.dispatcherCache = new DispatcherCacheForThread();
        ConfigurationrManager.getInstance().registerListner(this);
    }

    public Cache<String, OperationDispatcher> getDispatcherCache() {
        return dispatcherCache;
    }
    
    public OperationDispatcher provideDispatcher(String operationId) {
        
        OperationDispatcher d = null;
        
        if (getDispatcherCache() != null) {
            d = getDispatcherCache().get(operationId);
        }
        
        if (d == null) {
            
            OperationConfiguration conf = ConfigurationrManager.getInstance().provideConfiguration(operationId);
            
            if (conf == null) {
                throw new IllegalArgumentException("Operation id " + operationId + " not actually configured");
            }
            
            d = conf.getDispatcherProvider().provideDispatcher();
            
            if (getDispatcherCache() != null) {
                getDispatcherCache().put(operationId, d);
            }
        }
        
        return d;
    }    

//    @Override
//    public void notifyOperationConfigurationChange(OperationConfigurationEvent changeevent) {
//        
//        if (getDispatcherCache() != null && getDispatcherCache().containsKey(changeevent.operationId)) {
//            
//            getDispatcherCache().remove(changeevent.operationId);
//            
//        }
//    }

    @Override
    public void notifyConfigurationRefresh() {
        if (getDispatcherCache() != null ) {
            getDispatcherCache().clear();
        }

    }
    
}
