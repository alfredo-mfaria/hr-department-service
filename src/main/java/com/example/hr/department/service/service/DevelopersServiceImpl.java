package com.example.hr.department.service.service;

import com.example.hr.department.service.domain.DevelopersEntity;
import com.example.hr.department.service.domain.TeamsEntity;
import com.example.hr.department.service.mapper.DevelopersMapper;
import com.example.hr.department.service.model.request.DevelopersRequestDTO;
import com.example.hr.department.service.model.response.DevelopersResponseDTO;
import com.example.hr.department.service.repository.jpa.DevelopersRepository;
import com.example.hr.department.service.repository.jpa.TeamsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DevelopersServiceImpl implements GenericService<DevelopersRequestDTO, DevelopersResponseDTO> {

    private final DevelopersRepository developersRepository;
    private final TeamsRepository teamsRepository;
    private final DevelopersMapper mapper;

    @Override
    public DevelopersResponseDTO create(DevelopersRequestDTO payload) {

        TeamsEntity teamsEntity = teamsRepository.findByName(payload.getTeam());
        DevelopersEntity developersEntity = mapper.mapToDevelopersEntity(payload, teamsEntity);
        DevelopersEntity dbResponse = developersRepository.save(developersEntity);
        return mapper.mapToDevelopersResponseDTO(dbResponse);
    }

    @Override
    public DevelopersResponseDTO updateById(String id, DevelopersRequestDTO payload) {
        return null;
    }

    @Override
    public DevelopersResponseDTO findById(String id) {
        return null;
    }

    @Override
    public List<DevelopersResponseDTO> findAll() {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }
}
