package com.example.hr.department.service.handler;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionDTO {
    private String message;
}
