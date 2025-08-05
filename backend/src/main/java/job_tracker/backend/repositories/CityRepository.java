package job_tracker.backend.repositories;

import job_tracker.backend.domain.entities.CityEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository  extends CrudRepository<CityEntity, Long> {

}
