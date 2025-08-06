package job_tracker.backend.services;

import job_tracker.backend.domain.dtos.JobDto;

public interface JobService {

    JobDto save(JobDto jobDto);

    JobDto findOne(Long id);
}
