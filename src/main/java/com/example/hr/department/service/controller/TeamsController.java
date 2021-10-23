package com.example.hr.department.service.controller;

import com.example.hr.department.service.model.request.TeamResponseDTO;
import com.example.hr.department.service.model.response.TeamRequestDTO;
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
@RequestMapping("/teams")
public class TeamsController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
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

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TeamResponseDTO findTeamById(@PathVariable("id") String id) {
        return TeamResponseDTO.builder()
                .id("1")
                .developers(List.of("Alfredo", "Renata"))
                .description("backend team")
                .name("Alpha")
                .build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public TeamResponseDTO createTeam(@RequestBody TeamRequestDTO payload) {

        return TeamResponseDTO.builder()
                .id("1")
                .developers(List.of("Alfredo", "Renata"))
                .description("backend team")
                .name("Alpha")
                .build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public TeamResponseDTO updateTeam(@PathVariable("id") String id,
                                      @RequestBody TeamRequestDTO payload) {

        return TeamResponseDTO.builder()
                .id("1")
                .developers(List.of("Alfredo", "Renata"))
                .description("backend team")
                .name("Alpha")
                .build();
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteCat(@PathVariable("id") String id) {

        System.out.println("Deleted object with id: " + id);
    }
}
