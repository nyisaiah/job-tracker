package job_tracker.backend.domain.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="city")
public class CityEntity {

    @Id
    private String cityName;

    @Id
    @ManyToOne
    @JoinColumn(name = "state_id")
    private StateEntity stateEntity;
}
