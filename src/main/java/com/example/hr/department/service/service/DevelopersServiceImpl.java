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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DevelopersServiceImpl implements GenericService<DevelopersRequestDTO, DevelopersResponseDTO> {

    private final DevelopersRepository developersRepository;
    private final TeamsRepository teamsRepository;
    private final DevelopersMapper developersMapper;

    @Override
    public DevelopersResponseDTO create(DevelopersRequestDTO payload) {

        //todo team does not exist throw exception
        TeamsEntity teamsEntity = teamsRepository.findByName(payload.getTeam());
        DevelopersEntity developersEntity = developersMapper.mapToDevelopersEntity(null, payload, teamsEntity);
        DevelopersEntity dbResponse = developersRepository.save(developersEntity);
        return developersMapper.mapToDevelopersResponseDTO(dbResponse);
    }

    @Override
    public DevelopersResponseDTO updateById(String id, DevelopersRequestDTO payload) {
        TeamsEntity teamsEntity = teamsRepository.findByName(payload.getTeam());
        DevelopersEntity developersEntity = developersMapper.mapToDevelopersEntity(id, payload, teamsEntity);
        DevelopersEntity dbResponse = developersRepository.save(developersEntity);
        return developersMapper.mapToDevelopersResponseDTO(dbResponse);
    }

    @Override
    public DevelopersResponseDTO findById(String id) {
        Optional<DevelopersEntity> dbResponse = developersRepository.findById(id);
        return dbResponse.map(developersMapper::mapToDevelopersResponseDTO)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    @Override
    public List<DevelopersResponseDTO> findAll() {
        List<DevelopersEntity> allEntities = developersRepository.findAll();
        return developersMapper.mapToDevelopersResponseDTOList(allEntities);    }

    @Override
    public void deleteById(String id) {
        developersRepository.deleteById(id);
        System.out.println("Deleted developer with id: " + id);
    }
}
