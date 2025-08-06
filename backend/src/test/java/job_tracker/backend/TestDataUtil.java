package job_tracker.backend;

import job_tracker.backend.domain.JobStatus;
import job_tracker.backend.domain.dtos.JobDto;

public class TestDataUtil {
    public static JobDto createJobDto() {
        return JobDto.builder().
                company("Apple").
                position("Software Engineer").
                jobStatus(JobStatus.APPLIED).
                locationId(1L).
                build();
    }
}
