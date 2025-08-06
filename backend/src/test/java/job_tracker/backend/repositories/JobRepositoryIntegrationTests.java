package job_tracker.backend.repositories;

import job_tracker.backend.domain.JobStatus;
import job_tracker.backend.domain.entities.CityEntity;
import job_tracker.backend.domain.entities.JobEntity;
import job_tracker.backend.domain.entities.StateEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class JobRepositoryIntegrationTests {
    private final JobRepository jobRepository;
    private final CityRepository cityRepository;
    private final StateRepository stateRepository;

    @Autowired
    public JobRepositoryIntegrationTests(JobRepository jobRepository, CityRepository cityRepository, StateRepository stateRepository) {
        this.jobRepository = jobRepository;
        this.cityRepository = cityRepository;

        this.stateRepository = stateRepository;
    }

    @Test
    void testThatJobCanBeCreatedAndRecalled() {
        StateEntity stateEntity = StateEntity.builder().id("PA").name("Pennsylvania").build();

        CityEntity city = CityEntity.builder().id(1L).cityName("Easton").state(stateEntity).build();

        JobEntity jobEntity = JobEntity.builder().company("Amazon").
                position("Software Engineer").
                jobStatus(JobStatus.INTERVIEWING).
                location(city).
                build();

        JobEntity savedJob = jobRepository.save(jobEntity);
        Optional<JobEntity> retrieved = jobRepository.findById(savedJob.getId());
        assertThat(retrieved).isPresent().contains(savedJob);
    }
}
