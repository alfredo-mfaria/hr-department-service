package com.example.hr.department.service.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamRequestDTO {

    private String id;
    private String name;
    private String description;
    //TODO change to list of Developer
    private List<String> developers;
}
