/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.panks.opclientmanager.conf;

import java.util.List;

/**
 *
 * @author paolo.panconi
 */
public interface ConfigurationProvider {
    
   <T extends OperationConfiguration> List<T> provideConfigurations();
    
}
