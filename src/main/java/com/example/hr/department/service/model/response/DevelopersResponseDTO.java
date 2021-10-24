package com.example.hr.department.service.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DevelopersResponseDTO {

    private String id;
    private String name;
    private String team;
}
