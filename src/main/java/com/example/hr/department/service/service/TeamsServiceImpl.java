package com.example.hr.department.service.service;

import com.example.hr.department.service.domain.TeamsEntity;
import com.example.hr.department.service.exception.DataConflictException;
import com.example.hr.department.service.exception.DataNotFoundException;
import com.example.hr.department.service.mapper.TeamsMapper;
import com.example.hr.department.service.model.request.TeamRequestDTO;
import com.example.hr.department.service.model.response.TeamResponseDTO;
import com.example.hr.department.service.repository.jpa.TeamsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamsServiceImpl implements GenericService<TeamRequestDTO, TeamResponseDTO> {

    private final TeamsRepository teamsRepository;
    private final TeamsMapper teamsMapper;

    @Override
    public TeamResponseDTO create(TeamRequestDTO payload) {
        return Optional.ofNullable(teamsRepository.findByName(payload.getName()))
                .map(newTeam -> teamsMapper.mapToTeamsEntity(null, payload))
                .map(teamsRepository::save)
                .map(teamsMapper::mapToTeamsResponseDTO)
                .orElseThrow(() -> new DataConflictException("Team " + payload.getName() + " already exists"));
    }

    @Override
    public TeamResponseDTO updateById(String id, TeamRequestDTO payload) {
        return teamsRepository.findById(id)
                .map(ignored -> teamsMapper.mapToTeamsEntity(id, payload))
                .map(teamsRepository::save)
                .map(teamsMapper::mapToTeamsResponseDTO)
                .orElseThrow(() -> new DataNotFoundException("Team with id " + id + " not found"));
    }

    @Override
    public TeamResponseDTO findById(String id) {
        return teamsRepository.findById(id)
                .map(teamsMapper::mapToTeamsResponseDTO)
                .orElseThrow(() -> new DataNotFoundException("Team with id " + id + " not found"));
    }

    @Override
    public List<TeamResponseDTO> findAll() {
        List<TeamsEntity> allEntities = teamsRepository.findAll();
        return teamsMapper.mapToTeamsResponseDTOList(allEntities);
    }

    @Override
    public void deleteById(String id) {
        teamsRepository.deleteById(id);
    }
}
