package com.endava.RampUp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.endava.config.ServiceConfiguration;
import com.endava.resource.CRUDResource;
import com.endava.springConfig.SpringConfig;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

public class RampUpApp extends Service<ServiceConfiguration> {
	
	ApplicationContext context;
	
    public static void main(String[] args) throws Exception {
        new RampUpApp().run(args);
    }

    @Override
    public void initialize(Bootstrap<ServiceConfiguration> bootstrap) {
        bootstrap.setName("RampUp");
    }

    @Override
    public void run(ServiceConfiguration conf, Environment env) throws Exception {
    	context = new AnnotationConfigApplicationContext(SpringConfig.class);
    	CRUDResource res = (CRUDResource) context.getBean("helloResource");
    	env.addResource(res);
    }
}