package job_tracker.backend.domain.entities;

import jakarta.persistence.*;
import job_tracker.backend.domain.JobStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="jobs")
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_id_seq")
    @SequenceGenerator(
            name = "job_id_seq",
            sequenceName = "job_id_seq",
            allocationSize = 1
    )
    private Long id;
    private String company;
    private String position;

    @Enumerated(EnumType.STRING)
    private JobStatus jobStatus;

    @ManyToOne

    @JoinColumn(name = "location_id")
    private CityEntity location;
}
