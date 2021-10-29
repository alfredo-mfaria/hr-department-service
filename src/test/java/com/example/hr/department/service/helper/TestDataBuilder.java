package com.example.hr.department.service.helper;

import com.example.hr.department.service.model.request.DevelopersRequestDTO;
import com.example.hr.department.service.model.request.TeamRequestDTO;

public class TestDataBuilder {

    public static TeamRequestDTO getTeamRequestDTO(String name, String description) {
        return TeamRequestDTO.builder()
                .name(name)
                .description(description)
                .build();
    }

    public static DevelopersRequestDTO getDevelopersRequestDTO(String name, String team) {
        return DevelopersRequestDTO.builder()
                .name(name)
                .team(team)
                .build();
    }
}
