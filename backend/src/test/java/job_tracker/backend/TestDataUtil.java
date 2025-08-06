package job_tracker.backend;

import job_tracker.backend.domain.JobStatus;
import job_tracker.backend.domain.dtos.JobDto;

public class TestDataUtil {
    public static JobDto createJobDtoA() {
        return JobDto.builder().
                company("Apple").
                position("Software Engineer").
                jobStatus(JobStatus.APPLIED).
                locationId(1L).
                build();
    }

    public static JobDto createJobDtoB() {
        return JobDto.builder().
                company("Microsoft").
                position("Software Engineer").
                jobStatus(JobStatus.OFFER).
                locationId(1L).
                build();
    }

    public static JobDto createJobDtoC() {
        return JobDto.builder().
                company("Uber").
                position("Software Engineer").
                jobStatus(JobStatus.REJECTED).
                locationId(1L).
                build();
    }
}
