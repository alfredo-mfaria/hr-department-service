package com.example.hr.department.service.service;

import com.example.hr.department.service.domain.TeamsEntity;
import com.example.hr.department.service.mapper.TeamsMapper;
import com.example.hr.department.service.model.response.TeamResponseDTO;
import com.example.hr.department.service.model.request.TeamRequestDTO;
import com.example.hr.department.service.repository.jpa.TeamsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamsServiceImpl implements GenericService<TeamRequestDTO, TeamResponseDTO> {

    private final TeamsRepository repository;
    private final TeamsMapper teamsMapper;

    @Override
    public TeamResponseDTO create(TeamRequestDTO teamRequestDTO) {
        TeamsEntity entity = teamsMapper.mapToTeamsEntity(teamRequestDTO);
        TeamsEntity dbResponse = repository.save(entity);
        return teamsMapper.mapToTeamsResponseDTO(dbResponse);
    }

    @Override
    public TeamResponseDTO updateById(String id, TeamRequestDTO teamRequestDTO) {
        return TeamResponseDTO.builder()
                .id("1")
                .developers(List.of("Alfredo", "Renata"))
                .description("backend team")
                .name("Alpha")
                .build();
    }

    @Override
    public TeamResponseDTO findById(String id) {
        return TeamResponseDTO.builder()
                .id("1")
                .developers(List.of("Alfredo", "Renata"))
                .description("backend team")
                .name("Alpha")
                .build();
    }

    @Override
    public List<TeamResponseDTO> findAll() {
        TeamResponseDTO alpha = TeamResponseDTO.builder()
                .id("1")
                .developers(List.of("Alfredo", "Renata"))
                .description("backend team")
                .name("Alpha")
                .build();
        TeamResponseDTO beta = TeamResponseDTO.builder()
                .id("2")
                .developers(List.of("Renata", "Alfredo"))
                .description("frontend team")
                .name("Beta")
                .build();
        return List.of(alpha, beta);
    }

    @Override
    public void deleteById(String id) {
        System.out.println("Deleted team with id: " + id);
    }
}
