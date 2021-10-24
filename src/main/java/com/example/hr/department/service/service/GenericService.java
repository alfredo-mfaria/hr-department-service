package com.example.hr.department.service.service;

import com.example.hr.department.service.model.request.TeamRequestDTO;
import com.example.hr.department.service.model.response.DevelopersResponseDTO;
import com.example.hr.department.service.model.response.TeamResponseDTO;

import java.util.List;

//P stand for request Payload, R stands for Response
public interface GenericService<P, R> {

    R create(P payload);

    R updateById(String id, P payload);

    R findById(String id);

    List<R> findAll();

    void deleteById(String id);
}
