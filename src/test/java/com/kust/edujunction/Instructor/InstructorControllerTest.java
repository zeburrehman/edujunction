package com.kust.edujunction.Instructor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.kust.edujunction.common.BaseTest;
import com.kust.edujunction.dtos.InstructorDTO;
import com.kust.edujunction.repositories.InstructorRepository;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class InstructorControllerTest extends BaseTest {
    final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

    @Inject
    MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private InstructorRepository instructorRepository;

    @Test
    void contextLoads() {
        assertTrue(postgres.isCreated());
        assertTrue(postgres.isRunning());
    }

    @Test
    void shouldSaveInstructorWhenPostEndpointCalled() throws Exception {
        InstructorDTO instructor = new InstructorDTO();
        instructor.setName("Zeb ur Rehman");
        instructor.setEmail("zeburrehman@gmail.com");

        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(instructor);

        var resultContent = this.mockMvc.perform(post("/instructors/")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andDo(print())
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
    }

    @Test
    void shouldReturnListOfInstructors() throws Exception {
        this.mockMvc.perform(get("/instructors/")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void shouldReturnInstructorById() throws Exception {
        var instructor = this.instructorRepository.findByName("Zeb ur Rehman").stream().findFirst();
        var instructorId = instructor.get().getId();
        this.mockMvc.perform(get("/instructors/" + instructorId)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void shouldReturnNotFoundWhenGetByNonExistentId() throws Exception {
        this.mockMvc.perform(get("/instructors/" + UUID.randomUUID())).andDo(print()).andExpect(status().isNotFound());
    }
}
