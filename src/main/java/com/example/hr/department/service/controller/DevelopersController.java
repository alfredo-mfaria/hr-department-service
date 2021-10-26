package com.example.hr.department.service.controller;

import com.example.hr.department.service.model.request.DevelopersRequestDTO;
import com.example.hr.department.service.model.response.DevelopersResponseDTO;
import com.example.hr.department.service.service.GenericService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/developers")
@RequiredArgsConstructor
public class DevelopersController {

    private final GenericService<DevelopersRequestDTO, DevelopersResponseDTO> developersService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DevelopersResponseDTO> findAllDevelopers() {
        return developersService.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DevelopersResponseDTO findTeamById(@PathVariable("id") String id) {
        return developersService.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public DevelopersResponseDTO createTeam(@RequestBody DevelopersRequestDTO payload) {
        return developersService.create(payload);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DevelopersResponseDTO updateTeam(@PathVariable("id") String id,
                                            @RequestBody DevelopersRequestDTO payload) {
        return developersService.updateById(id, payload);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTeam(@PathVariable("id") String id) {

        developersService.deleteById(id);
    }
}
