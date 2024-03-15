package autobotzi.project.dto;

import autobotzi.project.utils.Period;
import autobotzi.project.utils.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class ProjectUpdate {
    private String name;
    private String description;
    private Period period;
    private Status status;
}
