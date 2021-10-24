package com.example.hr.department.service.service;

import com.example.hr.department.service.domain.TeamsEntity;
import com.example.hr.department.service.model.request.TeamResponseDTO;
import com.example.hr.department.service.model.response.TeamRequestDTO;
import com.example.hr.department.service.repository.RepositoryFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamsServiceImpl implements TeamsService {

    private final RepositoryFacade<TeamsEntity> postgresStrategy;

    @Override
    public TeamResponseDTO createTeam(TeamRequestDTO teamRequestDTO) {
        return TeamResponseDTO.builder()
                .id("1")
                .developers(List.of("Alfredo", "Renata"))
                .description("backend team")
                .name("Alpha")
                .build();
    }

    @Override
    public TeamResponseDTO updateTeamById(String id, TeamRequestDTO teamRequestDTO) {
        return TeamResponseDTO.builder()
                .id("1")
                .developers(List.of("Alfredo", "Renata"))
                .description("backend team")
                .name("Alpha")
                .build();
    }

    @Override
    public TeamResponseDTO findTeamById(String id) {
        return TeamResponseDTO.builder()
                .id("1")
                .developers(List.of("Alfredo", "Renata"))
                .description("backend team")
                .name("Alpha")
                .build();
    }

    @Override
    public List<TeamResponseDTO> findAllTeams() {
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
    public void deleteTeamById(String id) {
        System.out.println("Deleted team with id: " + id);
    }
}
