package job_tracker.backend.repositories;

import job_tracker.backend.domain.entities.StateEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends CrudRepository<StateEntity, Long> {

}
