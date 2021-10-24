package com.example.hr.department.service.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamRequestDTO {

    private String name;
    private String description;
    //TODO change to list of Developer
    //private List<String> developers;
}