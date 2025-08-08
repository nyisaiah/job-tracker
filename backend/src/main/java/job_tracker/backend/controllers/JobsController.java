package job_tracker.backend.controllers;

import job_tracker.backend.domain.dtos.JobDto;
import job_tracker.backend.services.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(path = "/jobs/{id}")
    public ResponseEntity<JobDto> findOne (
            @PathVariable(name = "id") Long id
    ) {
        try {
            JobDto result = jobService.findOne(id);
            return new ResponseEntity<>(result, HttpStatus.OK);

        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/jobs")
    public List<JobDto> listAuthors() {
        return jobService.findAll();
    }

    @PutMapping(path = "/jobs/{id}")
    public  ResponseEntity<JobDto> updateJob(
            @RequestBody JobDto jobDto,
            @PathVariable(name = "id") Long id
    ) {
        if (!jobService.exists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        JobDto savedJob = jobService.save(jobDto);
        return new ResponseEntity<>(savedJob,HttpStatus.OK);
    }

    @PatchMapping(path = "/jobs/{id}")
    public ResponseEntity<JobDto> partialUpdate(
            @RequestBody JobDto jobDto,
            @PathVariable(name = "id") Long id
    ) {
        if (!jobService.exists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        JobDto updatedJob = jobService.partialUpdate(id, jobDto);
        return new ResponseEntity<>(updatedJob,HttpStatus.OK);
    }
}
