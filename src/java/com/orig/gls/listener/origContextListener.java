/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orig.gls.listener;

import java.io.File;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author Stanley Mungai
 */
public class origContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        System.setProperty("rootPath", context.getRealPath("/"));
        System.out.println("XXXXXXXXXXXXXXXXXX Log Path " + context.getRealPath("/"));
        String prefix = context.getRealPath("/");
        String file = "WEB-INF" + System.getProperty("file.separator") + "classes" + System.getProperty("file.separator") + "log4j.properties";
        if (file != null) {
            PropertyConfigurator.configure(prefix + System.getProperty("file.separator") + file);
            System.out.println("Log4J Logging started for application: " + prefix + System.getProperty("file.separator") + file);
        } else {
            System.out.println("Log4J Is not configured for application Application: " + prefix + System.getProperty("file.separator") + file);
        }
        String otherreports = context.getRealPath("/Other Reports");
        File or = new File(otherreports);
        if (!or.exists()) {
            or.mkdirs();
        }
        context.setAttribute("reportdir", or.getAbsolutePath());
        context.setAttribute("otherreps", or.getAbsolutePath());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}
