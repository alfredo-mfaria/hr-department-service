package com.example.hr.department.service.integration;

import com.example.hr.department.service.handler.ExceptionDTO;
import com.example.hr.department.service.helper.TestDataBuilder;
import com.example.hr.department.service.model.request.TeamRequestDTO;
import com.example.hr.department.service.model.response.TeamResponseDTO;
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
class HrDepartmentServiceTeamsIT {

    @Autowired
    private MockMvc mockMvc;

    private TeamRequestDTO mockFeTeam;
    private TeamRequestDTO mockBeTeam;

    @BeforeEach
    void setUp() {
        mockFeTeam = TestDataBuilder.getTeamRequestDTO("FE", "Front end team");
        mockBeTeam = TestDataBuilder.getTeamRequestDTO("BE", "Back end team");
    }

    @Test
    void findAllTeamsShouldReturnEmptyList() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/teams")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        TeamResponseDTO[] actual = SerDesHelper.deserialize(response, TeamResponseDTO[].class);
        Assertions.assertEquals(0, actual.length);
    }

    @Test
    void createTeamsShouldReturnTeamWithId() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/teams")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(SerDesHelper.serialize(mockFeTeam)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        TeamResponseDTO actual = SerDesHelper.deserialize(response, TeamResponseDTO.class);
        Assertions.assertEquals(mockFeTeam.getName(), actual.getName());
        Assertions.assertEquals(mockFeTeam.getDescription(), actual.getDescription());
        Assertions.assertNotNull(actual.getId());
    }

    @Test
    void createSameTeamTwiceShouldReturnConflict() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/teams")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(SerDesHelper.serialize(mockFeTeam)))
                .andDo(print())
                .andExpect(status().isCreated());

        String response = mockMvc.perform(MockMvcRequestBuilders.post("/teams")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(SerDesHelper.serialize(mockFeTeam)))
                .andDo(print())
                .andExpect(status().isConflict())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ExceptionDTO actual = SerDesHelper.deserialize(response, ExceptionDTO.class);
        Assertions.assertEquals("Team FE already exists", actual.getMessage());
    }

    @Test
    void createATeamDeleteTheTeamAndRecreateShouldReturnStatusOK() throws Exception {
        String created = mockMvc.perform(MockMvcRequestBuilders.post("/teams")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(SerDesHelper.serialize(mockFeTeam)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String teamId = SerDesHelper.deserialize(created, TeamResponseDTO.class).getId();

        mockMvc.perform(MockMvcRequestBuilders.delete("/teams/" + teamId)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isNoContent());

        String response = mockMvc.perform(MockMvcRequestBuilders.post("/teams")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(SerDesHelper.serialize(mockFeTeam)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        TeamResponseDTO actual = SerDesHelper.deserialize(response, TeamResponseDTO.class);
        Assertions.assertEquals(mockFeTeam.getName(), actual.getName());
        Assertions.assertEquals(mockFeTeam.getDescription(), actual.getDescription());
        Assertions.assertNotNull(actual.getId());
    }

    @Test
    void createTwoTeamsShouldReturnStatusOkWithSize2() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/teams")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(SerDesHelper.serialize(mockFeTeam)))
                .andDo(print())
                .andExpect(status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.post("/teams")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(SerDesHelper.serialize(mockBeTeam)))
                .andDo(print())
                .andExpect(status().isCreated());

        String response = mockMvc.perform(MockMvcRequestBuilders.get("/teams")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(SerDesHelper.serialize(mockBeTeam)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        TeamResponseDTO[] actual = SerDesHelper.deserialize(response, TeamResponseDTO[].class);
        Assertions.assertEquals(2, actual.length);
        Assertions.assertEquals(mockFeTeam.getName(), actual[0].getName());
        Assertions.assertEquals(mockFeTeam.getDescription(), actual[0].getDescription());
        Assertions.assertNotNull(actual[0].getId());
        Assertions.assertEquals(mockBeTeam.getName(), actual[1].getName());
        Assertions.assertEquals(mockBeTeam.getDescription(), actual[1].getDescription());
        Assertions.assertNotNull(actual[1].getId());
    }

    @Test
    void createTeamAndUpdateByIdShouldReturnUpdatedTeam() throws Exception {
        String created = mockMvc.perform(MockMvcRequestBuilders.post("/teams")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(SerDesHelper.serialize(mockFeTeam)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String teamId = SerDesHelper.deserialize(created, TeamResponseDTO.class).getId();

        String response = mockMvc.perform(MockMvcRequestBuilders.put("/teams/" + teamId)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(SerDesHelper.serialize(mockBeTeam)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        TeamResponseDTO actual = SerDesHelper.deserialize(response, TeamResponseDTO.class);
        Assertions.assertEquals(mockBeTeam.getName(), actual.getName());
        Assertions.assertEquals(mockBeTeam.getDescription(), actual.getDescription());
        Assertions.assertNotNull(actual.getId());
    }

    @Test
    void mandatoryTeamNameFieldConstraintValidationShouldReturnBadRequest() throws Exception {

        TeamRequestDTO mockEmptyTeamName = TeamRequestDTO.builder()
                .name(" ")
                .build();

        String response = mockMvc.perform(MockMvcRequestBuilders.post("/teams")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(SerDesHelper.serialize(mockEmptyTeamName)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ExceptionDTO actual = SerDesHelper.deserialize(response, ExceptionDTO.class);
        Assertions.assertEquals("Name field must not be blank", actual.getMessage());
    }
}
