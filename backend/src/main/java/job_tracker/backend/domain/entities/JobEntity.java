package job_tracker.backend.domain.entities;

import jakarta.persistence.*;
import job_tracker.backend.domain.JobStatus;
import job_tracker.backend.domain.dtos.CityDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="job")
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_id_seq")
    private Long id;
    private String company;
    private String position;

    @Enumerated(EnumType.STRING)
    private JobStatus jobStatus;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "city_name", referencedColumnName = "cityName"),
            @JoinColumn(name = "state_id", referencedColumnName = "state_id")
    })
    private CityEntity location;
}
