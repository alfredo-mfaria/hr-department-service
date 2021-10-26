package com.example.hr.department.service.helper;

import com.example.hr.department.service.model.request.TeamRequestDTO;

public class TestDataBuilder {

    public static TeamRequestDTO getTeamRequestDTO(String name, String description) {
        return TeamRequestDTO.builder()
                .name(name)
                .description(description)
                .build();
    }
}
