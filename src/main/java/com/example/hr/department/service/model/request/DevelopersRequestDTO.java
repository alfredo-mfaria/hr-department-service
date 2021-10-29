package com.example.hr.department.service.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DevelopersRequestDTO {

    @NotBlank(message = "Name field must not be blank")
    private String name;
    @NotBlank(message = "Team field must not be blank")
    private String team;
}
