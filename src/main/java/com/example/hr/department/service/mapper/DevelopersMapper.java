package com.example.hr.department.service.mapper;

import com.example.hr.department.service.domain.DevelopersEntity;
import com.example.hr.department.service.domain.TeamsEntity;
import com.example.hr.department.service.model.request.DevelopersRequestDTO;
import com.example.hr.department.service.model.response.DevelopersResponseDTO;
import com.example.hr.department.service.model.response.TeamResponseDTO;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class DevelopersMapper {

    public DevelopersEntity mapToDevelopersEntity(String id, DevelopersRequestDTO developersRequestDTO, TeamsEntity teamsEntity) {
        return DevelopersEntity.builder()
                .id(getId(id))
                .name(developersRequestDTO.getName())
                .team(teamsEntity)
                .build();
    }

    public DevelopersResponseDTO mapToDevelopersResponseDTO(DevelopersEntity entity) {
        return DevelopersResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .team(entity.getTeam().getName())
                .build();
    }

    public List<DevelopersResponseDTO> mapToDevelopersResponseDTOList(List<DevelopersEntity> allEntities) {
        return Optional.ofNullable(allEntities)
                .orElse(Collections.emptyList())
                .stream()
                .map(this::mapToDevelopersResponseDTO)
                .collect(Collectors.toList());
    }

    private String getId(String id) {
        return isNull(id) ? UUID.randomUUID().toString() : id;
    }
}
