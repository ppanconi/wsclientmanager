/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.panks.opclientmanager.conf.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.naming.ConfigurationException;

/**
 *
 * @author paolo.panconi
 */
public class PropertyStringReplacer {
    
    private final String string;
    private final Properties properties;
    
    static final Pattern p = Pattern.compile("(\\$\\{.+?\\})");

    public PropertyStringReplacer(String string, Properties properties) {
        this.string = string;
        this.properties = properties;
    }
    
    public String replace() throws ConfigurationException {
        
        List<String[]> l = new ArrayList<>();
        Matcher m = p.matcher(string);
        
        while (m.find()) {
            String holder = m.group();
            try {
            
                String prop = m.group().substring(2, m.group().length() - 1);
                String value = properties.getProperty(prop).trim();
                
                l.add(new String[] {holder, value});
            
            } catch (Exception ex) {
                throw new ConfigurationException("Error resolving configuration place holder " + holder );
            }
        }
        
        String v = string;
        
        if (l.size() > 0) {
            for (String[] pair : l) {
                v = v.replaceAll(Pattern.quote(pair[0]), pair[1]);
            }
        }
        
        return v;
    }
    
}
