/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orig.gls.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Stanley Mungai
 */
public class origProperties {

    Properties prop;

    public origProperties() {
        this.prop = new Properties();
    }

    public Properties getProperties() {
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            InputStream url = classLoader.getResourceAsStream("com/orig/gls/properties/origproperties.properties");
            this.prop.load(url);
        } catch (IOException asd) {
            System.out.println(asd.getMessage());
        }
        return this.prop;
    }

    public boolean simlog() {
        return getProperties().getProperty("logging.enabled").equalsIgnoreCase("true");
    }
}
