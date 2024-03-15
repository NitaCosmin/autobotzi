package autobotzi.user.skill.dto;

import autobotzi.user.skill.utils.Experience;
import autobotzi.user.skill.utils.Level;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class UserSkillsDto {
    private Level level;
    private Experience experience;
    private String endorsements;

}
