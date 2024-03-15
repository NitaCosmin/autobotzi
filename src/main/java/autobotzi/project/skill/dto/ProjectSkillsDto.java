package autobotzi.project.skill.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class ProjectSkillsDto {
    private String projectName;
    private String skillName;
    private Integer level;
}
