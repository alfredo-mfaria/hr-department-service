package com.example.hr.department.service.service;

import com.example.hr.department.service.model.request.DevelopersRequestDTO;
import com.example.hr.department.service.model.response.DevelopersResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DevelopersServiceImpl implements GenericService<DevelopersRequestDTO, DevelopersResponseDTO> {

    @Override
    public DevelopersResponseDTO create(DevelopersRequestDTO payload) {
        return null;
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
