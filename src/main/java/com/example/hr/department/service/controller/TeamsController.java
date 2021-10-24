package com.example.hr.department.service.controller;

import com.example.hr.department.service.model.request.TeamResponseDTO;
import com.example.hr.department.service.model.response.TeamRequestDTO;
import com.example.hr.department.service.service.TeamsService;
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
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamsController {

    private final TeamsService teamsService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TeamResponseDTO> findAllTeams() {
        return teamsService.findAllTeams();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TeamResponseDTO findTeamById(@PathVariable("id") String id) {
        return teamsService.findTeamById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public TeamResponseDTO createTeam(@RequestBody TeamRequestDTO payload) {
        return teamsService.createTeam(payload);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public TeamResponseDTO updateTeam(@PathVariable("id") String id,
                                      @RequestBody TeamRequestDTO payload) {
        return teamsService.updateTeamById(id, payload);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteTeam(@PathVariable("id") String id) {

        teamsService.deleteTeamById(id);
    }
}
