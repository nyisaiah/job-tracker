package job_tracker.backend.repositories;

import job_tracker.backend.domain.entities.JobEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends CrudRepository<JobEntity, Long> {

}
