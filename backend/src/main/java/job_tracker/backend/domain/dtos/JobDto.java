package job_tracker.backend.domain.dtos;

import job_tracker.backend.domain.JobStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobDto {
    private Long id;
    private String company;
    private String position;
    private JobStatus jobStatus;

    private Long locationId;
}
