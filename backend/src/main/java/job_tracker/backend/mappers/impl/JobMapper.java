package job_tracker.backend.mappers.impl;

import job_tracker.backend.domain.dtos.JobDto;
import job_tracker.backend.domain.entities.JobEntity;
import job_tracker.backend.mappers.Mapper;
import org.springframework.stereotype.Component;


@Component
public class JobMapper implements Mapper<JobEntity, JobDto> {

    @Override
    public JobDto mapToDto(JobEntity entity) {
        return JobDto.builder().
                id(entity.getId()).
                company(entity.getCompany()).
                position(entity.getPosition()).
                jobStatus(entity.getJobStatus()).
                locationId(entity.getLocation().getId())
                .build();
    }

    @Override
    public JobEntity mapToEntity(JobDto dto) {
        return JobEntity.builder().
                id(dto.getId()).
                company(dto.getCompany()).
                position(dto.getPosition()).
                jobStatus(dto.getJobStatus()).build();
    }

}
