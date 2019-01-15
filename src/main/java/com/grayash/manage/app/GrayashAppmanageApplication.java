package com.grayash.manage.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.grayash.auditactivity.config.EnableAuditActivity;

@SpringBootApplication
@EnableAuditActivity(serviceName="MANAGE-APP")
public class GrayashAppmanageApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrayashAppmanageApplication.class, args);
	}
}
