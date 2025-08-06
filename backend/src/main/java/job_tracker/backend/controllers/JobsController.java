package job_tracker.backend.controllers;

import job_tracker.backend.domain.dtos.JobDto;
import job_tracker.backend.services.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobsController {
    private final JobService jobService;

    public JobsController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping(path = "/jobs")
    public ResponseEntity<JobDto> createJob(@RequestBody JobDto jobDto) {
        try {
            JobDto savedJobDto = jobService.save(jobDto);
            return new ResponseEntity<>(savedJobDto, HttpStatus.CREATED);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
