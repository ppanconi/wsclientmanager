/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.panks.opclientmanager.conf.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.naming.ConfigurationException;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author paolo.panconi
 */
public class XMLConfPropHolderAdapter extends XmlAdapter<String, String>{
    
    private Properties properties;
    Pattern p = Pattern.compile("(\\$\\{.+?\\})");

    public XMLConfPropHolderAdapter(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String unmarshal(String v) throws Exception {
        PropertyStringReplacer replacer = new PropertyStringReplacer(v, properties);
        return replacer.replace();
    }

    @Override
    public String marshal(String v) throws Exception {
        return v;
    }
   
}
