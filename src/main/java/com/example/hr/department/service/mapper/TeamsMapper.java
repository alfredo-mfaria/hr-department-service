package com.example.hr.department.service.mapper;

import com.example.hr.department.service.domain.TeamsEntity;
import com.example.hr.department.service.model.response.TeamResponseDTO;
import com.example.hr.department.service.model.request.TeamRequestDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TeamsMapper {

    public TeamsEntity mapToTeamsEntity(TeamRequestDTO teamRequestDTO) {
        return TeamsEntity.builder()
                .id(UUID.randomUUID().toString())
                .name(teamRequestDTO.getName())
                .description(teamRequestDTO.getDescription())
                .build();
    }

    public TeamResponseDTO mapToTeamsResponseDTO(TeamsEntity entity) {
        return TeamResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }
}
