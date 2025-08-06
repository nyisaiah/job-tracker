package job_tracker.backend.services.impl;

import job_tracker.backend.domain.dtos.JobDto;
import job_tracker.backend.domain.entities.CityEntity;
import job_tracker.backend.domain.entities.JobEntity;
import job_tracker.backend.mappers.Mapper;
import job_tracker.backend.repositories.CityRepository;
import job_tracker.backend.repositories.JobRepository;
import job_tracker.backend.services.JobService;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final CityRepository cityRepository;
    private final Mapper<JobEntity,JobDto> jobMapper;

    public JobServiceImpl(JobRepository jobRepository, CityRepository cityRepository, Mapper<JobEntity, JobDto> jobMapper) {
        this.jobRepository = jobRepository;
        this.cityRepository = cityRepository;
        this.jobMapper = jobMapper;
    }


    @Override
    public JobDto save(JobDto jobDto) {
        JobEntity jobEntity = jobMapper.mapToEntity(jobDto);
        CityEntity savedCity = cityRepository.findById(jobDto.getLocation_id()).orElseThrow(() -> new RuntimeException("City not found"));
        jobEntity.setLocation(savedCity);
        JobEntity savedJob = jobRepository.save(jobEntity);
        return jobMapper.mapToDto(jobEntity);
    }
}
