package com.example.hr.department.service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "HR Department API", version = "1.0",
        description = "hr-department-service is REST based microservice that enables an HR department to manage developers and the teams they belong to."))
public class HrDepartmentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrDepartmentServiceApplication.class, args);
    }

}
