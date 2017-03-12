package com.ag.restboot;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Amol N. Gholap
 */
@EnableAutoConfiguration
@ComponentScan
@EnableScheduling
@ImportResource(
		  {
		         "classpath*:/ApplicationContext.xml"
		  })
public class Application
{
    private static final Logger log = LoggerFactory.getLogger(Application.class);


    public static void main(String[] args)
    {
    	SpringApplication.run(Application.class);
    }
}