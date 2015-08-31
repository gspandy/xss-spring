package com.ryanperrizo.spring.sample;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan
@EnableAutoConfiguration
//@SpringBootApplication
@EnableTransactionManagement
public class SpringStartApplication {

	private static final Logger logger = LoggerFactory.getLogger(SpringStartApplication.class);
	
    public static void main(String[] args) throws Exception{
//        SpringApplication.run(SpringStartApplication.class, args);
    	try{
        ApplicationContext ctx = SpringApplication.run(SpringStartApplication.class, args);
        logger.info("Beans in application context: ");
        
        String beanNames[] = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        
        for (String beanName: beanNames)
        	logger.info(beanName);
        
    	}catch(Exception e){
        	logger.info(e.toString());
        }
    }
}
