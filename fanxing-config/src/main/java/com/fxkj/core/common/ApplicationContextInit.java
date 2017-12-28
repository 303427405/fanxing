package com.fxkj.core.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 在action,servlet之外的bean里获取applicationcontext的方法
 * @author AdministratorAug 19, 2014
 *
 */
public class ApplicationContextInit implements ServletContextListener {
    

    private static WebApplicationContext springContext;
    
    public ApplicationContextInit() {
        super();
    }
    
    public void contextInitialized(ServletContextEvent event) {
        springContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
    }
    

    public void contextDestroyed(ServletContextEvent event) {
    }
    
    public static ApplicationContext getApplicationContext() {
        return springContext;
    }

    
}