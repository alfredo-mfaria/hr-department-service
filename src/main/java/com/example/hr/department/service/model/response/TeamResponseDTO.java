package com.example.hr.department.service.model.response;

import com.example.hr.department.service.domain.DevelopersEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamResponseDTO {

    private String id;
    private String name;
    private String description;
    private List<DevelopersEntity> developers = new ArrayList<>();
}
