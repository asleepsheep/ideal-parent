package com.ideal.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@ComponentScan(basePackages = "com.ideal.cloud")
@MapperScan(basePackages = "com.ideal.cloud.intensive.mapper")
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages="com.ideal.cloud.api")
public class IntensiveApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(IntensiveApplication.class, args);
	}
}
