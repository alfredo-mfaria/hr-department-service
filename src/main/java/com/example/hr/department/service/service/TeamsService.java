package com.example.hr.department.service.service;

import com.example.hr.department.service.model.request.TeamResponseDTO;
import com.example.hr.department.service.model.response.TeamRequestDTO;

import java.util.List;

public interface TeamsService {

    TeamResponseDTO createTeam(TeamRequestDTO teamRequestDTO);

    TeamResponseDTO updateTeamById(String id, TeamRequestDTO teamRequestDTO);

    TeamResponseDTO findTeamById(String id);

    List<TeamResponseDTO> findAllTeams();

    void deleteTeamById(String id);
}
