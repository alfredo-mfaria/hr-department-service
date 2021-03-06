package com.example.hr.department.service.integration;

import com.example.hr.department.service.handler.ExceptionDTO;
import com.example.hr.department.service.helper.TestDataBuilder;
import com.example.hr.department.service.model.request.DevelopersRequestDTO;
import com.example.hr.department.service.model.request.TeamRequestDTO;
import com.example.hr.department.service.model.response.DevelopersResponseDTO;
import com.example.hr.department.service.util.SerDesHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class HrDepartmentServiceDevelopersIT {

    @Autowired
    private MockMvc mockMvc;

    private TeamRequestDTO mockFeTeam;
    private TeamRequestDTO mockBeTeam;
    private DevelopersRequestDTO mockFeDeveloper;
    private DevelopersRequestDTO mockBeDeveloper;

    @BeforeEach
    void setUp() {
        mockFeTeam = TestDataBuilder.getTeamRequestDTO("FE", "Front end team");
        mockBeTeam = TestDataBuilder.getTeamRequestDTO("BE", "Back end team");
        mockFeDeveloper = TestDataBuilder.getDevelopersRequestDTO("Joe", "FE");
        mockBeDeveloper = TestDataBuilder.getDevelopersRequestDTO("Jane", "BE");
    }

    @Test
    void findAllDevelopersShouldReturnEmptyList() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/developers")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        DevelopersResponseDTO[] actual = SerDesHelper.deserialize(response, DevelopersResponseDTO[].class);
        Assertions.assertEquals(0, actual.length);
    }

    @Test
    void createDeveloperWithoutTeamShouldReturnNotFound() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/developers")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(SerDesHelper.serialize(mockBeDeveloper)))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ExceptionDTO actual = SerDesHelper.deserialize(response, ExceptionDTO.class);
        Assertions.assertEquals("Team " + mockBeDeveloper.getTeam() + " was not found", actual.getMessage());
    }

    @Test
    void createDeveloperAfterCreatingTeamShouldReturnDeveloperWithId() throws Exception {
        createTeam(mockBeTeam);
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/developers")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(SerDesHelper.serialize(mockBeDeveloper)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        DevelopersResponseDTO actual = SerDesHelper.deserialize(response, DevelopersResponseDTO.class);
        Assertions.assertNotNull(actual.getId());
        Assertions.assertEquals(mockBeTeam.getName(), actual.getTeam());
        Assertions.assertEquals(mockBeDeveloper.getName(), actual.getName());
    }

    private void createTeam(TeamRequestDTO team) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/teams")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(SerDesHelper.serialize(team)));
    }
}
