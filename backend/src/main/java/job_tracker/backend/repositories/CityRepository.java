package job_tracker.backend.repositories;

import job_tracker.backend.domain.entities.CityEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository  extends CrudRepository<CityEntity, Long> {
    @Query("SELECT c.id FROM CityEntity c WHERE c.cityName = :cityName AND c.state.id = :state")
    Optional<CityEntity> findByCityNameAndState(@Param("cityName") String cityName, @Param("state") String state_id);
}
