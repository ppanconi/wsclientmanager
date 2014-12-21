/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.panks.opclientmanager.core;

/**
 *
 * @author paolo.panconi
 */
public interface OperationDispatcherProvider {
    
    OperationDispatcher provideDispatcher();
    
}
