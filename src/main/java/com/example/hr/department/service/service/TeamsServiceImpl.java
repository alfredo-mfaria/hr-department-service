package com.example.hr.department.service.service;

import com.example.hr.department.service.domain.TeamsEntity;
import com.example.hr.department.service.mapper.TeamsMapper;
import com.example.hr.department.service.model.response.TeamResponseDTO;
import com.example.hr.department.service.model.request.TeamRequestDTO;
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
    public TeamResponseDTO create(TeamRequestDTO teamRequestDTO) {
        TeamsEntity entity = teamsMapper.mapToTeamsEntity(null, teamRequestDTO);
        TeamsEntity dbResponse = teamsRepository.save(entity);
        return teamsMapper.mapToTeamsResponseDTO(dbResponse);
    }

    @Override
    public TeamResponseDTO updateById(String id, TeamRequestDTO teamRequestDTO) {
        TeamsEntity entity = teamsMapper.mapToTeamsEntity(id, teamRequestDTO);
        TeamsEntity dbResponse = teamsRepository.save(entity);
        return teamsMapper.mapToTeamsResponseDTO(dbResponse);
    }

    @Override
    public TeamResponseDTO findById(String id) {
        Optional<TeamsEntity> dbResponse = teamsRepository.findById(id);
        return dbResponse.map(teamsMapper::mapToTeamsResponseDTO)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    @Override
    public List<TeamResponseDTO> findAll() {
        List<TeamsEntity> allEntities = teamsRepository.findAll();
        return teamsMapper.mapToTeamsResponseDTOList(allEntities);
    }

    @Override
    public void deleteById(String id) {
        teamsRepository.deleteById(id);
        System.out.println("Deleted team with id: " + id);
    }
}
