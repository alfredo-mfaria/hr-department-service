package com.example.hr.department.service.service;

import com.example.hr.department.service.domain.DevelopersEntity;
import com.example.hr.department.service.exception.DataNotFoundException;
import com.example.hr.department.service.mapper.DevelopersMapper;
import com.example.hr.department.service.model.request.DevelopersRequestDTO;
import com.example.hr.department.service.model.response.DevelopersResponseDTO;
import com.example.hr.department.service.repository.jpa.DevelopersRepository;
import com.example.hr.department.service.repository.jpa.TeamsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DevelopersServiceImpl implements GenericService<DevelopersRequestDTO, DevelopersResponseDTO> {

    private final DevelopersRepository developersRepository;
    private final TeamsRepository teamsRepository;
    private final DevelopersMapper developersMapper;

    @Override
    @Transactional
    public DevelopersResponseDTO create(DevelopersRequestDTO payload) {
        return Optional.ofNullable(teamsRepository.findByName(payload.getTeam()))
                .map(teamsEntity -> developersMapper.mapToDevelopersEntity(null, payload, teamsEntity))
                .map(developersRepository::save)
                .map(developersMapper::mapToDevelopersResponseDTO)
                .orElseThrow(() -> new DataNotFoundException("Team " + payload.getTeam() + " was not found"));
    }

    @Override
    @Transactional
    public DevelopersResponseDTO updateById(String id, DevelopersRequestDTO payload) {
        return developersRepository.findById(id)
                .map(developerFound -> teamsRepository.findById(id)
                        .orElseThrow(() -> new DataNotFoundException("Developer " + payload.getName() + " was not found")))
                .map(teamEntity -> developersMapper.mapToDevelopersEntity(id, payload, teamEntity))
                .map(developersRepository::save)
                .map(developersMapper::mapToDevelopersResponseDTO)
                .orElseThrow(() -> new DataNotFoundException("Developer " + payload.getName() + " was not found"));
    }

    @Override
    public DevelopersResponseDTO findById(String id) {
        return developersRepository.findById(id)
                .map(developersMapper::mapToDevelopersResponseDTO)
                .orElseThrow(() -> new DataNotFoundException("Developer with id " + id + " not found"));
    }

    @Override
    public List<DevelopersResponseDTO> findAll() {
        List<DevelopersEntity> allEntities = developersRepository.findAll();
        return developersMapper.mapToDevelopersResponseDTOList(allEntities);
    }

    @Override
    public void deleteById(String id) {
        developersRepository.deleteById(id);
    }
}
