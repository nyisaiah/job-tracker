package job_tracker.backend.services.impl;

import job_tracker.backend.domain.dtos.JobDto;
import job_tracker.backend.domain.entities.CityEntity;
import job_tracker.backend.domain.entities.JobEntity;
import job_tracker.backend.mappers.Mapper;
import job_tracker.backend.repositories.CityRepository;
import job_tracker.backend.repositories.JobRepository;
import job_tracker.backend.services.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

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
    public JobDto findOne(Long id) {
        Optional<JobEntity> queriedJob = jobRepository.findById(id);
        return queriedJob
                .map(jobMapper::mapToDto)
                .orElseThrow(() -> new RuntimeException("Job not found"));
    }

    @Override
    public List<JobDto> findAll() {
        List<JobEntity> result = StreamSupport
                .stream(jobRepository.findAll().spliterator(),false)
                .toList();

        return result.stream().map((jobMapper::mapToDto))
                .toList();
    }

    @Override
    public JobDto partialUpdate(Long id, JobDto jobDto) {
        jobDto.setId(id);
        Optional<JobEntity> jobEntity = jobRepository.findById(id).map(existingJob -> {
            Optional.ofNullable(jobDto.getCompany()).ifPresent(existingJob::setCompany);
            Optional.ofNullable(jobDto.getPosition()).ifPresent(existingJob::setPosition);
            Optional.ofNullable(jobDto.getJobStatus()).ifPresent(existingJob::setJobStatus);
            Optional.ofNullable(jobDto.getLocationId()).ifPresent(locationId -> {
                CityEntity cityEntity = cityRepository.findById(locationId).orElseThrow( () -> new RuntimeException(""));
                existingJob.setLocation(cityEntity);
            });

            return jobRepository.save(existingJob);
        });

        return jobEntity
                .map(jobMapper::mapToDto)
                .orElseThrow(() -> new RuntimeException("no"));
    }

    @Override
    public boolean exists(Long id) {
        return jobRepository.existsById(id);
    }

    @Override
    public JobDto save(JobDto jobDto) {
        JobEntity jobEntity = jobMapper.mapToEntity(jobDto);
        CityEntity savedCity = cityRepository.findById(jobDto.getLocationId()).orElseThrow(() -> new RuntimeException("City not found"));
        jobEntity.setLocation(savedCity);
        JobEntity savedJob = jobRepository.save(jobEntity);
        return jobMapper.mapToDto(jobEntity);
    }
}
