package com.example.hr.department.service.mapper;

import com.example.hr.department.service.domain.DevelopersEntity;
import com.example.hr.department.service.domain.TeamsEntity;
import com.example.hr.department.service.model.request.DevelopersRequestDTO;
import com.example.hr.department.service.model.response.DevelopersResponseDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DevelopersMapper {

    public DevelopersEntity mapToDevelopersEntity(DevelopersRequestDTO developersRequestDTO, TeamsEntity teamsEntity) {
        return DevelopersEntity.builder()
                .id(UUID.randomUUID().toString())
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
}
