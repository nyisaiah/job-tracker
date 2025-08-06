package job_tracker.backend.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import job_tracker.backend.TestDataUtil;
import job_tracker.backend.domain.dtos.JobDto;
import job_tracker.backend.domain.entities.JobEntity;
import job_tracker.backend.mappers.Mapper;
import job_tracker.backend.services.JobService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class JobControllerIntegrationTests {
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final JobService jobService;
    private final Mapper<JobEntity, JobDto> jobMapper;

    @Autowired
    public JobControllerIntegrationTests(MockMvc mockMvc, ObjectMapper objectMapper, JobService jobService, Mapper<JobEntity, JobDto> jobMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.jobService = jobService;
        this.jobMapper = jobMapper;
    }

    @Test
    public void testThatCreateJobSuccessfullyReturns201() throws Exception {
        JobDto testJobDto = TestDataUtil.createJobDto();
        testJobDto.setId(null);

        String jobJson = objectMapper.writeValueAsString(testJobDto);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/jobs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jobJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateJobSuccessfullyReturnsSavedAuthor() throws Exception {
        JobDto testJobDto = TestDataUtil.createJobDto();
        testJobDto.setId(null);

        String jobJson = objectMapper.writeValueAsString(testJobDto);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/jobs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jobJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.company").value("Apple")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.position").value("Software Engineer")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.jobStatus").value("APPLIED")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.locationId").value(1L)
        );
    }

    @Test
    public void testThatGetJobReturnsHttpStatus200WhenJobExists() throws Exception {
        JobDto testJobDto = TestDataUtil.createJobDto();

        jobService.save(testJobDto);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/jobs/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatGetAuthorReturnsHttpStatus404WhenAuthorDNE() throws Exception {
        JobDto testJobDto = TestDataUtil.createJobDto();
        testJobDto.setId(null);
        jobService.save(testJobDto);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/jobs/69420")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
