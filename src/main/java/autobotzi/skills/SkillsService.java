package autobotzi.skills;

import autobotzi.skills.dto.SkillsDto;
import autobotzi.skills.dto.SkillsDtoDelete;

import java.util.List;

public interface SkillsService {

     Skills updateSkill(SkillsDto skillsDto,String email);
     Skills addSkill(SkillsDto skillsDto, String email);
     SkillsDto getSkill(String name);
     List<SkillsDto> getSkills();
     void deleteSkill(SkillsDtoDelete skillsDtoDelete);
}
