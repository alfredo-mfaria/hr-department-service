package com.example.hr.department.service.mapper;

import com.example.hr.department.service.domain.TeamsEntity;
import com.example.hr.department.service.model.request.TeamRequestDTO;
import com.example.hr.department.service.model.response.TeamResponseDTO;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class TeamsMapper {

    public TeamsEntity mapToTeamsEntity(String id, TeamRequestDTO teamRequestDTO) {
        return TeamsEntity.builder()
                .id(getId(id))
                .name(teamRequestDTO.getName())
                .description(teamRequestDTO.getDescription())
                .build();
    }

    public TeamResponseDTO mapToTeamsResponseDTO(TeamsEntity entity) {
        return TeamResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .developers(entity.getDevelopers())
                .build();
    }

    public List<TeamResponseDTO> mapToTeamsResponseDTOList(List<TeamsEntity> allEntities) {
        return Optional.ofNullable(allEntities)
                .orElse(Collections.emptyList())
                .stream()
                .map(this::mapToTeamsResponseDTO)
                .collect(Collectors.toList());
    }

    private String getId(String id) {
        return isNull(id) ? UUID.randomUUID().toString() : id;
    }
}
