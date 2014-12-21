/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.panks.opclientmanager.conf.imp;

import it.panks.opclientmanager.conf.ConfigurationProvider;
import it.panks.opclientmanager.conf.OperationConfiguration;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author paolo.panconi
 */
public class ApiConfigurationProvider implements ConfigurationProvider {
    
    private List<OperationConfiguration> configurations;

    public List<OperationConfiguration> getConfigurations() {
        if (configurations == null) configurations = new ArrayList<>();
        return configurations;
    }
    
    @Override
    public List<OperationConfiguration> provideConfigurations() {
        return configurations;
    }
    
}
