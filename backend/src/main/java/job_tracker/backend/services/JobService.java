package job_tracker.backend.services;

import job_tracker.backend.domain.dtos.JobDto;

import java.util.List;

public interface JobService {

    JobDto save(JobDto jobDto);

    JobDto findOne(Long id);

    List<JobDto> findAll();
}
