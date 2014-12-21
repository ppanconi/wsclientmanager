/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.panks.opclientmanager.conf;

import it.panks.opclientmanager.core.OperationDispatcherProvider;

/**
 *
 * @author paolo.panconi
 */
public interface OperationConfiguration {
    
    OperationDispatcherProvider getDispatcherProvider();
    
    String getOperationId();
}
