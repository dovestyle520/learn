package com.marquess.learn;




import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.marquess.learn.dao")
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class LearnApplication {
    private static Logger logger = LoggerFactory.getLogger(LearnApplication.class);
    
//    @Autowired
//    private TaskService taskService;
//    @Autowired
//    private ProcessEngine processEngine;
    
	public static void main(String[] args) {
	    logger.info("应用启动-------------------------------");
		SpringApplication.run(LearnApplication.class, args);
	}
}
