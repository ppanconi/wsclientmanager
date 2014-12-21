/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.panks.opclientmanager.conf;

/**
 *
 * @author paolo.panconi
 */
public interface ConfigurationEventListener {
    
    static class OperationConfigurationEvent {
        public String operationId;
    }
    
//    void notifyOperationConfigurationChange(OperationConfigurationEvent changeevent);
 
    /**
     * Refresh 
     */
    void notifyConfigurationRefresh();
}
